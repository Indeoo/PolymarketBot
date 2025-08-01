package com.venherak.polymarket.client;

import com.venherak.polymarket.model.MarketsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Client for communicating with the Polymarket API.
 * Handles HTTP requests and response parsing.
 * Single responsibility: API communication only.
 */
@Component
public class PolymarketApiClient {
    
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PolymarketApiClient(
            RestTemplate restTemplate,
            @Value("${polymarket.api.base-url:https://clob.polymarket.com}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Fetches markets from the Polymarket API with pagination.
     * 
     * @param cursor Optional cursor for pagination (can be null for first page)
     * @return MarketsResponse containing the list of markets and next cursor
     */
    public MarketsResponse getMarkets(String cursor) {
        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(baseUrl + "/markets");
        
        if (cursor != null && !cursor.isEmpty()) {
            builder.queryParam("next_cursor", cursor);
        }
        
        String url = builder.build().toUriString();
        return restTemplate.getForObject(url, MarketsResponse.class);
    }
    
    /**
     * Fetches the first page of markets from the Polymarket API.
     * 
     * @return MarketsResponse containing the list of markets and next cursor
     */
    public MarketsResponse getMarkets() {
        return getMarkets(null);
    }
}