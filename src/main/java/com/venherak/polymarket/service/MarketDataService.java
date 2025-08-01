package com.venherak.polymarket.service;

import com.venherak.polymarket.infrastructure.persistence.document.MarketDocument;
import com.venherak.polymarket.mapper.MarketMapper;
import com.venherak.polymarket.domain.model.Market;
import com.venherak.polymarket.infrastructure.persistence.repository.MarketDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service for managing market data persistence and retrieval using Elasticsearch.
 * Single responsibility: Elasticsearch operations and data management.
 */
@Service
public class MarketDataService {
    
    private static final Logger logger = Logger.getLogger(MarketDataService.class.getName());
    
    private final MarketDocumentRepository marketDocumentRepository;
    private final MarketMapper marketMapper;

    public MarketDataService(
            MarketDocumentRepository marketDocumentRepository,
            MarketMapper marketMapper) {
        this.marketDocumentRepository = marketDocumentRepository;
        this.marketMapper = marketMapper;
    }

    /**
     * Saves a list of markets to Elasticsearch.
     * 
     * @param markets The list of markets to save
     * @return The number of markets saved
     */
    public int saveMarkets(List<Market> markets) {
        if (markets == null || markets.isEmpty()) {
            return 0;
        }
        
        try {
            List<MarketDocument> documents = marketMapper.toDocuments(markets);
            Iterable<MarketDocument> savedDocuments = marketDocumentRepository.saveAll(documents);
            
            // Count saved documents
            int count = 0;
            for (MarketDocument doc : savedDocuments) {
                count++;
            }
            
            return count;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving markets to Elasticsearch: " + e.getMessage(), e);
            return 0;
        }
    }
    
    /**
     * Retrieves all markets from Elasticsearch.
     * 
     * @return List of Market objects
     */
    public List<Market> getAllMarkets() {
        Iterable<MarketDocument> documents = marketDocumentRepository.findAll();
        List<MarketDocument> documentList = new java.util.ArrayList<>();
        documents.forEach(documentList::add);
        return marketMapper.toModels(documentList);
    }
    
    /**
     * Retrieves active markets from Elasticsearch.
     * 
     * @return List of active Market objects
     */
    public List<Market> getActiveMarkets() {
        List<MarketDocument> documents = marketDocumentRepository.findByActiveTrue();
        return marketMapper.toModels(documents);
    }
    
    /**
     * Gets the total count of markets in Elasticsearch.
     * 
     * @return Total number of markets
     */
    public long getTotalMarketCount() {
        return marketDocumentRepository.count();
    }
    
    /**
     * Gets the count of active markets in Elasticsearch.
     * 
     * @return Number of active markets
     */
    public long getActiveMarketCount() {
        return marketDocumentRepository.countByActiveTrue();
    }
    
    /**
     * Search markets by question text.
     * 
     * @param questionText the text to search for in questions
     * @return List of markets matching the search
     */
    public List<Market> searchMarketsByQuestion(String questionText) {
        List<MarketDocument> documents = marketDocumentRepository.findByQuestionContaining(questionText);
        return marketMapper.toModels(documents);
    }
    
    /**
     * Search markets by description text.
     * 
     * @param descriptionText the text to search for in descriptions
     * @return List of markets matching the search
     */
    public List<Market> searchMarketsByDescription(String descriptionText) {
        List<MarketDocument> documents = marketDocumentRepository.findByDescriptionContaining(descriptionText);
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get active and open markets (active=true, closed=false).
     * 
     * @return List of active and open markets
     */
    public List<Market> getActiveOpenMarkets() {
        List<MarketDocument> documents = marketDocumentRepository.findByActiveTrueAndClosedFalse();
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get all markets that have rewards.
     * 
     * @return List of markets with rewards
     */
    public List<Market> getMarketsWithRewards() {
        List<MarketDocument> documents = marketDocumentRepository.findByRewardsIsNotNull();
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get markets with high reward multipliers.
     * 
     * @param minMultiplier minimum multiplier value
     * @return List of markets with reward multipliers above the threshold
     */
    public List<Market> getMarketsWithHighRewardMultiplier(double minMultiplier) {
        List<MarketDocument> documents = marketDocumentRepository.findByRewardsInGameMultiplierGreaterThan(minMultiplier);
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get active markets that have rewards.
     * 
     * @return List of active markets with rewards
     */
    public List<Market> getActiveMarketsWithRewards() {
        List<MarketDocument> documents = marketDocumentRepository.findByActiveTrueAndRewardsIsNotNull();
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get markets by reward epoch.
     * 
     * @param epoch the reward epoch
     * @return List of markets in the specified reward epoch
     */
    public List<Market> getMarketsByRewardEpoch(int epoch) {
        List<MarketDocument> documents = marketDocumentRepository.findByRewardsRewardEpoch(epoch);
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get markets with specific tag.
     * 
     * @param tag the tag to search for
     * @return List of markets with the specified tag
     */
    public List<Market> getMarketsByTag(String tag) {
        List<MarketDocument> documents = marketDocumentRepository.findByTagsContaining(tag);
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get markets with winning tokens.
     * 
     * @return List of markets that have winning tokens
     */
    public List<Market> getMarketsWithWinningTokens() {
        List<MarketDocument> documents = marketDocumentRepository.findByTokensWinnerTrue();
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get markets with high-priced tokens.
     * 
     * @param minPrice minimum token price
     * @return List of markets with tokens above the price threshold
     */
    public List<Market> getMarketsWithHighPricedTokens(double minPrice) {
        List<MarketDocument> documents = marketDocumentRepository.findByTokensPriceGreaterThan(minPrice);
        return marketMapper.toModels(documents);
    }
    
    /**
     * Get count of markets with rewards.
     * 
     * @return Number of markets that have rewards
     */
    public long getMarketsWithRewardsCount() {
        return marketDocumentRepository.countByRewardsIsNotNull();
    }
    
    /**
     * Get count of active markets with rewards.
     * 
     * @return Number of active markets that have rewards
     */
    public long getActiveMarketsWithRewardsCount() {
        return marketDocumentRepository.countByActiveTrueAndRewardsIsNotNull();
    }
}