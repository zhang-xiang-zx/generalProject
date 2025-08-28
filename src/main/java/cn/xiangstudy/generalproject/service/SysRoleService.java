package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.dto.RoleDTO;

public interface SysRoleService {

    /**
     * 创建角色
     * @author zhangxiang
     * @date 2025/8/27 17:33
     * @param roleDTO
     */
    void createRole(RoleDTO roleDTO);

    /**
     * 修改角色
     * @author zhangxiang
     * @date 2025/8/28 11:59
     * @param roleDTO
     */
    void updateRole(RoleDTO roleDTO);
}
