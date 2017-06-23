package com.app.listtestwithapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quotes {

    @SerializedName("result")
    private List<ResultItem> result;

    @SerializedName("limit")
    private int limit;

    @SerializedName("total_rec")
    private String totalRec;

    @SerializedName("page")
    private int page;

    public void setResult(List<ResultItem> result) {
        this.result = result;
    }

    public List<ResultItem> getResult() {
        return result;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setTotalRec(String totalRec) {
        this.totalRec = totalRec;
    }

    public String getTotalRec() {
        return totalRec;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    @Override
    public String toString() {
        return
                "Quotes{" +
                        "result = '" + result + '\'' +
                        ",limit = '" + limit + '\'' +
                        ",total_rec = '" + totalRec + '\'' +
                        ",page = '" + page + '\'' +
                        "}";
    }
}