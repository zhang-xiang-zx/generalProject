package cn.xiangstudy.generalproject.utils;

import cn.xiangstudy.generalproject.config.threadLocal.ContextKeys;
import cn.xiangstudy.generalproject.config.threadLocal.ContextManager;
import cn.xiangstudy.generalproject.pojo.utils.Page;

/**
 * 分页工具
 * @author zhangxiang
 * @date 2025-09-04 11:34
 */
public class PageHelper {

    /**
     * 分页上下文
     * @author zhangxiang
     * @date 2025/9/4 14:22
     * @param pageNum
     * @param pageSize
     */
    public static void startPage(int pageNum, int pageSize) {

        Page<Object> page = new Page<>(pageNum, pageSize);

        ContextManager.set(ContextKeys.PAGE, page);
    }

}
