package cn.xiangstudy.generalproject.controller.mgr;

import cn.xiangstudy.generalproject.pojo.dto.PageDTO;
import cn.xiangstudy.generalproject.pojo.dto.RoleDTO;
import cn.xiangstudy.generalproject.pojo.dto.UpdateRoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import cn.xiangstudy.generalproject.pojo.utils.PageInfo;
import cn.xiangstudy.generalproject.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createRole(@RequestBody RoleDTO role) {
        service.createRole(role);
    }

    @PostMapping("update")
    @Operation(summary = "修改角色")
    public void updateRole(@RequestBody UpdateRoleDTO role) {
        service.updateRole(role);
    }

    @GetMapping("list")
    @Operation(summary = "角色列表")
    public PageInfo<SysRole> selectRoleList(@RequestParam(required = false) String keyword,
                                            @ParameterObject PageDTO pageDTO){
        return service.selectRoleListByKeyword(keyword, pageDTO);
    }

    @PostMapping("delete")
    @Operation(summary = "删除角色")
    public void deleteRole(@RequestBody List<Long> roleIds){
        service.deleteRole(roleIds);
    }
}
