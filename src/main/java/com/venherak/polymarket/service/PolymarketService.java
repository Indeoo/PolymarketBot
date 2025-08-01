package com.venherak.polymarket.service;

import com.venherak.polymarket.client.PolymarketApiClient;
import com.venherak.polymarket.model.Market;
import com.venherak.polymarket.model.MarketsResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service class for orchestrating market data synchronization.
 * Single responsibility: Business logic and coordination between API and data layers.
 */
@Service
public class PolymarketService {
    private static final Logger logger = Logger.getLogger(PolymarketService.class.getName());

    private final PolymarketApiClient apiClient;
    private final MarketDataService marketDataService;

    public PolymarketService(
            PolymarketApiClient apiClient,
            MarketDataService marketDataService) {
        this.apiClient = apiClient;
        this.marketDataService = marketDataService;
    }

    
    /**
     * Fetches markets from the Polymarket API and saves them to the database.
     * This method will fetch all pages of markets by following the cursor.
     * 
     * @return The total number of markets saved
     */
    public int fetchAndSaveAllMarkets() {
        String cursor = null;
        int totalSaved = 0;
        
        do {
            try {
                MarketsResponse response = apiClient.getMarkets(cursor);
                if (response == null || response.getData() == null || response.getData().isEmpty()) {
                    logger.info("No more markets available. Pagination complete.");
                    break;
                }
                
                cursor = response.getNextCursor();
                
                // Save this batch of markets
                int saved = marketDataService.saveMarkets(response.getData());
                totalSaved += saved;
                int batchSize = response.getData().size();
                
                logger.info(String.format("Saved %d markets from batch, total saved: %d", saved, totalSaved));
                
                // If less than 500 markets returned, we've reached the end of available data
                if (batchSize < 500) {
                    logger.info(String.format("Received %d markets (less than 500). All available markets have been fetched.", batchSize));
                    break;
                }
                
                // If there's no next cursor, we've reached the end
                if (cursor == null || cursor.isEmpty()) {
                    logger.info("No next cursor available. Pagination complete.");
                    break;
                }
                
                // Small delay to avoid hitting rate limits
                Thread.sleep(100);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error fetching or saving markets: " + e.getMessage(), e);
                break;
            }
        } while (cursor != null);
        
        logger.info("Completed fetching and saving markets. Total saved: " + totalSaved);
        return totalSaved;
    }
    
    /**
     * Retrieves all markets from the database.
     * Delegates to MarketDataService.
     * 
     * @return List of Market objects
     */
    public List<Market> getAllMarkets() {
        return marketDataService.getAllMarkets();
    }
    
    /**
     * Retrieves active markets from the database.
     * Delegates to MarketDataService.
     * 
     * @return List of active Market objects
     */
    public List<Market> getActiveMarkets() {
        return marketDataService.getActiveMarkets();
    }
    
    /**
     * Gets the total count of markets in the database.
     * Delegates to MarketDataService.
     * 
     * @return Total number of markets
     */
    public long getTotalMarketCount() {
        return marketDataService.getTotalMarketCount();
    }
    
    /**
     * Gets the count of active markets in the database.
     * Delegates to MarketDataService.
     * 
     * @return Number of active markets
     */
    public long getActiveMarketCount() {
        return marketDataService.getActiveMarketCount();
    }
}