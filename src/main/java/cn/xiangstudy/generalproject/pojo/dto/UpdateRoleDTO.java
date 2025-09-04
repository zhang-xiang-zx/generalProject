package cn.xiangstudy.generalproject.pojo.dto;

import cn.xiangstudy.generalproject.config.annotation.FieldValueNotNull;
import cn.xiangstudy.generalproject.config.annotation.UpdateFieldIsNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangxiang
 * @date 2025-08-27 17:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleDTO {

    @FieldValueNotNull
    private Long roleId;

    @UpdateFieldIsNull
    private String roleName;

    @UpdateFieldIsNull
    private String roleKey;
}
