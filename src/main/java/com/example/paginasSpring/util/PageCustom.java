package com.example.paginasSpring.util;

import java.util.List;

public class PageCustom<T> {
    private List<T> items;
    private Integer total;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
