package com.venherak.polymarket.service;

import com.venherak.polymarket.entity.MarketEntity;
import com.venherak.polymarket.model.Market;
import com.venherak.polymarket.model.MarketsResponse;
import com.venherak.polymarket.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service class for interacting with the Polymarket API and managing market data.
 */
@Service
public class PolymarketService {
    private static final Logger logger = Logger.getLogger(PolymarketService.class.getName());

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final MarketRepository marketRepository;
    private final MarketMappingService marketMappingService;

    public PolymarketService(
            RestTemplate restTemplate,
            @Value("${polymarket.api.base-url:https://clob.polymarket.com}") String baseUrl,
            MarketRepository marketRepository,
            MarketMappingService marketMappingService) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.marketRepository = marketRepository;
        this.marketMappingService = marketMappingService;
    }

    /**
     * Fetches markets from the Polymarket API.
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
    
    /**
     * Fetches markets from the Polymarket API and saves them to the database.
     * This method will fetch all pages of markets by following the cursor.
     * 
     * @return The total number of markets saved
     */
    @Transactional
    public int fetchAndSaveAllMarkets() {
        String cursor = null;
        List<Market> allMarkets = new ArrayList<>();
        int totalSaved = 0;
        
        do {
            try {
                MarketsResponse response = getMarkets(cursor);
                if (response == null || response.getData() == null || response.getData().isEmpty()) {
                    break;
                }
                
                allMarkets.addAll(response.getData());
                cursor = response.getNextCursor();
                
                // Save this batch of markets
                int saved = saveMarkets(response.getData());
                totalSaved += saved;
                
                logger.info(String.format("Saved %d markets from batch, total saved: %d", saved, totalSaved));
                
                // If there's no next cursor, we've reached the end
                if (cursor == null || cursor.isEmpty()) {
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
     * Saves a list of markets to the database.
     * 
     * @param markets The list of markets to save
     * @return The number of markets saved
     */
    @Transactional
    public int saveMarkets(List<Market> markets) {
        if (markets == null || markets.isEmpty()) {
            return 0;
        }
        
        try {
            List<MarketEntity> entities = marketMappingService.toEntities(markets);
            List<MarketEntity> savedEntities = marketRepository.saveAll(entities);
            return savedEntities.size();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving markets: " + e.getMessage(), e);
            return 0;
        }
    }
    
    /**
     * Retrieves all markets from the database.
     * 
     * @return List of Market objects
     */
    public List<Market> getAllMarketsFromDatabase() {
        List<MarketEntity> entities = marketRepository.findAll();
        return marketMappingService.toModels(entities);
    }
    
    /**
     * Retrieves active markets from the database.
     * 
     * @return List of active Market objects
     */
    public List<Market> getActiveMarketsFromDatabase() {
        List<MarketEntity> entities = marketRepository.findByActiveTrue();
        return marketMappingService.toModels(entities);
    }
}