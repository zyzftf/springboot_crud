package com.example.school.util;

import java.util.List;


public class Page<T> {

    // 當前頁碼
    private Integer currentPage;

    //  總數據數量
    private Integer totalPageCount;

    // 總頁碼數量
    private Integer totalPages;

    // 當前頁面數據
    private List<T> result;

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
