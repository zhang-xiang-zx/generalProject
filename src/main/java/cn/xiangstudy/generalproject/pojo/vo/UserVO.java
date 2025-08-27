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
@Schema(name = "用户信息")
public class UserVO {

    @Schema(name = "Id")
    private Long userId;

    @Schema(name = "昵称")
    private String nickname;

    @Schema(name = "账号")
    private String account;

    @Schema(name = "手机号")
    private String phone;

    @Schema(name = "状态")
    private Integer status;

    @Schema(name = "创建时间")
    private Date createTime;

    @Schema(name = "备注")
    private String remark;
}
