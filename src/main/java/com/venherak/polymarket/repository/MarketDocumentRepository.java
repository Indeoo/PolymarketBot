package com.venherak.polymarket.repository;

import com.venherak.polymarket.document.MarketDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Elasticsearch repository for managing MarketDocument objects.
 * Provides search and CRUD operations for market data in Elasticsearch.
 */
@Repository
public interface MarketDocumentRepository extends ElasticsearchRepository<MarketDocument, String> {
    
    /**
     * Find a market by its condition ID.
     * 
     * @param conditionId the condition ID
     * @return the market document if found
     */
    Optional<MarketDocument> findByConditionId(String conditionId);
    
    /**
     * Find a market by its market slug.
     * 
     * @param marketSlug the market slug
     * @return the market document if found
     */
    Optional<MarketDocument> findByMarketSlug(String marketSlug);
    
    /**
     * Find all active markets.
     * 
     * @return list of active markets
     */
    List<MarketDocument> findByActiveTrue();
    
    /**
     * Find all markets that are not closed.
     * 
     * @return list of markets that are not closed
     */
    List<MarketDocument> findByClosedFalse();
    
    /**
     * Count all active markets.
     * 
     * @return count of active markets
     */
    long countByActiveTrue();
    
    /**
     * Search markets by question text.
     * 
     * @param question the question text to search for
     * @return list of markets matching the question
     */
    List<MarketDocument> findByQuestionContaining(String question);
    
    /**
     * Search markets by description text.
     * 
     * @param description the description text to search for
     * @return list of markets matching the description
     */
    List<MarketDocument> findByDescriptionContaining(String description);
    
    /**
     * Find markets by active status and closed status.
     * 
     * @param active whether the market is active
     * @param closed whether the market is closed
     * @return list of markets matching the criteria
     */
    List<MarketDocument> findByActiveTrueAndClosedFalse();
}