package com.venherak.polymarket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venherak.polymarket.entity.MarketEntity;
import com.venherak.polymarket.model.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service for mapping between Market model and MarketEntity JPA entity.
 */
@Service
public class MarketMappingService {
    
    private static final Logger logger = Logger.getLogger(MarketMappingService.class.getName());
    
    private final ObjectMapper objectMapper;
    
    @Autowired
    public MarketMappingService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    /**
     * Convert a Market model to a MarketEntity for database storage.
     * 
     * @param market the Market model from API
     * @return the MarketEntity for database
     */
    public MarketEntity toEntity(Market market) {
        if (market == null) {
            return null;
        }
        
        MarketEntity entity = new MarketEntity();
        
        // Map simple fields
        entity.setConditionId(market.getConditionId());
        entity.setQuestionId(market.getQuestionId());
        entity.setQuestion(market.getQuestion());
        entity.setDescription(market.getDescription());
        entity.setMarketSlug(market.getMarketSlug());
        entity.setFpmm(market.getFpmm());
        entity.setSecondsDelay(market.getSecondsDelay());
        entity.setMinimumOrderSize(market.getMinimumOrderSize());
        entity.setMinimumTickSize(market.getMinimumTickSize());
        entity.setMakerBaseFee(market.getMakerBaseFee());
        entity.setTakerBaseFee(market.getTakerBaseFee());
        
        // Map boolean fields
        entity.setActive(market.isActive());
        entity.setClosed(market.isClosed());
        entity.setArchived(market.isArchived());
        entity.setAcceptingOrders(market.isAcceptingOrders());
        entity.setEnableOrderBook(market.isEnableOrderBook());
        entity.setNotificationsEnabled(market.isNotificationsEnabled());
        entity.setNegRisk(market.isNegRisk());
        entity.setIs5050Outcome(market.isIs5050Outcome());
        
        // Map string IDs
        entity.setNegRiskMarketId(market.getNegRiskMarketId());
        entity.setNegRiskRequestId(market.getNegRiskRequestId());
        
        // Convert date strings to OffsetDateTime
        try {
            if (market.getEndDateIso() != null && !market.getEndDateIso().isEmpty()) {
                entity.setEndDate(OffsetDateTime.parse(market.getEndDateIso()));
            }
            
            if (market.getGameStartTime() != null && !market.getGameStartTime().isEmpty()) {
                entity.setGameStartTime(OffsetDateTime.parse(market.getGameStartTime()));
            }
            
            if (market.getAcceptingOrderTimestamp() != null && !market.getAcceptingOrderTimestamp().isEmpty()) {
                entity.setAcceptingOrderTimestamp(OffsetDateTime.parse(market.getAcceptingOrderTimestamp()));
            }
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing date: " + e.getMessage(), e);
        }
        
        // Convert complex objects to JSON strings
        try {
            if (market.getTags() != null) {
                entity.setTagsJson(objectMapper.writeValueAsString(market.getTags()));
            }
            
            if (market.getTokens() != null) {
                entity.setTokensJson(objectMapper.writeValueAsString(market.getTokens()));
            }
            
            if (market.getRewards() != null) {
                entity.setRewardsJson(objectMapper.writeValueAsString(market.getRewards()));
            }
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error serializing to JSON: " + e.getMessage(), e);
        }
        
        return entity;
    }
    
    /**
     * Convert a list of Market models to a list of MarketEntity objects.
     * 
     * @param markets the list of Market models
     * @return the list of MarketEntity objects
     */
    public List<MarketEntity> toEntities(List<Market> markets) {
        if (markets == null) {
            return new ArrayList<>();
        }
        
        List<MarketEntity> entities = new ArrayList<>(markets.size());
        for (Market market : markets) {
            MarketEntity entity = toEntity(market);
            if (entity != null) {
                entities.add(entity);
            }
        }
        
        return entities;
    }
    
    /**
     * Convert a MarketEntity from the database to a Market model.
     * 
     * @param entity the MarketEntity from database
     * @return the Market model
     */
    public Market toModel(MarketEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Market market = new Market();
        
        // Map simple fields
        market.setConditionId(entity.getConditionId());
        market.setQuestionId(entity.getQuestionId());
        market.setQuestion(entity.getQuestion());
        market.setDescription(entity.getDescription());
        market.setMarketSlug(entity.getMarketSlug());
        market.setFpmm(entity.getFpmm());
        market.setSecondsDelay(entity.getSecondsDelay());
        market.setMinimumOrderSize(entity.getMinimumOrderSize());
        market.setMinimumTickSize(entity.getMinimumTickSize());
        market.setMakerBaseFee(entity.getMakerBaseFee());
        market.setTakerBaseFee(entity.getTakerBaseFee());
        
        // Map boolean fields
        market.setActive(entity.getActive());
        market.setClosed(entity.getClosed());
        market.setArchived(entity.getArchived());
        market.setAcceptingOrders(entity.getAcceptingOrders());
        market.setEnableOrderBook(entity.getEnableOrderBook());
        market.setNotificationsEnabled(entity.getNotificationsEnabled());
        market.setNegRisk(entity.getNegRisk());
        market.setIs5050Outcome(entity.getIs5050Outcome());
        
        // Map string IDs
        market.setNegRiskMarketId(entity.getNegRiskMarketId());
        market.setNegRiskRequestId(entity.getNegRiskRequestId());
        
        // Convert OffsetDateTime to strings
        if (entity.getEndDate() != null) {
            market.setEndDateIso(entity.getEndDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        
        if (entity.getGameStartTime() != null) {
            market.setGameStartTime(entity.getGameStartTime().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        
        if (entity.getAcceptingOrderTimestamp() != null) {
            market.setAcceptingOrderTimestamp(entity.getAcceptingOrderTimestamp().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        
        // Convert JSON strings to complex objects
        try {
            if (entity.getTagsJson() != null && !entity.getTagsJson().isEmpty()) {
                market.setTags(objectMapper.readValue(entity.getTagsJson(), 
                        objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)));
            }
            
            if (entity.getTokensJson() != null && !entity.getTokensJson().isEmpty()) {
                market.setTokens(objectMapper.readValue(entity.getTokensJson(), 
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Market.Token.class)));
            }
            
            if (entity.getRewardsJson() != null && !entity.getRewardsJson().isEmpty()) {
                market.setRewards(objectMapper.readValue(entity.getRewardsJson(), Market.Rewards.class));
            }
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error deserializing from JSON: " + e.getMessage(), e);
        }
        
        return market;
    }
    
    /**
     * Convert a list of MarketEntity objects to a list of Market models.
     * 
     * @param entities the list of MarketEntity objects
     * @return the list of Market models
     */
    public List<Market> toModels(List<MarketEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        
        List<Market> markets = new ArrayList<>(entities.size());
        for (MarketEntity entity : entities) {
            Market market = toModel(entity);
            if (market != null) {
                markets.add(market);
            }
        }
        
        return markets;
    }
}