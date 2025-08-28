package cn.xiangstudy.generalproject.pojo;

import cn.xiangstudy.generalproject.config.constant.SysConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2025-07-10 16:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEntity {

    @Builder.Default
    private Date createTime = new Date();

    @Builder.Default
    private Integer delFlag = SysConst.DEL_FLAG_FALSE;

    private String remark;
}
