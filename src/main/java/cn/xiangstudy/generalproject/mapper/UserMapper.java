package cn.xiangstudy.generalproject.mapper;

import cn.xiangstudy.generalproject.pojo.dto.UpdateUserRoleDTO;
import cn.xiangstudy.generalproject.pojo.entity.User;
import cn.xiangstudy.generalproject.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 查找最大账号
     * @author zhangxiang
     * @date 2025/7/24 11:55
     * @return java.lang.String
     */
    String selectMaxUserAccount();

    /**
     * 新增用户
     * @author zhangxiang
     * @date 2025/7/24 11:55
     * @param user
     */
    void createUser(User user);

    /**
     * 查找用户列表
     * @author zhangxiang
     * @date 2025/7/24 17:31
     * @param keyword
     * @return java.util.List<cn.xiangstudy.generalproject.pojo.vo.UserVO>
     */
    List<UserVO> selectAllUsers(@Param("keyword") String keyword);

    /**
     * 通过手机号码查找用户
     * @author zhangxiang
     * @date 2025/7/29 16:22
     * @param phone
     * @return cn.xiangstudy.generalproject.pojo.entity.User
     */
    User selectUserByPhone(@Param("phone") String phone);

    /**
     * 通过昵称查找用户
     * @author zhangxiang
     * @date 2025/7/29 16:32
     * @param nickname
     * @return cn.xiangstudy.generalproject.pojo.entity.User
     */
    User selectUserByNickname(@Param("nickname") String nickname);

    /**
     * 通过账号查找用户
     * @author zhangxiang
     * @date 2025/9/5 14:43
     * @param account
     * @return cn.xiangstudy.generalproject.pojo.entity.User
     */
    User selectUserLogin(@Param("account") String account);

    /**
     * 修改用户角色
     * @author zhangxiang
     * @date 2025/9/5 14:48
     * @param updateDto
     */
    void updateUserRole(@Param("dto") UpdateUserRoleDTO updateDto);
}
