package cn.xiangstudy.generalproject.pojo.dto;

import cn.xiangstudy.generalproject.config.annotation.FieldValueNotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2025-09-04 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(description = "用户角色")
public class UserRoleDTO {

    @FieldValueNotNull
    private Long userId;

    @FieldValueNotNull
    private Long roleId;
}
