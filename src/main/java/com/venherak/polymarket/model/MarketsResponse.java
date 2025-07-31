package com.venherak.polymarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Wrapper class for the Polymarket markets API response.
 */
public class MarketsResponse {
    @JsonProperty("data")
    private List<Market> data;
    
    @JsonProperty("next_cursor")
    private String nextCursor;
    
    public List<Market> getData() {
        return data;
    }
    
    public void setData(List<Market> data) {
        this.data = data;
    }
    
    public String getNextCursor() {
        return nextCursor;
    }
    
    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}