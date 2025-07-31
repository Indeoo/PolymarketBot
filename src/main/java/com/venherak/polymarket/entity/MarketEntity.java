package com.venherak.polymarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * JPA entity representing a Polymarket market.
 */
@Entity
@Table(name = "markets")
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "condition_id")
    private String conditionId;
    
    @Column(name = "question_id")
    private String questionId;
    
    @Column(name = "question", length = 1000)
    private String question;
    
    @Column(name = "description", length = 2000)
    private String description;
    
    @Column(name = "market_slug")
    private String marketSlug;
    
    @Column(name = "end_date")
    private OffsetDateTime endDate;
    
    @Column(name = "game_start_time")
    private OffsetDateTime gameStartTime;
    
    @Column(name = "seconds_delay")
    private Integer secondsDelay;
    
    @Column(name = "fpmm")
    private String fpmm;
    
    @Column(name = "maker_base_fee")
    private Integer makerBaseFee;
    
    @Column(name = "taker_base_fee")
    private Integer takerBaseFee;
    
    @Column(name = "minimum_order_size")
    private Integer minimumOrderSize;
    
    @Column(name = "minimum_tick_size")
    private Double minimumTickSize;
    
    @Column(name = "active")
    private Boolean active;
    
    @Column(name = "closed")
    private Boolean closed;
    
    @Column(name = "archived")
    private Boolean archived;
    
    @Column(name = "accepting_orders")
    private Boolean acceptingOrders;
    
    @Column(name = "accepting_order_timestamp")
    private OffsetDateTime acceptingOrderTimestamp;
    
    @Column(name = "enable_order_book")
    private Boolean enableOrderBook;
    
    @Column(name = "notifications_enabled")
    private Boolean notificationsEnabled;
    
    @Column(name = "neg_risk")
    private Boolean negRisk;
    
    @Column(name = "neg_risk_market_id")
    private String negRiskMarketId;
    
    @Column(name = "neg_risk_request_id")
    private String negRiskRequestId;
    
    @Column(name = "is_50_50_outcome")
    private Boolean is5050Outcome;
    
    @Column(name = "tags", length = 1000)
    private String tagsJson;
    
    @Column(name = "tokens_json", length = 2000)
    private String tokensJson;
    
    @Column(name = "rewards_json", length = 1000)
    private String rewardsJson;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarketSlug() {
        return marketSlug;
    }

    public void setMarketSlug(String marketSlug) {
        this.marketSlug = marketSlug;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public OffsetDateTime getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(OffsetDateTime gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public Integer getSecondsDelay() {
        return secondsDelay;
    }

    public void setSecondsDelay(Integer secondsDelay) {
        this.secondsDelay = secondsDelay;
    }

    public String getFpmm() {
        return fpmm;
    }

    public void setFpmm(String fpmm) {
        this.fpmm = fpmm;
    }

    public Integer getMakerBaseFee() {
        return makerBaseFee;
    }

    public void setMakerBaseFee(Integer makerBaseFee) {
        this.makerBaseFee = makerBaseFee;
    }

    public Integer getTakerBaseFee() {
        return takerBaseFee;
    }

    public void setTakerBaseFee(Integer takerBaseFee) {
        this.takerBaseFee = takerBaseFee;
    }

    public Integer getMinimumOrderSize() {
        return minimumOrderSize;
    }

    public void setMinimumOrderSize(Integer minimumOrderSize) {
        this.minimumOrderSize = minimumOrderSize;
    }

    public Double getMinimumTickSize() {
        return minimumTickSize;
    }

    public void setMinimumTickSize(Double minimumTickSize) {
        this.minimumTickSize = minimumTickSize;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getAcceptingOrders() {
        return acceptingOrders;
    }

    public void setAcceptingOrders(Boolean acceptingOrders) {
        this.acceptingOrders = acceptingOrders;
    }

    public OffsetDateTime getAcceptingOrderTimestamp() {
        return acceptingOrderTimestamp;
    }

    public void setAcceptingOrderTimestamp(OffsetDateTime acceptingOrderTimestamp) {
        this.acceptingOrderTimestamp = acceptingOrderTimestamp;
    }

    public Boolean getEnableOrderBook() {
        return enableOrderBook;
    }

    public void setEnableOrderBook(Boolean enableOrderBook) {
        this.enableOrderBook = enableOrderBook;
    }

    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public Boolean getNegRisk() {
        return negRisk;
    }

    public void setNegRisk(Boolean negRisk) {
        this.negRisk = negRisk;
    }

    public String getNegRiskMarketId() {
        return negRiskMarketId;
    }

    public void setNegRiskMarketId(String negRiskMarketId) {
        this.negRiskMarketId = negRiskMarketId;
    }

    public String getNegRiskRequestId() {
        return negRiskRequestId;
    }

    public void setNegRiskRequestId(String negRiskRequestId) {
        this.negRiskRequestId = negRiskRequestId;
    }

    public Boolean getIs5050Outcome() {
        return is5050Outcome;
    }

    public void setIs5050Outcome(Boolean is5050Outcome) {
        this.is5050Outcome = is5050Outcome;
    }

    public String getTagsJson() {
        return tagsJson;
    }

    public void setTagsJson(String tagsJson) {
        this.tagsJson = tagsJson;
    }

    public String getTokensJson() {
        return tokensJson;
    }

    public void setTokensJson(String tokensJson) {
        this.tokensJson = tokensJson;
    }

    public String getRewardsJson() {
        return rewardsJson;
    }

    public void setRewardsJson(String rewardsJson) {
        this.rewardsJson = rewardsJson;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}