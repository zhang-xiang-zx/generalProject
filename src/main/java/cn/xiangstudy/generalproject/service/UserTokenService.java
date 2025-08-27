package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.entity.SysToken;

public interface UserTokenService {

    /**
     * 查看token是否存在
     * @author zhangxiang
     * @date 2025/7/31 10:09
     * @param token
     * @return cn.xiangstudy.generalproject.pojo.entity.SysToken
     */
    SysToken selectUserTokenByToken(String token);
}
