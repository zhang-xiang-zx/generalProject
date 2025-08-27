package cn.xiangstudy.generalproject.pojo.entity;

import cn.xiangstudy.generalproject.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 日志
 * @author zhangxiang
 * @date 2025-08-26 14:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SysLog extends BaseEntity {

    @Schema(name = "注解ID")
    private Long logId;

    @Schema(name = "客户端IP")
    private String ip;

    @Schema(name = "请求地址")
    private String requestUri;

    @Schema(name = "用户ID")
    private Long userId;

    @Schema(name = "类型")
    private Integer type;

    @Schema(name = "0:请求失败, 1:请求成功")
    private Integer isSuccess;

    @Schema(name = "请求失败信息")
    private String errorMsg;
}
