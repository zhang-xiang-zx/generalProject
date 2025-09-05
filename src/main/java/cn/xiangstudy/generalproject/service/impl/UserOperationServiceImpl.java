package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.component.user.UserAccountPool;
import cn.xiangstudy.generalproject.config.constant.SysConst;
import cn.xiangstudy.generalproject.config.response.BusinessException;
import cn.xiangstudy.generalproject.mapper.UserMapper;
import cn.xiangstudy.generalproject.pojo.dto.UserLoginDTO;
import cn.xiangstudy.generalproject.pojo.dto.UserRegisterDTO;
import cn.xiangstudy.generalproject.pojo.dto.UserRoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysLoginLog;
import cn.xiangstudy.generalproject.pojo.entity.SysRole;
import cn.xiangstudy.generalproject.pojo.entity.SysToken;
import cn.xiangstudy.generalproject.pojo.entity.User;
import cn.xiangstudy.generalproject.service.SysLoginLogService;
import cn.xiangstudy.generalproject.service.SysRoleService;
import cn.xiangstudy.generalproject.service.SysUserRoleService;
import cn.xiangstudy.generalproject.service.UserOperationService;
import cn.xiangstudy.generalproject.utils.DateUtils;
import cn.xiangstudy.generalproject.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2025-07-22 15:17
 */
@Slf4j
@Service
public class UserOperationServiceImpl implements UserOperationService {

    private final UserAccountPool userAccountPool;

    private final UserMapper userMapper;

    @Value("${my.tokenExpireTime}")
    private int tokenExpire;

    private final SysLoginLogService sysLoginLogService;

    private final SysUserRoleService sysUserRoleService;

    private final SysRoleService sysRoleService;

    @Autowired
    public UserOperationServiceImpl(UserAccountPool userAccountPool, UserMapper userMapper,
                                    SysLoginLogService sysLoginLogService, SysUserRoleService sysUserRoleService, SysRoleService sysRoleService) {
        this.userAccountPool = userAccountPool;
        this.userMapper = userMapper;
        this.sysLoginLogService = sysLoginLogService;
        this.sysUserRoleService = sysUserRoleService;
        this.sysRoleService = sysRoleService;
    }

    @Override
    public void register(UserRegisterDTO dto) {

        String phone = dto.getPhone();
        String nickname = dto.getNickname();
        String password = dto.getPassword();
        if (!StringUtils.checkPhone(phone) || !StringUtils.checkNickname(nickname) || !StringUtils.checkPassword(password)) {
            throw new BusinessException(500, "格式不支持");
        }

        // 查重复; 手机号, 昵称
        User havePhone = userMapper.selectUserByPhone(phone);
        if (havePhone != null) {
            throw new BusinessException(500, "手机号已注册");
        } else {
            User haveNickname = userMapper.selectUserByNickname(nickname);
            if (haveNickname != null) {
                throw new BusinessException(500, "昵称已存在");
            }
        }

        String slatStr = StringUtils.randomSlatStr(SysConst.PASSWORD_SLAT_NUM);

        String alreadyPassword = slatStr + password;

        String encodePassword = StringUtils.encodeMD5(alreadyPassword);

        String newPassword = encodePassword + slatStr;

        String account = userAccountPool.getAccount();

        User userInfo = User.builder()
                .nickname(nickname)
                .account(account)
                .password(newPassword)
                .phone(phone)
                .status(SysConst.USER_STATUS_NORMAL)
                .delFlag(SysConst.DEL_FLAG_FALSE)
                .createTime(DateUtils.nowDate())
                .build();

        userMapper.createUser(userInfo);

        // 查找普通角色ID
        SysRole sysRole = sysRoleService.selectRoleByRoleKey(SysConst.USER_ROLE);

        // 赋予普通用户角色
        if(sysRole != null) {
            UserRoleDTO roleInfo = UserRoleDTO.builder()
                    .userId(userInfo.getUserId())
                    .roleId(sysRole.getRoleId())
                    .build();
            sysUserRoleService.createUserRole(roleInfo);
        }

    }

    @Override
    public String login(UserLoginDTO dto) {

        String account = dto.getAccount();
        String password = dto.getPassword();

        // 查找数据库中是否存在数据
        User user = userMapper.selectUserLogin(account);

        // 进行密码校验
        if (user == null) {
            throw new BusinessException(500, "账号或密码错误");
        } else {

            // 进行密码验证
            String sourcePassword = user.getPassword();

            String slat = StringUtils.getEndStr(sourcePassword, SysConst.PASSWORD_SLAT_NUM);

            String encodePassword = StringUtils.encodeMD5(slat + password) + slat;

            if (!sourcePassword.equals(encodePassword)) {
                throw new BusinessException(500, "账号或密码错误");
            }
        }

        Date nowTime = DateUtils.nowDate();
        Date expireTime = DateUtils.datePlusDay(nowTime, tokenExpire);
        SysToken userInfo = SysToken.builder().userId(user.getUserId())
                .expireTime(expireTime)
                .createTime(nowTime).build();
        String token = StringUtils.encoderByBase64(userInfo);

        // 记录登录IP信息
        String ipAddress = (String) SecurityContextHolder.getContext().getAuthentication().getDetails();

        SysLoginLog loginLogInfo = SysLoginLog.builder()
                .userId(user.getUserId())
                .loginIp(ipAddress)
                .build();

        sysLoginLogService.createUserLoginLog(loginLogInfo);

        return StringUtils.encodeToken(token);
    }
}
