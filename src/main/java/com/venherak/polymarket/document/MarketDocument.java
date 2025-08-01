package com.venherak.polymarket.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.OffsetDateTime;

/**
 * Elasticsearch document representing a Polymarket market.
 * Stores market data in Elasticsearch index for fast search and analytics.
 */
@Document(indexName = "polymarket-markets")
public class MarketDocument {
    
    @Id
    private String id;
    
    @Field(type = FieldType.Keyword)
    private String conditionId;
    
    @Field(type = FieldType.Keyword)
    private String questionId;
    
    @Field(type = FieldType.Text, analyzer = "standard")
    private String question;
    
    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;
    
    @Field(type = FieldType.Keyword)
    private String marketSlug;
    
    @Field(type = FieldType.Date)
    private OffsetDateTime endDate;
    
    @Field(type = FieldType.Date)
    private OffsetDateTime gameStartTime;
    
    @Field(type = FieldType.Integer)
    private Integer secondsDelay;
    
    @Field(type = FieldType.Keyword)
    private String fpmm;
    
    @Field(type = FieldType.Integer)
    private Integer makerBaseFee;
    
    @Field(type = FieldType.Integer)
    private Integer takerBaseFee;
    
    @Field(type = FieldType.Integer)
    private Integer minimumOrderSize;
    
    @Field(type = FieldType.Double)
    private Double minimumTickSize;
    
    @Field(type = FieldType.Boolean)
    private Boolean active;
    
    @Field(type = FieldType.Boolean)
    private Boolean closed;
    
    @Field(type = FieldType.Boolean)
    private Boolean archived;
    
    @Field(type = FieldType.Boolean)
    private Boolean acceptingOrders;
    
    @Field(type = FieldType.Date)
    private OffsetDateTime acceptingOrderTimestamp;
    
    @Field(type = FieldType.Boolean)
    private Boolean enableOrderBook;
    
    @Field(type = FieldType.Boolean)
    private Boolean notificationsEnabled;
    
    @Field(type = FieldType.Boolean)
    private Boolean negRisk;
    
    @Field(type = FieldType.Keyword)
    private String negRiskMarketId;
    
    @Field(type = FieldType.Keyword)
    private String negRiskRequestId;
    
    @Field(type = FieldType.Boolean)
    private Boolean is5050Outcome;
    
    @Field(type = FieldType.Text)
    private String tagsJson;
    
    @Field(type = FieldType.Text)
    private String tokensJson;
    
    @Field(type = FieldType.Text)
    private String rewardsJson;
    
    @Field(type = FieldType.Date)
    private OffsetDateTime createdAt;
    
    @Field(type = FieldType.Date)
    private OffsetDateTime updatedAt;

    // Constructors
    public MarketDocument() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}