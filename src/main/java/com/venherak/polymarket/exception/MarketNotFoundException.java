package com.venherak.polymarket.exception;

/**
 * Exception thrown when a market is not found.
 */
public class MarketNotFoundException extends RuntimeException {
    
    public MarketNotFoundException(String message) {
        super(message);
    }
    
    public MarketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static MarketNotFoundException forConditionId(String conditionId) {
        return new MarketNotFoundException("Market not found for condition ID: " + conditionId);
    }
    
    public static MarketNotFoundException forMarketSlug(String marketSlug) {
        return new MarketNotFoundException("Market not found for slug: " + marketSlug);
    }
}