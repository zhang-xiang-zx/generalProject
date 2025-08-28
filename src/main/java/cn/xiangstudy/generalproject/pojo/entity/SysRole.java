package cn.xiangstudy.generalproject.pojo.entity;

import cn.xiangstudy.generalproject.config.annotation.FieldValueNotNull;
import cn.xiangstudy.generalproject.pojo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2025-08-27 17:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SysRole extends BaseEntity {

    private Long roleId;

    @FieldValueNotNull
    private String roleName;

    @FieldValueNotNull
    private String roleKey;

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                '}' + super.toString();
    }
}
