package cn.xiangstudy.generalproject.pojo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息
 * @author zhangxiang
 * @date 2025-09-04 11:37
 */
public class Page<E> extends ArrayList<E> {
    private int pageNum;    // 当前页
    private int pageSize;   // 每页大小
    private long total;     // 总数
    private int pages;      // 总页数
    private boolean hasNextPage;    // 是否还有下一页

    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public void setTotal(long total) {
        this.total = total;
        this.pages = (int) Math.ceil((double) total / pageSize);
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotal() {
        return total;
    }

    public int getPages() {
        return pages;
    }

    public boolean getHasNextPage() {
        return hasNextPage;
    }

    public static <E> Page<E> of(List<E> list, int pageNum, int pageSize, long total, boolean hasNextPage) {
        Page<E> page = new Page(pageNum, pageSize);
        page.addAll(list);
        page.setTotal(total);
        page.setHasNextPage(hasNextPage);
        return page;
    }

}
