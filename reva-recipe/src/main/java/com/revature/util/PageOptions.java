package com.revature.util;

public class PageOptions {
    private int pageNumber;
    private int pageSize;
    private String sortBy;
    private String sortDirection;

    public PageOptions() {
        super();
    }

    public PageOptions(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortBy = "id";
        this.sortDirection = "asc";
    }

    public PageOptions(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        this(pageNumber, pageSize);
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
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

    public String getSortBy() {
        return this.sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return this.sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
    
}
