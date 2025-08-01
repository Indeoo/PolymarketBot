package com.venherak.polymarket.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venherak.polymarket.entity.MarketEntity;
import com.venherak.polymarket.model.Market;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MapStruct mapper for converting between Market model and MarketEntity.
 * Handles complex mappings like date conversions and JSON serialization.
 */
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class MarketMapper {
    
    private static final Logger logger = Logger.getLogger(MarketMapper.class.getName());
    
    @Autowired
    protected ObjectMapper objectMapper;

    // Mapping from Market to MarketEntity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "endDate", source = "endDateIso", qualifiedByName = "stringToOffsetDateTime")
    @Mapping(target = "gameStartTime", source = "gameStartTime", qualifiedByName = "stringToOffsetDateTime")
    @Mapping(target = "acceptingOrderTimestamp", source = "acceptingOrderTimestamp", qualifiedByName = "stringToOffsetDateTime")
    @Mapping(target = "tagsJson", source = "tags", qualifiedByName = "listToJson")
    @Mapping(target = "tokensJson", source = "tokens", qualifiedByName = "tokensToJson")
    @Mapping(target = "rewardsJson", source = "rewards", qualifiedByName = "rewardsToJson")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "closed", source = "closed")
    @Mapping(target = "archived", source = "archived")
    @Mapping(target = "acceptingOrders", source = "acceptingOrders")
    @Mapping(target = "enableOrderBook", source = "enableOrderBook")
    @Mapping(target = "notificationsEnabled", source = "notificationsEnabled")
    @Mapping(target = "negRisk", source = "negRisk")
    @Mapping(target = "is5050Outcome", source = "is5050Outcome")
    public abstract MarketEntity toEntity(Market market);

    // Mapping from MarketEntity to Market
    @Mapping(target = "endDateIso", source = "endDate", qualifiedByName = "offsetDateTimeToString")
    @Mapping(target = "gameStartTime", source = "gameStartTime", qualifiedByName = "offsetDateTimeToString")
    @Mapping(target = "acceptingOrderTimestamp", source = "acceptingOrderTimestamp", qualifiedByName = "offsetDateTimeToString")
    @Mapping(target = "tags", source = "tagsJson", qualifiedByName = "jsonToStringList")
    @Mapping(target = "tokens", source = "tokensJson", qualifiedByName = "jsonToTokenList")
    @Mapping(target = "rewards", source = "rewardsJson", qualifiedByName = "jsonToRewards")
    public abstract Market toModel(MarketEntity entity);

    // List mappings
    public abstract List<MarketEntity> toEntities(List<Market> markets);
    public abstract List<Market> toModels(List<MarketEntity> entities);

    // Custom mapping methods

    @Named("stringToOffsetDateTime")
    protected OffsetDateTime stringToOffsetDateTime(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        try {
            return OffsetDateTime.parse(dateString);
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing date: " + e.getMessage(), e);
            return null;
        }
    }

    @Named("offsetDateTimeToString")
    protected String offsetDateTimeToString(OffsetDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @Named("listToJson")
    protected String listToJson(List<String> list) {
        if (list == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error serializing list to JSON: " + e.getMessage(), e);
            return null;
        }
    }

    @Named("tokensToJson")
    protected String tokensToJson(List<Market.Token> tokens) {
        if (tokens == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(tokens);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error serializing tokens to JSON: " + e.getMessage(), e);
            return null;
        }
    }

    @Named("rewardsToJson")
    protected String rewardsToJson(Market.Rewards rewards) {
        if (rewards == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(rewards);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error serializing rewards to JSON: " + e.getMessage(), e);
            return null;
        }
    }

    @Named("jsonToStringList")
    protected List<String> jsonToStringList(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error deserializing JSON to string list: " + e.getMessage(), e);
            return null;
        }
    }

    @Named("jsonToTokenList")
    protected List<Market.Token> jsonToTokenList(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, Market.Token.class));
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error deserializing JSON to token list: " + e.getMessage(), e);
            return null;
        }
    }

    @Named("jsonToRewards")
    protected Market.Rewards jsonToRewards(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, Market.Rewards.class);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error deserializing JSON to rewards: " + e.getMessage(), e);
            return null;
        }
    }
}