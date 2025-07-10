package cn.xiangstudy.generalproject.pojo.entity;

import cn.xiangstudy.generalproject.pojo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2025-07-10 15:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends BaseEntity {

    private Long userId;

    private String nickname;

    private String account;

    private String password;

    private Integer status;

    private String phone;

}
