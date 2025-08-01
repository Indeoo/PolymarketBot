package com.venherak.polymarket.repository;

import com.venherak.polymarket.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing MarketEntity objects in the database.
 */
@Repository
public interface MarketRepository extends JpaRepository<MarketEntity, Long> {
    
    /**
     * Find a market by its condition ID.
     * 
     * @param conditionId the condition ID
     * @return the market entity if found
     */
    Optional<MarketEntity> findByConditionId(String conditionId);
    
    /**
     * Find a market by its market slug.
     * 
     * @param marketSlug the market slug
     * @return the market entity if found
     */
    Optional<MarketEntity> findByMarketSlug(String marketSlug);
    
    /**
     * Find all active markets.
     * 
     * @return list of active markets
     */
    List<MarketEntity> findByActiveTrue();
    
    /**
     * Find all markets that are not closed.
     * 
     * @return list of markets that are not closed
     */
    List<MarketEntity> findByClosedFalse();
    
    /**
     * Count all active markets.
     * 
     * @return count of active markets
     */
    long countByActiveTrue();
}