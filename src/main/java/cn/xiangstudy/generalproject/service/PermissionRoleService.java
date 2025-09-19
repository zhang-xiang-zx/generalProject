package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.entity.SysRole;

import java.util.List;

public interface PermissionRoleService {

    List<SysRole> selectRolesByUserId(Long userId);
}
