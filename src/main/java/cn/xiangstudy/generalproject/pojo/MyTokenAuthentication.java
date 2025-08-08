package cn.xiangstudy.generalproject.pojo;

import cn.xiangstudy.generalproject.pojo.entity.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * 自定义spring security认证
 * @author zhangxiang
 * @date 2025-08-08 14:18
 */
@Data
@SuperBuilder
public class MyTokenAuthentication implements Authentication {

    private Long userId;

    private boolean isSuccess;

    // 授权决策时获取用户的权限/角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // 通常返回密码或token等凭证
    @Override
    public Object getCredentials() {
        return null;
    }

    // 可能包含IP、会话ID等额外信息
    @Override
    public Object getDetails() {
        return null;
    }

    // 返回用户身份标识（用户名/用户对象）
    @Override
    public Object getPrincipal() {
        return userId;
    }

    // 判断是否认证
    @Override
    public boolean isAuthenticated() {
        return isSuccess;
    }

    // 由AuthenticationManager设置认证状态
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return "";
    }
}
