package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.config.response.BusinessException;
import cn.xiangstudy.generalproject.mapper.UserMapper;
import cn.xiangstudy.generalproject.pojo.dto.PageDTO;
import cn.xiangstudy.generalproject.pojo.dto.UpdateUserRoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import cn.xiangstudy.generalproject.pojo.entity.SysUserRole;
import cn.xiangstudy.generalproject.pojo.utils.Page;
import cn.xiangstudy.generalproject.pojo.utils.PageInfo;
import cn.xiangstudy.generalproject.pojo.vo.UserVO;
import cn.xiangstudy.generalproject.service.SysRoleService;
import cn.xiangstudy.generalproject.service.SysUserRoleService;
import cn.xiangstudy.generalproject.service.UserService;
import cn.xiangstudy.generalproject.utils.ObjectUtils;
import cn.xiangstudy.generalproject.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangxiang
 * @date 2025-07-10 15:17
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final SysUserRoleService sysUserRoleService;

    private final SysRoleService sysRoleService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, SysUserRoleService sysUserRoleService, SysRoleService sysRoleService) {
        this.userMapper = userMapper;
        this.sysUserRoleService = sysUserRoleService;
        this.sysRoleService = sysRoleService;
    }

    @Override
    public String selectMaxUserAccount() {
        return userMapper.selectMaxUserAccount();
    }

    @Override
    public PageInfo<UserVO> selectAllUsers(String keyword, PageDTO pageDTO) {

        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<UserVO> userInfoList = userMapper.selectAllUsers(keyword);

        // 用户ID
        List<Long> userIdList = userInfoList.stream().map(UserVO::getUserId).collect(Collectors.toList());

        //查找用户角色
        List<SysUserRole> userRoleInfoList = sysUserRoleService.selectUserRoleByUserIds(userIdList);
        List<Long> roleIdList = userRoleInfoList.stream().map(SysUserRole::getRoleId).distinct().collect(Collectors.toList());
        List<SysRole> roleInfoList = sysRoleService.selectRoleByRoleIds(roleIdList);

        List<UserVO> collect = userInfoList.stream().peek(single -> {
            Long userId = single.getUserId();
            userRoleInfoList.stream().filter(urInfo -> urInfo.getUserId().equals(userId)).findFirst().ifPresent(urInfo -> {
                Long roleId = urInfo.getRoleId();
                roleInfoList.stream().filter(rInfo -> rInfo.getRoleId().equals(roleId)).findFirst().ifPresent(roleInfo -> {
                    single.setRoleKey(roleInfo.getRoleKey());
                    single.setRoleName(roleInfo.getRoleName());
                });
            });
        }).collect(Collectors.toList());

        // 重新设置list
        Page<UserVO> oldPageInfo = (Page<UserVO>) userInfoList;

        return PageHelper.edit(oldPageInfo, collect);
    }

    @Override
    public void updateUserRole(UpdateUserRoleDTO dto) {

        if(ObjectUtils.isAnnotationNull(dto)){
            throw new BusinessException(500, "参数不能为空");
        }

        userMapper.updateUserRole(dto);
    }
}
