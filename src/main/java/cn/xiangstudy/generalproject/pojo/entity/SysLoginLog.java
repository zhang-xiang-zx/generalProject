package cn.xiangstudy.generalproject.pojo.entity;

import cn.xiangstudy.generalproject.pojo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2025-08-27 11:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SysLoginLog extends BaseEntity {

    private Long loginLogId;

    private Long userId;

    private String loginIp;
}
