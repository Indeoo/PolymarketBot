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
    
    /**
     * Find markets with non-null rewards.
     * 
     * @return list of markets that have rewards
     */
    List<MarketDocument> findByRewardsIsNotNull();
    
    /**
     * Find markets with in-game multiplier greater than specified value.
     * 
     * @param multiplier the minimum multiplier value
     * @return list of markets with high reward multipliers
     */
    List<MarketDocument> findByRewardsInGameMultiplierGreaterThan(Double multiplier);
    
    /**
     * Find markets with in-game multiplier between specified values.
     * 
     * @param minMultiplier minimum multiplier value
     * @param maxMultiplier maximum multiplier value
     * @return list of markets with multipliers in range
     */
    List<MarketDocument> findByRewardsInGameMultiplierBetween(Double minMultiplier, Double maxMultiplier);
    
    /**
     * Find active markets with rewards.
     * 
     * @return list of active markets that have rewards
     */
    List<MarketDocument> findByActiveTrueAndRewardsIsNotNull();
    
    /**
     * Find markets with minimum reward size greater than specified value.
     * 
     * @param minSize the minimum reward size
     * @return list of markets with high minimum reward sizes
     */
    List<MarketDocument> findByRewardsMinSizeGreaterThan(Integer minSize);
    
    /**
     * Find markets by reward epoch.
     * 
     * @param epoch the reward epoch
     * @return list of markets in the specified reward epoch
     */
    List<MarketDocument> findByRewardsRewardEpoch(Integer epoch);
    
    /**
     * Find markets with specific token outcome.
     * 
     * @param outcome the token outcome to search for
     * @return list of markets with tokens having the specified outcome
     */
    List<MarketDocument> findByTokensOutcome(String outcome);
    
    /**
     * Find markets with token price greater than specified value.
     * 
     * @param price the minimum token price
     * @return list of markets with high-priced tokens
     */
    List<MarketDocument> findByTokensPriceGreaterThan(Double price);
    
    /**
     * Find markets with winning tokens.
     * 
     * @return list of markets that have winning tokens
     */
    List<MarketDocument> findByTokensWinnerTrue();
    
    /**
     * Find markets by tag.
     * 
     * @param tag the tag to search for
     * @return list of markets with the specified tag
     */
    List<MarketDocument> findByTagsContaining(String tag);
    
    /**
     * Count markets with rewards.
     * 
     * @return number of markets that have rewards
     */
    long countByRewardsIsNotNull();
    
    /**
     * Count active markets with rewards.
     * 
     * @return number of active markets that have rewards
     */
    long countByActiveTrueAndRewardsIsNotNull();
}