package com.leyou.common.dao;

import java.util.List;

/**
 * @ClassName PageResultResponse
 * @Description: 乐优分页对象
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/8 13:56
 * @Version V1.0
 **/
public class PageResultResp<T> {

    private Long total;

    private Integer totalPage;

    private List<T> items;

    public PageResultResp() {

    };

    public PageResultResp(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResultResp(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
