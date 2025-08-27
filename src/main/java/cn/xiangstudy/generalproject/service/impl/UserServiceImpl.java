package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.mapper.UserMapper;
import cn.xiangstudy.generalproject.pojo.vo.PageInfoVO;
import cn.xiangstudy.generalproject.pojo.vo.UserVO;
import cn.xiangstudy.generalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangxiang
 * @date 2025-07-10 15:17
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String selectMaxUserAccount() {
        return userMapper.selectMaxUserAccount();
    }

    @Override
    public List<UserVO> selectAllUsers(String keyword, Integer num, Integer page) {

        return userMapper.selectAllUsers(keyword, 0);
    }
}
