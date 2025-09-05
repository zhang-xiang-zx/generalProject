package cn.xiangstudy.generalproject.pojo.vo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2025-07-24 16:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(description = "用户信息")
public class UserVO {

    @Schema(description = "Id")
    private Long userId;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "账号")
    private String account;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "角色")
    private String roleName;

    @Schema(description = "角色Key")
    private String roleKey;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "备注")
    private String remark;
}
