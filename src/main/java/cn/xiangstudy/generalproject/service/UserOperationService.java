package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.dto.UserLoginDTO;
import cn.xiangstudy.generalproject.pojo.dto.UserRegisterDTO;

public interface UserOperationService {

    /**
     * 用户注册
     * @author zhangxiang
     * @date 2025/7/22 15:18
     * @param dto
     */
    void register(UserRegisterDTO dto);


    /**
     * 用户登录
     * @author zhangxiang
     * @date 2025/7/29 17:07
     * @param dto
     * @return string
     */
    String login(UserLoginDTO dto);
}
