package cn.xiangstudy.generalproject.utils;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 第三方请求工具类
 *
 * @author zhangxiang
 * @date 2025-07-14 09:48
 */
@Slf4j
public class HttpUtils {

    /**
     * hutool Get请求
     * @author zhangxiang
     * @date 2025/7/16 09:53
     * @param url
     * @param params
     * @param headers
     * @return java.lang.String
     */
    public static String hutoolGetHttp(String url, Map<String, Object> params, Map<String, List<String>> headers) {

        String urlStr = HttpUtil.urlWithForm(url, params, StandardCharsets.UTF_8, false);

        String body = "";
        try(HttpResponse execute = HttpUtil.createGet(urlStr).header(headers).timeout(5000)
                    .execute()){

            if(execute.isOk()){
                body = execute.body();
            }

        }catch (Exception e){
            log.info("http get error: {}", e.getMessage());
        }

        return body;
    }

    /**
     * hutool Post请求
     * @author zhangxiang
     * @date 2025/7/16 11:16
     * @param url
     * @param params
     * @param headers
     * @param isFormSub
     * @return java.lang.String
     */
    public static String hutoolPostHttp(String url, Map<String, Object> params, Map<String, List<String>> headers, boolean isFormSub) {

        String result = "";

        HttpRequest request = HttpUtil.createPost(url)
                .timeout(5000)
                .header(headers);

        if(isFormSub){
            request.form(params);
        }else{
            String valueStr = JSON.toJSONString(params);
            request.body(valueStr);
        }

        try (HttpResponse execute = request.execute();){
            if(execute.isOk()){
                result = execute.body();
            }
        }catch (Exception e){
            log.info("http post error: {}", e.getMessage());
        }

        return result;
    }

}
