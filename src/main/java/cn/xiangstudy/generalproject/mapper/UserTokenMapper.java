package cn.xiangstudy.generalproject.mapper;

import cn.xiangstudy.generalproject.pojo.entity.SysToken;

public interface UserTokenMapper {

    /**
     * 创建token信息
     * @author zhangxiang
     * @date 2025/7/31 10:04
     * @param token
     * @return int
     */
    int insertUserToken(SysToken token);


    /**
     * 查询token是否存在
     * @author zhangxiang
     * @date 2025/7/31 10:04
     * @param token
     * @return cn.xiangstudy.generalproject.pojo.entity.SysToken
     */
    SysToken selectUserTokenByToken(String token);
}
