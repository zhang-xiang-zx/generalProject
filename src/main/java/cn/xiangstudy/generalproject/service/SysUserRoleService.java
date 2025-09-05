package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.dto.UserRoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService {

    /**
     * 创建用户角色
     * @author zhangxiang
     * @date 2025/9/4 16:14
     * @param dto
     * @return int
     */
    void createUserRole(UserRoleDTO dto);

    /**
     * 删除用户角色, 传参不能都为空
     * @author zhangxiang
     * @date 2025/9/5 11:59
     * @param userId
     * @param userRoleId
     */
    void deleteUserRole(Long userId, Long userRoleId);

    /**
     * 根据用户ID查找所对应的角色ID
     * @author zhangxiang
     * @date 2025/9/5 15:01
     * @param userIds
     * @return java.util.List<cn.xiangstudy.generalproject.pojo.entity.SysUserRole>
     */
    List<SysUserRole> selectUserRoleByUserIds(List<Long> userIds);
}
