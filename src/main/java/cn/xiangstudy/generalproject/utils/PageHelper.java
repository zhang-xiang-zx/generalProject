package cn.xiangstudy.generalproject.utils;

import cn.xiangstudy.generalproject.config.threadLocal.ContextKeys;
import cn.xiangstudy.generalproject.config.threadLocal.ContextManager;
import cn.xiangstudy.generalproject.pojo.utils.Page;
import cn.xiangstudy.generalproject.pojo.utils.PageInfo;

import java.util.List;

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

    /**
     * 重新赋值list
     * @author zhangxiang
     * @date 2025/9/5 15:41
     * @param oldPage
     * @param list
     * @return cn.xiangstudy.generalproject.pojo.utils.Page<E>
     */
    public static <E> PageInfo<E> edit(Page<E> oldPage, List<E> list) {
        Page<E> page = new Page(oldPage.getPageNum(), oldPage.getPageSize());
        page.addAll(list);
        page.setTotal(oldPage.getTotal());
        page.setHasNextPage(oldPage.getHasNextPage());
        return new PageInfo<>(page);
    }

}
