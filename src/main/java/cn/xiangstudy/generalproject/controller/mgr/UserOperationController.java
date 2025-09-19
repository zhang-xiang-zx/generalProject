package cn.xiangstudy.generalproject.controller.mgr;

import cn.xiangstudy.generalproject.pojo.dto.UpdatePasswordDTO;
import cn.xiangstudy.generalproject.pojo.dto.UserLoginDTO;
import cn.xiangstudy.generalproject.pojo.dto.UserRegisterDTO;
import cn.xiangstudy.generalproject.service.UserOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxiang
 * @date 2025-07-22 15:03
 */
@RestController
@RequestMapping("mgr/userOperation")
@Tag(name = "用户操作")
public class UserOperationController {

    private final UserOperationService service;

    @Autowired
    public UserOperationController(UserOperationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "注册")
    public void register(@RequestBody UserRegisterDTO dto){
        service.register(dto);
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public String login(@RequestBody UserLoginDTO dto){
        return service.login(dto);
    }

    @PostMapping("forgotPassword")
    @Operation(summary = "忘记密码")
    public void forgotPassword(@RequestBody UpdatePasswordDTO dto){
        service.forgotPassword(dto);
    }
}
