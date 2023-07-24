package com.revature.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Page<E> {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private List<E> items;

    public Page() {
    }

    public Page(int pageNumber, int pageSize, int totalPages, int totalElements, List<E> items) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.items = items;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<E> getItems() {
        return this.items;
    }

    public void setElements(List<E> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Page)) {
            return false;
        }
        Page page = (Page) o;
        return pageNumber == page.pageNumber && pageSize == page.pageSize && totalPages == page.totalPages && totalElements == page.totalElements && Arrays.equals(items.toArray(), page.items.toArray());
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize, totalPages, totalElements, items);
    }
}
