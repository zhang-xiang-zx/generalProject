package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.mapper.UserTokenMapper;
import cn.xiangstudy.generalproject.pojo.entity.SysToken;
import cn.xiangstudy.generalproject.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxiang
 * @date 2025-07-31 09:20
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private final UserTokenMapper userTokenMapper;

    public UserTokenServiceImpl(UserTokenMapper userTokenMapper) {
        this.userTokenMapper = userTokenMapper;
    }

    @Override
    public SysToken selectUserTokenByToken(String token) {

        return userTokenMapper.selectUserTokenByToken(token);
    }
}
