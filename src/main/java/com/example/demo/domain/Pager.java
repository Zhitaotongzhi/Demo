package com.example.demo.domain;

import java.util.List;

public class Pager<T> {
    private int pagenum;
    private int size;
    private List<T> data;
    private long total;

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "pagenum=" + pagenum +
                ", size=" + size +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
