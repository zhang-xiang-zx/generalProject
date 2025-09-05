package cn.xiangstudy.generalproject.pojo.entity;

import cn.xiangstudy.generalproject.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2025-09-04 16:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(description = "用户角色")
public class SysUserRole extends BaseEntity {

    private Long userRoleId;

    private Long userId;

    private Long roleId;
}
