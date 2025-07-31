package com.venherak.polymarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Model class representing a Polymarket market.
 * This class is also a JPA entity for database persistence.
 */
public class Market {
    @JsonIgnore
    private Long id;
    
    @JsonIgnore
    private LocalDateTime createdAt;
    
    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonProperty("enable_order_book")
    private boolean enableOrderBook;
    
    @JsonProperty("active")
    private boolean active;
    
    @JsonProperty("closed")
    private boolean closed;
    
    @JsonProperty("archived")
    private boolean archived;
    
    @JsonProperty("accepting_orders")
    private boolean acceptingOrders;
    
    @JsonProperty("accepting_order_timestamp")
    private String acceptingOrderTimestamp;
    
    @JsonProperty("minimum_order_size")
    private int minimumOrderSize;
    
    @JsonProperty("minimum_tick_size")
    private double minimumTickSize;
    
    @JsonProperty("condition_id")
    private String conditionId;
    
    @JsonProperty("question_id")
    private String questionId;
    
    @JsonProperty("question")
    private String question;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("market_slug")
    private String marketSlug;
    
    @JsonProperty("end_date_iso")
    private String endDateIso;
    
    @JsonProperty("game_start_time")
    private String gameStartTime;
    
    @JsonProperty("seconds_delay")
    private int secondsDelay;
    
    @JsonProperty("fpmm")
    private String fpmm;
    
    @JsonProperty("maker_base_fee")
    private int makerBaseFee;
    
    @JsonProperty("taker_base_fee")
    private int takerBaseFee;
    
    @JsonProperty("notifications_enabled")
    private boolean notificationsEnabled;
    
    @JsonProperty("neg_risk")
    private boolean negRisk;
    
    @JsonProperty("neg_risk_market_id")
    private String negRiskMarketId;
    
    @JsonProperty("neg_risk_request_id")
    private String negRiskRequestId;
    
    @JsonProperty("icon")
    private String icon;
    
    @JsonProperty("image")
    private String image;
    
    @JsonProperty("rewards")
    private Rewards rewards;
    
    @JsonProperty("is_50_50_outcome")
    private boolean is5050Outcome;
    
    @JsonProperty("tokens")
    private List<Token> tokens;
    
    @JsonProperty("tags")
    private List<String> tags;
    
    // Nested class for rewards
    public static class Rewards {
        @JsonProperty("rates")
        private String rates;
        
        @JsonProperty("min_size")
        private int minSize;
        
        @JsonProperty("max_spread")
        private int maxSpread;
        
        public String getRates() { return rates; }
        public void setRates(String rates) { this.rates = rates; }
        public int getMinSize() { return minSize; }
        public void setMinSize(int minSize) { this.minSize = minSize; }
        public int getMaxSpread() { return maxSpread; }
        public void setMaxSpread(int maxSpread) { this.maxSpread = maxSpread; }
    }
    
    // Nested class for tokens
    public static class Token {
        @JsonProperty("token_id")
        private String tokenId;
        
        @JsonProperty("outcome")
        private String outcome;
        
        @JsonProperty("price")
        private double price;
        
        @JsonProperty("winner")
        private boolean winner;
        
        public String getTokenId() { return tokenId; }
        public void setTokenId(String tokenId) { this.tokenId = tokenId; }
        public String getOutcome() { return outcome; }
        public void setOutcome(String outcome) { this.outcome = outcome; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        public boolean isWinner() { return winner; }
        public void setWinner(boolean winner) { this.winner = winner; }
    }
    
    // Getters and setters
    public boolean isEnableOrderBook() { return enableOrderBook; }
    public void setEnableOrderBook(boolean enableOrderBook) { this.enableOrderBook = enableOrderBook; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public boolean isClosed() { return closed; }
    public void setClosed(boolean closed) { this.closed = closed; }
    
    public boolean isArchived() { return archived; }
    public void setArchived(boolean archived) { this.archived = archived; }
    
    public boolean isAcceptingOrders() { return acceptingOrders; }
    public void setAcceptingOrders(boolean acceptingOrders) { this.acceptingOrders = acceptingOrders; }
    
    public String getAcceptingOrderTimestamp() { return acceptingOrderTimestamp; }
    public void setAcceptingOrderTimestamp(String acceptingOrderTimestamp) { this.acceptingOrderTimestamp = acceptingOrderTimestamp; }
    
    public int getMinimumOrderSize() { return minimumOrderSize; }
    public void setMinimumOrderSize(int minimumOrderSize) { this.minimumOrderSize = minimumOrderSize; }
    
    public double getMinimumTickSize() { return minimumTickSize; }
    public void setMinimumTickSize(double minimumTickSize) { this.minimumTickSize = minimumTickSize; }
    
    public String getConditionId() { return conditionId; }
    public void setConditionId(String conditionId) { this.conditionId = conditionId; }
    
    public String getQuestionId() { return questionId; }
    public void setQuestionId(String questionId) { this.questionId = questionId; }
    
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getMarketSlug() { return marketSlug; }
    public void setMarketSlug(String marketSlug) { this.marketSlug = marketSlug; }
    
    public String getEndDateIso() { return endDateIso; }
    public void setEndDateIso(String endDateIso) { this.endDateIso = endDateIso; }
    
    public String getGameStartTime() { return gameStartTime; }
    public void setGameStartTime(String gameStartTime) { this.gameStartTime = gameStartTime; }
    
    public int getSecondsDelay() { return secondsDelay; }
    public void setSecondsDelay(int secondsDelay) { this.secondsDelay = secondsDelay; }
    
    public String getFpmm() { return fpmm; }
    public void setFpmm(String fpmm) { this.fpmm = fpmm; }
    
    public int getMakerBaseFee() { return makerBaseFee; }
    public void setMakerBaseFee(int makerBaseFee) { this.makerBaseFee = makerBaseFee; }
    
    public int getTakerBaseFee() { return takerBaseFee; }
    public void setTakerBaseFee(int takerBaseFee) { this.takerBaseFee = takerBaseFee; }
    
    public boolean isNotificationsEnabled() { return notificationsEnabled; }
    public void setNotificationsEnabled(boolean notificationsEnabled) { this.notificationsEnabled = notificationsEnabled; }
    
    public boolean isNegRisk() { return negRisk; }
    public void setNegRisk(boolean negRisk) { this.negRisk = negRisk; }
    
    public String getNegRiskMarketId() { return negRiskMarketId; }
    public void setNegRiskMarketId(String negRiskMarketId) { this.negRiskMarketId = negRiskMarketId; }
    
    public String getNegRiskRequestId() { return negRiskRequestId; }
    public void setNegRiskRequestId(String negRiskRequestId) { this.negRiskRequestId = negRiskRequestId; }
    
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public Rewards getRewards() { return rewards; }
    public void setRewards(Rewards rewards) { this.rewards = rewards; }
    
    public boolean isIs5050Outcome() { return is5050Outcome; }
    public void setIs5050Outcome(boolean is5050Outcome) { this.is5050Outcome = is5050Outcome; }
    
    public List<Token> getTokens() { return tokens; }
    public void setTokens(List<Token> tokens) { this.tokens = tokens; }
    
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}