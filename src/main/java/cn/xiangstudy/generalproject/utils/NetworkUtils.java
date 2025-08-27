package cn.xiangstudy.generalproject.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 获取客户端IP地址
 * @author zhangxiang
 * @date 2025-08-26 11:43
 */
public class NetworkUtils {

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getClientIP(HttpServletRequest request) {

        // 1.检查常见的代理服务器转发头
        for (String header : IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {

                // X-Forwarded-For 可能是一串IP，第一个才是真实客户端IP
                String ip = ipList.split(",")[0].trim();
                if(!ip.isEmpty()){
                    return ip;
                }
            }
        }

        // 2.如果没有代理头，或者头信息无效，则回退到remoteAddr
        String remoteAddr = request.getRemoteAddr();

        // 3.处理本地 IPv6 回环地址，将其转换为 IPv4 格式以便于阅读和一致性处理
        if ("0:0:0:0:0:0:0:1".equals(remoteAddr) || "::1".equals(remoteAddr)) {
            return "127.0.0.1";
        }

        return remoteAddr;
    }
}
