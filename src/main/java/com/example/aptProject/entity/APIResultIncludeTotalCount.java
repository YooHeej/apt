package com.example.aptProject.entity;

import java.util.List;

public class APIResultIncludeTotalCount {
    private List<APIResult> apiResults;
    private String totalCount;

    @Override
    public String toString() {
        return "APIResultIncludeTotalCount{" +
                "apiResults=" + apiResults +
                ", totalCount='" + totalCount + '\'' +
                '}';
    }

    public APIResultIncludeTotalCount() {
    }

    public List<APIResult> getApiResults() {
        return apiResults;
    }

    public void setApiResults(List<APIResult> apiResults) {
        this.apiResults = apiResults;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public APIResultIncludeTotalCount(List<APIResult> apiResults, String totalCount) {
        this.apiResults = apiResults;
        this.totalCount = totalCount;
    }
}
