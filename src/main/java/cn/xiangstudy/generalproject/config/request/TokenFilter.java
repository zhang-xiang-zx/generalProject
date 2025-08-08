package cn.xiangstudy.generalproject.config.request;

import cn.xiangstudy.generalproject.config.response.BusinessException;
import cn.xiangstudy.generalproject.config.response.JsonAuthenticationEntryPoint;
import cn.xiangstudy.generalproject.pojo.entity.SysToken;
import cn.xiangstudy.generalproject.service.UserTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author zhangxiang
 * @date 2025-07-11 10:30
 */
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    private final UserTokenService userTokenService;

    private final JsonAuthenticationEntryPoint entryPoint;

    public TokenFilter(UserTokenService userTokenService, JsonAuthenticationEntryPoint entryPoint) {
        this.userTokenService = userTokenService;
        this.entryPoint = entryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("进到拦截");

        String token = request.getHeader("token");

        try{
            if (token == null) {
                throw new BusinessException(401, "token 账号或密码出错");
            }

            // 查找token是否存在
            SysToken sysToken = userTokenService.selectUserTokenByToken(token);


            filterChain.doFilter(request, response);
        }catch (BusinessException e){
            request.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", e);
            entryPoint.commence(request, response, new BadCredentialsException(e.getMessage(), e));
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String uri = request.getRequestURI();

//        System.out.println("在not filter中：" + uri);

//        return requestURI.startsWith("/swagger-ui/")
//                || requestURI.startsWith("/swagger-ui.html")
//                || requestURI.startsWith("/swagger-resources")
//                || requestURI.startsWith("/v3/api-docs")
//                || requestURI.startsWith("/webjars")
//                || requestURI.startsWith("/doc.html")
//                || requestURI.startsWith("/favicon.ico")
//                || requestURI.startsWith("/.well-known");

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
