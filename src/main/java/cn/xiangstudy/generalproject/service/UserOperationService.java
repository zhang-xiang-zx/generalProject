package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.dto.UpdatePasswordDTO;
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

    /**
     * 修改密码
     * @author zhangxiang
     * @date 2025/9/8 16:20
     * @param updatePasswordDTO
     */
    void forgotPassword(UpdatePasswordDTO updatePasswordDTO);
}
