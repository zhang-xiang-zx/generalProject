package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.config.response.BusinessException;
import cn.xiangstudy.generalproject.mapper.SysUserRoleMapper;
import cn.xiangstudy.generalproject.pojo.dto.UserRoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysUserRole;
import cn.xiangstudy.generalproject.service.SysUserRoleService;
import cn.xiangstudy.generalproject.utils.ListUtils;
import cn.xiangstudy.generalproject.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户关联角色
 * @author zhangxiang
 * @date 2025-09-04 16:07
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    private final SysUserRoleMapper mapper;

    public SysUserRoleServiceImpl(SysUserRoleMapper sysUserRoleMapper) {
        this.mapper = sysUserRoleMapper;
    }

    @Override
    public void createUserRole(UserRoleDTO dto) {

        if(ObjectUtils.isAnnotationNull(dto)){
            throw new BusinessException(401, "传参不足");
        }

        SysUserRole sysUserRole = ObjectUtils.cloneProperties(dto, SysUserRole.class);
        mapper.createUserRole(sysUserRole);
    }

    @Override
    public void deleteUserRole(Long userId, Long userRoleId) {

        if(userId == null && userRoleId == null){
            throw new BusinessException(500, "不能都为空");
        }

        mapper.deleteUserRole(userId, userRoleId);
    }

    @Override
    public List<SysUserRole> selectUserRoleByUserIds(List<Long> userIds) {
        List<SysUserRole> result = new ArrayList<>();

        if(!ListUtils.isEmpty(userIds)){
            result = mapper.selectUserRoleByUserIds(userIds);
        }
        return result;
    }
}
