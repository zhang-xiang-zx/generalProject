package cn.xiangstudy.generalproject.controller.mgr;

import cn.xiangstudy.generalproject.pojo.dto.PageDTO;
import cn.xiangstudy.generalproject.pojo.dto.UpdateUserRoleDTO;
import cn.xiangstudy.generalproject.pojo.utils.PageInfo;
import cn.xiangstudy.generalproject.pojo.vo.UserVO;
import cn.xiangstudy.generalproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxiang
 * @date 2025-07-10 15:13
 */
@RestController
@RequestMapping("mgr/user")
@Tag(name = "用户管理")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list")
    @Operation(summary = "用户列表", description = "所有用户列表")
    public PageInfo<UserVO> userList(@RequestParam(required = false) String keyword,
                                     @ParameterObject PageDTO pageDTO) {

        return userService.selectAllUsers(keyword, pageDTO);
    }

    @PostMapping("updateUserRole")
    @Operation(summary = "修改用户角色", description = "修改用户角色")
    public void updateUserRole(@RequestBody UpdateUserRoleDTO dto) {
        userService.updateUserRole(dto);
    }
}
