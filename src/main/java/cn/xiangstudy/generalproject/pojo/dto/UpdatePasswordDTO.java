package cn.xiangstudy.generalproject.pojo.dto;

import cn.xiangstudy.generalproject.config.annotation.FieldValueNotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2025-09-08 16:18
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "修改密码")
public class UpdatePasswordDTO {

    @Schema(description = "账号")
    private String account;

    @FieldValueNotNull
    @Schema(description = "手机号")
    private String phone;

    @FieldValueNotNull
    @Schema(description = "密码")
    private String password;
}
