package cn.xiangstudy.generalproject.pojo;

import cn.xiangstudy.generalproject.pojo.entity.SysLog;

/**
 * 日志上下文
 * @author zhangxiang
 * @date 2025-08-27 10:08
 */
public class LogContext {
    private static final ThreadLocal<SysLog> logLocal = new ThreadLocal<>();

    public static void set(SysLog sysLog) {
        logLocal.set(sysLog);
    }

    public static SysLog get() {
        return logLocal.get();
    }

    public static void remove() {
        logLocal.remove();
    }
}
