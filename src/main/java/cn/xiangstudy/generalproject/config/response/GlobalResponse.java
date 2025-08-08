package cn.xiangstudy.generalproject.config.response;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一处理异常和返回结果
 *
 * @author zhangxiang
 * @date 2025-07-24 14:46
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponse implements ResponseBodyAdvice<Object> {

    /**
     * 处理业务上的异常
     *
     * @param e
     * @return cn.xiangstudy.generalproject.config.response.Result<java.lang.Void>
     * @author zhangxiang
     * @date 2025/7/24 15:45
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> businessException(BusinessException e) {
        log.info("自定义异常：{}", e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> exception(Exception e) {
        log.info("系统异常：{}", e.getMessage());
        return Result.fail(500, "系统异常");
    }

    /**
     * 判断是否对某个方法值生效
     *
     * @param returnType
     * @param converterType
     * @return boolean
     * @author zhangxiang
     * @date 2025/7/24 14:54
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        boolean isNeedProcess = true;

        // 不处理的类型
        if (returnType.getParameterType().equals(ResponseEntity.class)) {
            isNeedProcess = false;
        }

        if(returnType.getParameterType().equals(Result.class)){
            isNeedProcess = false;
        }

        // 不处理swagger
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String requestURI = requestAttributes.getRequest().getRequestURI();
        if (requestURI.startsWith("/v3/api-docs") || requestURI.startsWith("/webjars")) {
            isNeedProcess = false;
        }

        return isNeedProcess;
    }

    /**
     * 处理返回值
     *
     * @param body                  controller返回的原始数据
     * @param returnType            方法返回值类型
     * @param selectedContentType   响应的 MediaType（如 application/json）
     * @param selectedConverterType 使用的消息转换器
     * @param request               当前请求
     * @param response              当前响应
     * @return java.lang.Object
     * @author zhangxiang
     * @date 2025/7/24 14:51
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (returnType.getParameterType().equals(String.class)) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return JSON.toJSONString(Result.success(body));
        }

        return Result.success(body);
    }
}
