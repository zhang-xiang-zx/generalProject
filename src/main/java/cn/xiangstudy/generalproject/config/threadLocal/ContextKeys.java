package cn.xiangstudy.generalproject.config.threadLocal;

import cn.xiangstudy.generalproject.pojo.dto.PageDTO;
import cn.xiangstudy.generalproject.pojo.entity.SysLog;
import cn.xiangstudy.generalproject.pojo.utils.Page;

/**
 * 定义上下文key
 * @author zhangxiang
 * @date 2025-09-02 10:32
 */
public class ContextKeys {

    // 日志上下文
    public static final ContextKey<SysLog> SYS_LOG = ContextKey.of("sysLog", SysLog.class);

    // 分页上下文
    public static final ContextKey<PageDTO> PAGE_INFO = ContextKey.of("pageInfo", PageDTO.class);

    // 分页上下文
    public static final ContextKey<Page> PAGE = ContextKey.of("page", Page.class);
}
