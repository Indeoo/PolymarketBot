package com.venherak.polymarket.mapper;

import com.venherak.polymarket.document.MarketDocument;
import com.venherak.polymarket.document.RateDocument;
import com.venherak.polymarket.document.RewardsDocument;
import com.venherak.polymarket.document.TokenDocument;
import com.venherak.polymarket.model.Market;
import org.mapstruct.*;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MapStruct mapper for converting between Market model and MarketDocument.
 * Handles complex mappings like date conversions and JSON serialization.
 */
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class MarketMapper {
    
    private static final Logger logger = Logger.getLogger(MarketMapper.class.getName());
    

    // Mapping from Market to MarketDocument
    @Mapping(target = "id", expression = "java(generateDocumentId(market))")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.OffsetDateTime.now())")
    @Mapping(target = "endDate", source = "endDateIso", qualifiedByName = "stringToOffsetDateTime")
    @Mapping(target = "gameStartTime", source = "gameStartTime", qualifiedByName = "stringToOffsetDateTime")
    @Mapping(target = "acceptingOrderTimestamp", source = "acceptingOrderTimestamp", qualifiedByName = "stringToOffsetDateTime")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "tokens", source = "tokens", qualifiedByName = "tokensToDocuments")
    @Mapping(target = "rewards", source = "rewards", qualifiedByName = "rewardsToDocument")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "closed", source = "closed")
    @Mapping(target = "archived", source = "archived")
    @Mapping(target = "acceptingOrders", source = "acceptingOrders")
    @Mapping(target = "enableOrderBook", source = "enableOrderBook")
    @Mapping(target = "notificationsEnabled", source = "notificationsEnabled")
    @Mapping(target = "negRisk", source = "negRisk")
    @Mapping(target = "is5050Outcome", source = "is5050Outcome")
    public abstract MarketDocument toDocument(Market market);

    // Mapping from MarketDocument to Market
    @Mapping(target = "endDateIso", source = "endDate", qualifiedByName = "offsetDateTimeToString")
    @Mapping(target = "gameStartTime", source = "gameStartTime", qualifiedByName = "offsetDateTimeToString")
    @Mapping(target = "acceptingOrderTimestamp", source = "acceptingOrderTimestamp", qualifiedByName = "offsetDateTimeToString")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "tokens", source = "tokens", qualifiedByName = "documentsToTokens")
    @Mapping(target = "rewards", source = "rewards", qualifiedByName = "documentToRewards")
    public abstract Market toModel(MarketDocument document);

    // List mappings
    public abstract List<MarketDocument> toDocuments(List<Market> markets);
    public abstract List<Market> toModels(List<MarketDocument> documents);


    // Custom mapping methods
    
    /**
     * Generate a unique document ID for Elasticsearch.
     * Uses condition_id if available, otherwise market_slug, otherwise generates UUID.
     */
    protected String generateDocumentId(Market market) {
        if (market.getConditionId() != null && !market.getConditionId().isEmpty()) {
            return market.getConditionId();
        }
        if (market.getMarketSlug() != null && !market.getMarketSlug().isEmpty()) {
            return market.getMarketSlug();
        }
        return java.util.UUID.randomUUID().toString();
    }

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

    // Object mappings for nested types
    
    @Named("rewardsToDocument")
    protected RewardsDocument rewardsToDocument(Market.Rewards rewards) {
        if (rewards == null) {
            return null;
        }
        
        RewardsDocument document = new RewardsDocument();
        document.setMinSize(rewards.getMinSize());
        document.setMaxSpread(rewards.getMaxSpread());
        document.setInGameMultiplier(rewards.getInGameMultiplier());
        document.setRewardEpoch(rewards.getRewardEpoch());
        document.setRates(ratesToDocuments(rewards.getRates()));
        
        // Parse date strings to OffsetDateTime
        if (rewards.getEventStartDate() != null && !rewards.getEventStartDate().isEmpty()) {
            document.setEventStartDate(stringToOffsetDateTime(rewards.getEventStartDate()));
        }
        if (rewards.getEventEndDate() != null && !rewards.getEventEndDate().isEmpty()) {
            document.setEventEndDate(stringToOffsetDateTime(rewards.getEventEndDate()));
        }
        
        return document;
    }
    
    @Named("documentToRewards")
    protected Market.Rewards documentToRewards(RewardsDocument document) {
        if (document == null) {
            return null;
        }
        
        Market.Rewards rewards = new Market.Rewards();
        rewards.setMinSize(document.getMinSize() != null ? document.getMinSize() : 0);
        rewards.setMaxSpread(document.getMaxSpread() != null ? document.getMaxSpread() : 0);
        rewards.setInGameMultiplier(document.getInGameMultiplier() != null ? document.getInGameMultiplier() : 0.0);
        rewards.setRewardEpoch(document.getRewardEpoch() != null ? document.getRewardEpoch() : 0);
        rewards.setRates(documentsToRates(document.getRates()));
        
        // Convert OffsetDateTime back to strings
        if (document.getEventStartDate() != null) {
            rewards.setEventStartDate(offsetDateTimeToString(document.getEventStartDate()));
        }
        if (document.getEventEndDate() != null) {
            rewards.setEventEndDate(offsetDateTimeToString(document.getEventEndDate()));
        }
        
        return rewards;
    }
    
    protected TokenDocument tokenToDocument(Market.Token token) {
        if (token == null) {
            return null;
        }
        
        TokenDocument document = new TokenDocument();
        document.setTokenId(token.getTokenId());
        document.setOutcome(token.getOutcome());
        document.setPrice(token.getPrice());
        document.setWinner(token.isWinner());
        
        return document;
    }
    
    protected Market.Token documentToToken(TokenDocument document) {
        if (document == null) {
            return null;
        }
        
        Market.Token token = new Market.Token();
        token.setTokenId(document.getTokenId());
        token.setOutcome(document.getOutcome());
        token.setPrice(document.getPrice() != null ? document.getPrice() : 0.0);
        token.setWinner(document.getWinner() != null ? document.getWinner() : false);
        
        return token;
    }
    
    @Named("tokensToDocuments")
    protected List<TokenDocument> tokensToDocuments(List<Market.Token> tokens) {
        if (tokens == null) {
            return null;
        }
        return tokens.stream()
                .map(this::tokenToDocument)
                .collect(java.util.stream.Collectors.toList());
    }
    
    @Named("documentsToTokens")
    protected List<Market.Token> documentsToTokens(List<TokenDocument> documents) {
        if (documents == null) {
            return null;
        }
        return documents.stream()
                .map(this::documentToToken)
                .collect(java.util.stream.Collectors.toList());
    }
    
    protected RateDocument rateToDocument(Market.Rate rate) {
        if (rate == null) {
            return null;
        }
        
        RateDocument document = new RateDocument();
        document.setAssetAddress(rate.getAssetAddress());
        document.setRewardsDailyRate(rate.getRewardsDailyRate());
        
        return document;
    }
    
    protected Market.Rate documentToRate(RateDocument document) {
        if (document == null) {
            return null;
        }
        
        Market.Rate rate = new Market.Rate();
        rate.setAssetAddress(document.getAssetAddress());
        rate.setRewardsDailyRate(document.getRewardsDailyRate() != null ? document.getRewardsDailyRate() : 0);
        
        return rate;
    }
    
    protected List<RateDocument> ratesToDocuments(List<Market.Rate> rates) {
        if (rates == null) {
            return null;
        }
        return rates.stream()
                .map(this::rateToDocument)
                .collect(java.util.stream.Collectors.toList());
    }
    
    protected List<Market.Rate> documentsToRates(List<RateDocument> documents) {
        if (documents == null) {
            return null;
        }
        return documents.stream()
                .map(this::documentToRate)
                .collect(java.util.stream.Collectors.toList());
    }
}