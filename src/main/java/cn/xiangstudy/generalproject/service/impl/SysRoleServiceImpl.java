package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.config.constant.SysConst;
import cn.xiangstudy.generalproject.mapper.SysRoleMapper;
import cn.xiangstudy.generalproject.pojo.dto.PageDTO;
import cn.xiangstudy.generalproject.pojo.dto.RoleDTO;
import cn.xiangstudy.generalproject.pojo.dto.UpdateRoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import cn.xiangstudy.generalproject.pojo.utils.PageInfo;
import cn.xiangstudy.generalproject.service.SysRoleService;
import cn.xiangstudy.generalproject.utils.ObjectUtils;
import cn.xiangstudy.generalproject.utils.PageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public void updateRole(UpdateRoleDTO roleDTO) {

        // 判断不能全为空
        if(!ObjectUtils.isUpdateObjectNull(roleDTO)){
            SysRole sysRole = ObjectUtils.cloneProperties(roleDTO, SysRole.class);
            mapper.updateRole(sysRole);
        }

    }

    @Override
    public PageInfo<SysRole> selectRoleListByKeyword(String keyword, PageDTO pageDTO) {

        // 分页
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());

        List<SysRole> sysRoles = mapper.selectRoleListByKeyword(keyword);

        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoles);

        return pageInfo;
    }

    @Override
    public void deleteRole(Long[] ids) {

    }
}
