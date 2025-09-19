package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.mapper.SysRoleMapper;
import cn.xiangstudy.generalproject.mapper.SysUserRoleMapper;
import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import cn.xiangstudy.generalproject.pojo.entity.SysUserRole;
import cn.xiangstudy.generalproject.service.PermissionRoleService;
import cn.xiangstudy.generalproject.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 防止循环依赖, 单独一个查询用户角色
 * @author zhangxiang
 * @date 2025-09-08 11:32
 */
@Service
public class PermissionRoleServiceImpl implements PermissionRoleService {

    private final SysRoleMapper sysRoleMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    public PermissionRoleServiceImpl(SysRoleMapper sysRoleMapper, SysUserRoleMapper sysUserRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        List<SysRole> result = new ArrayList<>();
        if(userId != null){
            List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectUserRoleByUserId(userId);
            List<Long> roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).distinct().collect(Collectors.toList());
            if(!ListUtils.isEmpty(roleIdList)){
                List<SysRole> sysRoles = sysRoleMapper.selectRoleByRoleIds(roleIdList);
                result.addAll(sysRoles);
            }

        }
        return result;
    }
}
