package cn.xiangstudy.generalproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangxiang
 * @date 2025-07-10 16:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEntity implements Serializable {

    private Date createTime;

    private Integer delFlag;

    private String remark;
}
