package cn.xiangstudy.generalproject.service;

import cn.xiangstudy.generalproject.pojo.dto.PageDTO;
import cn.xiangstudy.generalproject.pojo.dto.UpdateUserRoleDTO;
import cn.xiangstudy.generalproject.pojo.utils.PageInfo;
import cn.xiangstudy.generalproject.pojo.vo.UserVO;

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
     * @param pageDTO
     * @return java.util.List<cn.xiangstudy.generalproject.pojo.vo.UserVO>
     */
    PageInfo<UserVO> selectAllUsers(String keyword, PageDTO pageDTO);

    /**
     * 修改用户角色
     * @author zhangxiang
     * @date 2025/9/5 14:51
     * @param dto
     */
    void updateUserRole(UpdateUserRoleDTO dto);
}
