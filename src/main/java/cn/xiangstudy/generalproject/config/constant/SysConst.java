package cn.xiangstudy.generalproject.config.constant;

/**
 * 基础常量
 * @author zhangxiang
 * @date 2025-07-28 17:02
 */
public class SysConst {

    /**
     * 删除
     */
    public static final int DEL_FLAG_TRUE = 1;

    /**
     * 没删除
     */
    public static final int DEL_FLAG_FALSE = 0;

    /**
     * 用户状态：正常
     */
    public static final int USER_STATUS_NORMAL = 0;

    /**
     * 用户状态：封禁
     */
    public static final int USER_STATUS_DISABLE = 1;

    /**
     * 密码加密盐值个数
     */
    public static final int PASSWORD_SLAT_NUM  = 6;

    /**
     * token加密盐值个数
     */
    public static final int TOKEN_SLAT_NUM  = 10;
}
