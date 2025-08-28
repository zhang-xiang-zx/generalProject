package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.config.constant.SysConst;
import cn.xiangstudy.generalproject.mapper.SysRoleMapper;
import cn.xiangstudy.generalproject.pojo.dto.RoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import cn.xiangstudy.generalproject.service.SysRoleService;
import cn.xiangstudy.generalproject.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2025-08-27 17:19
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper mapper;

    public SysRoleServiceImpl(SysRoleMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public void createRole(RoleDTO roleDTO) {

        SysRole sysRole = ObjectUtils.cloneProperties(roleDTO, SysRole.class);
        sysRole.setCreateTime(new Date());
        sysRole.setDelFlag(SysConst.DEL_FLAG_FALSE);
        mapper.createRole(sysRole);
    }

    @Override
    public void updateRole(RoleDTO roleDTO) {

        // 判断不能全为空


        SysRole sysRole = ObjectUtils.cloneProperties(roleDTO, SysRole.class);
        mapper.updateRole(sysRole);
    }
}
