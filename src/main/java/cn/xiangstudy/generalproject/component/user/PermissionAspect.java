package cn.xiangstudy.generalproject.component.user;

import cn.xiangstudy.generalproject.config.annotation.RequestRole;
import cn.xiangstudy.generalproject.config.response.BusinessException;
import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import cn.xiangstudy.generalproject.service.PermissionRoleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判断接口权限
 * @author zhangxiang
 * @date 2025-09-08 11:08
 */
@Aspect
@Component
public class PermissionAspect {

    private final PermissionRoleService permissionRoleService;

    public PermissionAspect(PermissionRoleService permissionRoleService) {
        this.permissionRoleService = permissionRoleService;
    }

    @Before("@within(requestRole) || @annotation(requestRole)")
    public void checkRole(JoinPoint joinPoint, RequestRole requestRole) {

        // 获取当前用户ID
        Long currentUserId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 找到用户所拥有的角色
        List<SysRole> sysRoles = permissionRoleService.selectRolesByUserId(currentUserId);

        String[] value = requestRole.value();

        List<String> userRoleKeyList = sysRoles.stream().map(SysRole::getRoleKey).collect(Collectors.toList());

        List<String> requestRoleList = Arrays.asList(value);

        boolean haveRole = userRoleKeyList.stream().anyMatch(requestRoleList::contains);

        if (!haveRole) {
            throw new BusinessException(500, "权限不足");
        }

    }
}
