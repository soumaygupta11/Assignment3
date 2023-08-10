package com.navi.Assignment3.Response;

import java.util.List;

public class Response {
    private String status;
    private int totalResults;
    private List<ResponseArticles> articles;

    public Response(String status, int totalResults, List<ResponseArticles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ResponseArticles> getArticles() {
        return articles;
    }

    public void setArticles(List<ResponseArticles> articles) {
        this.articles = articles;
    }
}
