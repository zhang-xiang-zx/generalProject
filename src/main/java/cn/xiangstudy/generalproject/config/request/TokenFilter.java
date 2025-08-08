package cn.xiangstudy.generalproject.config.request;

import cn.xiangstudy.generalproject.config.response.BusinessException;
import cn.xiangstudy.generalproject.config.response.JsonAuthenticationEntryPoint;
import cn.xiangstudy.generalproject.pojo.MyTokenAuthentication;
import cn.xiangstudy.generalproject.pojo.entity.SysToken;
import cn.xiangstudy.generalproject.service.UserTokenService;
import cn.xiangstudy.generalproject.utils.DateUtils;
import cn.xiangstudy.generalproject.utils.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

/**
 * @author zhangxiang
 * @date 2025-07-11 10:30
 */
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    private final JsonAuthenticationEntryPoint entryPoint;

    public TokenFilter(JsonAuthenticationEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");

        try{
            if (token == null) {
                throw new BusinessException(401, "header no token");
            }else{

                // 解密token, 获取信息
                SysToken sysToken = StringUtils.decodeToken(token);

                if (sysToken == null) {
                    throw new BusinessException(401, "encode no token");
                }

                Date expireTime = sysToken.getExpireTime();
                Date nowDateTime = DateUtils.nowDate();

                Long cha = DateUtils.differenceTime(nowDateTime, expireTime);

                if(cha >= 0){
                    throw new BusinessException(401, "token expired");
                }

                // 告诉spring security当前请求已经认证过了, 并且是谁在访问, 否则Spring Security 还是会认为当前请求是“匿名用户”，就会返回 401/403。
                MyTokenAuthentication userAuthentication = MyTokenAuthentication.builder()
                        .userId(sysToken.getUserId())
                        .isSuccess(true).build();
                SecurityContextHolder.getContext().setAuthentication(userAuthentication);
            }
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
                || uri.startsWith("/.well-known")                  // Let's Encrypt 等认证路径
                || uri.startsWith("/mgr/userOperation/register") // 注册接口
                || uri.startsWith("/mgr/userOperation/login"); // 登录接口
    }

}
