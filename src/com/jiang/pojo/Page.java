package com.jiang.pojo;

import java.util.List;

/**
 * @author jiangboss
 * @create 2021-05-20-8:49
 */
public class Page<T> {
    public static final Integer PAGE_SIZE=4;
    private Integer pageNo; //当前页码
    private Integer pageTotal;//总页码
    private Integer pageSize=PAGE_SIZE;//每页的大小
    private List<Book> items;//每页显示的数据
    private Integer pageTotalCount;//总记录数
    private  String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, List<Book> items, Integer pageTotalCount) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.items = items;
        this.pageTotalCount = pageTotalCount;
    }
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo<1){
            pageNo=1;
        }if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        this.pageNo=pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Book> getItems() {
        return items;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", pageTotalCount=" + pageTotalCount +
                '}';
    }
}
