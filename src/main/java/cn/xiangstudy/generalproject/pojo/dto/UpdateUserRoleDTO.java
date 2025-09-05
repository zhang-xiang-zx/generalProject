package cn.xiangstudy.generalproject.pojo.dto;

import cn.xiangstudy.generalproject.config.annotation.FieldValueNotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2025-09-05 14:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(description = "修改用户角色")
public class UpdateUserRoleDTO {

    @FieldValueNotNull
    @Schema(description = "用户ID")
    private Long userId;

    @FieldValueNotNull
    @Schema(description = "角色ID")
    private Long roleId;
}
