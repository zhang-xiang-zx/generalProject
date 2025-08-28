package cn.xiangstudy.generalproject.controller.mgr;

import cn.xiangstudy.generalproject.pojo.dto.RoleDTO;
import cn.xiangstudy.generalproject.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxiang
 * @date 2025-08-27 17:22
 */
@RestController
@RequestMapping("mgr/role")
@Tag(name = "角色管理")
public class RoleController {

    private final SysRoleService service;

    @Autowired
    public RoleController(SysRoleService sysRoleService) {
        this.service = sysRoleService;
    }

    @PostMapping("create")
    @Operation(summary = "创建角色")
    public void createRole(RoleDTO role) {
        service.createRole(role);
    }

    @PostMapping("update")
    @Operation(summary = "修改角色")
    public void updateRole(RoleDTO role) {

    }
}
