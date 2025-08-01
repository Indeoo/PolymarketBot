package com.venherak.polymarket.service;

import com.venherak.polymarket.entity.MarketEntity;
import com.venherak.polymarket.model.Market;
import com.venherak.polymarket.repository.MarketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service for managing market data persistence and retrieval.
 * Single responsibility: Database operations and data management.
 */
@Service
public class MarketDataService {
    
    private static final Logger logger = Logger.getLogger(MarketDataService.class.getName());
    
    private final MarketRepository marketRepository;
    private final MarketMappingService marketMappingService;

    public MarketDataService(
            MarketRepository marketRepository,
            MarketMappingService marketMappingService) {
        this.marketRepository = marketRepository;
        this.marketMappingService = marketMappingService;
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
    public List<Market> getAllMarkets() {
        List<MarketEntity> entities = marketRepository.findAll();
        return marketMappingService.toModels(entities);
    }
    
    /**
     * Retrieves active markets from the database.
     * 
     * @return List of active Market objects
     */
    public List<Market> getActiveMarkets() {
        List<MarketEntity> entities = marketRepository.findByActiveTrue();
        return marketMappingService.toModels(entities);
    }
    
    /**
     * Gets the total count of markets in the database.
     * 
     * @return Total number of markets
     */
    public long getTotalMarketCount() {
        return marketRepository.count();
    }
    
    /**
     * Gets the count of active markets in the database.
     * 
     * @return Number of active markets
     */
    public long getActiveMarketCount() {
        return marketRepository.countByActiveTrue();
    }
}