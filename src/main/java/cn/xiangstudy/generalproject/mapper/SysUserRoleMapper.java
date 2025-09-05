package cn.xiangstudy.generalproject.mapper;

import cn.xiangstudy.generalproject.pojo.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper {

    int createUserRole(SysUserRole sysUserRole);

    int deleteUserRole(@Param("userId") Long userId,
                       @Param("userRoleId") Long userRoleId);

    List<SysUserRole> selectUserRoleByUserIds(@Param("userIds") List<Long> userIds);
}
