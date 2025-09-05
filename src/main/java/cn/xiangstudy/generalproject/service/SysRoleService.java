package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.dto.PageDTO;
import cn.xiangstudy.generalproject.pojo.dto.RoleDTO;
import cn.xiangstudy.generalproject.pojo.dto.UpdateRoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import cn.xiangstudy.generalproject.pojo.utils.PageInfo;

import java.util.List;

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
    void updateRole(UpdateRoleDTO roleDTO);

    /**
     * 查找角色列表
     * @author zhangxiang
     * @date 2025/8/28 16:05
     * @param keyword
     * @return java.util.List<cn.xiangstudy.generalproject.pojo.entity.SysRole>
     */
    PageInfo<SysRole> selectRoleListByKeyword(String keyword, PageDTO pageDTO);

    /**
     * 删除角色
     * @author zhangxiang
     * @date 2025/9/2 15:46
     * @param ids
     */
    void deleteRole(List<Long> ids);

    /**
     * 根据key寻找角色
     * @author zhangxiang
     * @date 2025/9/4 16:26
     * @param roleKey
     * @return cn.xiangstudy.generalproject.pojo.entity.SysRole
     */
    SysRole selectRoleByRoleKey(String roleKey);

    /**
     * 根据角色ID查找角色
     * @author zhangxiang
     * @date 2025/9/5 15:17
     * @param roleIds
     * @return java.util.List<cn.xiangstudy.generalproject.pojo.entity.SysRole>
     */
    List<SysRole> selectRoleByRoleIds(List<Long> roleIds);
}
