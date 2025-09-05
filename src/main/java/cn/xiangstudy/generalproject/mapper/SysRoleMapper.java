package cn.xiangstudy.generalproject.mapper;

import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {

    int createRole(SysRole sysRole);

    int updateRole(SysRole sysRole);

    List<SysRole> selectRoleListByKeyword(@Param("keyword") String keyword);

    int deleteRoleByRoleIds(@Param("roleIds") List<Long> roleIds);

    SysRole selectRoleByRoleKey(@Param("roleKey") String roleKey);

    List<SysRole> selectRoleByRoleIds(@Param("roleIds") List<Long> roleIds);
}
