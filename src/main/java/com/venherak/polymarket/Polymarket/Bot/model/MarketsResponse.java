package com.venherak.polymarket.Polymarket.Bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Wrapper class for the Polymarket markets API response.
 */
public class MarketsResponse {
    @JsonProperty("markets")
    private List<Market> markets;
    
    @JsonProperty("next_cursor")
    private String nextCursor;
    
    public List<Market> getMarkets() {
        return markets;
    }
    
    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }
    
    public String getNextCursor() {
        return nextCursor;
    }
    
    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}