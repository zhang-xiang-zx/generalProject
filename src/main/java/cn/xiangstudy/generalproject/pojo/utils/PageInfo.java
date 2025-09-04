package cn.xiangstudy.generalproject.pojo.utils;

import java.util.List;

/**
 * 分页包装类
 * @author zhangxiang
 * @date 2025-09-04 14:07
 */
public class PageInfo<T> {
    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;
    private boolean hasNextPage;
    private List<T> list;

    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.hasNextPage = page.getHasNextPage();
            this.list = page;
        } else {
            // 普通 List，兜底处理
            this.pageNum = 1;
            this.pageSize = list.size();
            this.total = list.size();
            this.pages = 1;
            this.list = list;
        }
    }

    // getter
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

    public List<T> getList() {
        return list;
    }
}


