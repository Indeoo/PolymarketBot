package com.venherak.polymarket.runner;

import com.venherak.polymarket.service.PolymarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Command line runner that initializes market data by fetching from the API
 * and saving to the database on application startup.
 * Only runs when the "init-data" profile is active.
 */
@Component
public class MarketDataInitializer implements CommandLineRunner {
    
    private static final Logger logger = Logger.getLogger(MarketDataInitializer.class.getName());
    
    private final PolymarketService polymarketService;
    
    @Autowired
    public MarketDataInitializer(PolymarketService polymarketService) {
        this.polymarketService = polymarketService;
    }
    
    @Override
    public void run(String... args) {
        logger.info("Starting to fetch and save market data...");
        
        try {
            int totalSaved = polymarketService.fetchAndSaveAllMarkets();
            logger.info("Successfully saved " + totalSaved + " markets to the database");
        } catch (Exception e) {
            logger.severe("Error initializing market data: " + e.getMessage());
            e.printStackTrace();
        }
        
        logger.info("Market data initialization completed");
    }
}