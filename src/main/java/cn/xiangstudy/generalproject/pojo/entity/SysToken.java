package cn.xiangstudy.generalproject.pojo.entity;

import cn.xiangstudy.generalproject.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 存储数据库中的信息
 * @author zhangxiang
 * @date 2025-07-22 11:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SysToken extends BaseEntity {

    @Schema(description = "主键ID")
    private Long tokenId;

    private Long userId;

    private String token;

    private Date expireTime;

}
