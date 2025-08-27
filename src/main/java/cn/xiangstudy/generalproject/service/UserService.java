package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.vo.PageInfoVO;
import cn.xiangstudy.generalproject.pojo.vo.UserVO;

import java.util.List;

public interface UserService {

    /**
     * 查找最大账号
     * @author zhangxiang
     * @date 2025/7/24 08:58
     * @return java.lang.String
     */
    String selectMaxUserAccount();

    /**
     * 查找用户列表
     * @author zhangxiang
     * @date 2025/7/24 17:14
     * @param keyword
     * @param num
     * @param page
     * @return java.util.List<cn.xiangstudy.generalproject.pojo.vo.UserVO>
     */
    List<UserVO> selectAllUsers(String keyword, Integer num, Integer page);
}
