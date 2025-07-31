package com.venherak.polymarket.service;

import com.venherak.polymarket.Polymarket.Bot.model.MarketsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PolymarketServiceTest {

    @Autowired
    private PolymarketService polymarketService;

    @Test
    public void testGetMarkets() {
        // Test with the specific cursor from the requirement
        MarketsResponse response = polymarketService.getMarkets("MTAwMA==");
        
        // Verify response is not null
        assertNotNull(response, "Markets response should not be null");
        
        // Print some information about the response
        System.out.println("[DEBUG_LOG] Markets fetched: " + 
            (response.getData() != null ? response.getData().size() : 0));
        System.out.println("[DEBUG_LOG] Next cursor: " + response.getNextCursor());
    }
}