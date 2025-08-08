package cn.xiangstudy.generalproject.config.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * spring security 异常处理器
 * 在filter中抛出的异常，因为未进入controller中，所以捕获不到，需要在此捕获
 * @author zhangxiang
 * @date 2025-08-06 10:45
 */
@Component
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        // 默认状态码
        int code = HttpServletResponse.SC_UNAUTHORIZED;
        String message = authException.getMessage();


        // 如果是 BusinessException，从 request 中取出来
        Object ex = request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (ex instanceof BusinessException be) {
            code = be.getCode();
            message = be.getMessage();
        }

        response.setStatus(code);
        response.setContentType("application/json;charset=UTF-8");
        Result<Void> result = Result.fail(code, message);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
