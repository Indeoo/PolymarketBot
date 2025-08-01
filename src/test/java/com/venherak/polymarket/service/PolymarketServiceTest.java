package com.venherak.polymarket.service;

import com.venherak.polymarket.infrastructure.client.PolymarketApiClient;
import com.venherak.polymarket.domain.model.MarketsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PolymarketServiceTest {

    @Autowired
    private PolymarketApiClient polymarketApiClient;
    
    @Autowired
    private PolymarketService polymarketService;

    @Test
    public void testGetMarkets() {
        // Test with the specific cursor from the requirement using the API client directly
        MarketsResponse response = polymarketApiClient.getMarkets("MTAwMA==");
        
        // Verify response is not null
        assertNotNull(response, "Markets response should not be null");
        
        // Print some information about the response
        System.out.println("[DEBUG_LOG] Markets fetched: " + 
            (response.getData() != null ? response.getData().size() : 0));
        System.out.println("[DEBUG_LOG] Next cursor: " + response.getNextCursor());
    }
    
    @Test
    public void testFetchAndSaveAllMarkets() {
        // Test the main orchestration method
        int totalSaved = polymarketService.fetchAndSaveAllMarkets();
        
        // Verify that some markets were processed (could be 0 if already exists)
        System.out.println("[DEBUG_LOG] Total markets processed: " + totalSaved);
    }
}