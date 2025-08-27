package cn.xiangstudy.generalproject.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2025-07-29 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "登录")
public class UserLoginDTO {

    @Schema(description = "账号")
    private String account;

    @Schema(description = "密码")
    private String password;

}
