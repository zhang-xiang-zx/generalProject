package cn.xiangstudy.generalproject.mapper;

import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {

    int createRole(SysRole sysRole);

    int updateRole(SysRole sysRole);

    List<SysRole> selectRoleListByKeyword(@Param("keyword") String keyword);
}
