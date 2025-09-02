package cn.xiangstudy.generalproject.config.request;

import cn.xiangstudy.generalproject.config.threadLocal.ContextKeys;
import cn.xiangstudy.generalproject.config.threadLocal.ContextManager;
import cn.xiangstudy.generalproject.pojo.LogContext;
import cn.xiangstudy.generalproject.pojo.MyTokenAuthentication;
import cn.xiangstudy.generalproject.pojo.entity.SysLog;
import cn.xiangstudy.generalproject.pojo.entity.SysToken;
import cn.xiangstudy.generalproject.utils.NetworkUtils;
import cn.xiangstudy.generalproject.utils.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 操作拦截日志
 * @author zhangxiang
 * @date 2025-08-08 16:38
 */
@Slf4j
public class LogFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String clientIP = NetworkUtils.getClientIP(request);

        String token = request.getHeader("token");

        String requestURI = request.getRequestURI();

        int type;
        if(requestURI.contains("/login")){
            type = 0;
        }else{
            type = 1;
        }


        Long userId = null;
        if(!StringUtils.isEmpty(token)){
            // 解密token信息
            SysToken sysToken = StringUtils.decodeToken(token);
            userId = sysToken.getUserId();
        }

        SysLog logInfo = SysLog.builder()
                .ip(clientIP)
                .requestUri(requestURI)
                .userId(userId)
                .type(type)
                .build();

        //日志存放在上下文中
//        LogContext.set(logInfo);
        ContextManager.set(ContextKeys.SYS_LOG, logInfo);

        // 登录时存储客户端请求IP，方便登录成功记录
        if(request.getRequestURI().contains("/login")){

            MyTokenAuthentication info = MyTokenAuthentication.builder()
                    .ipAddress(clientIP)
                    .build();

            SecurityContextHolder.getContext().setAuthentication(info);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();

        // Swagger UI & Knife4j 常用静态资源路径
        return uri.startsWith("/swagger-ui/")                  // 新版 swagger-ui 的静态资源
                || uri.equals("/swagger-ui.html")                  // 旧版 Swagger UI 入口
                || uri.startsWith("/swagger-resources")            // Swagger 资源配置接口
                || uri.startsWith("/v3/api-docs")                   // OpenAPI 3 文档接口
                || uri.startsWith("/webjars/")                     // Swagger 依赖的 JS/CSS
                || uri.equals("/doc.html")                         // Knife4j 文档页面
                || uri.contains("favicon")                         // favicon.ico 及其他图标
                || uri.startsWith("/.well-known");                  // Let's Encrypt 等认证路径
    }
}
