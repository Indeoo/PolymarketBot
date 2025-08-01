package com.venherak.polymarket.document;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.OffsetDateTime;

/**
 * Elasticsearch document representing market rewards information.
 * Stores rewards data as native Elasticsearch object for efficient querying.
 */
public class RewardsDocument {
    
    @Field(type = FieldType.Integer)
    private Integer minSize;
    
    @Field(type = FieldType.Integer)
    private Integer maxSpread;
    
    @Field(type = FieldType.Date)
    private OffsetDateTime eventStartDate;
    
    @Field(type = FieldType.Date)
    private OffsetDateTime eventEndDate;
    
    @Field(type = FieldType.Double)
    private Double inGameMultiplier;
    
    @Field(type = FieldType.Integer)
    private Integer rewardEpoch;

    // Constructors
    public RewardsDocument() {}

    public RewardsDocument(Integer minSize, Integer maxSpread, OffsetDateTime eventStartDate, 
                          OffsetDateTime eventEndDate, Double inGameMultiplier, Integer rewardEpoch) {
        this.minSize = minSize;
        this.maxSpread = maxSpread;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.inGameMultiplier = inGameMultiplier;
        this.rewardEpoch = rewardEpoch;
    }

    // Getters and setters
    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxSpread() {
        return maxSpread;
    }

    public void setMaxSpread(Integer maxSpread) {
        this.maxSpread = maxSpread;
    }

    public OffsetDateTime getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(OffsetDateTime eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public OffsetDateTime getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(OffsetDateTime eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public Double getInGameMultiplier() {
        return inGameMultiplier;
    }

    public void setInGameMultiplier(Double inGameMultiplier) {
        this.inGameMultiplier = inGameMultiplier;
    }

    public Integer getRewardEpoch() {
        return rewardEpoch;
    }

    public void setRewardEpoch(Integer rewardEpoch) {
        this.rewardEpoch = rewardEpoch;
    }

    @Override
    public String toString() {
        return "RewardsDocument{" +
               "minSize=" + minSize +
               ", maxSpread=" + maxSpread +
               ", eventStartDate=" + eventStartDate +
               ", eventEndDate=" + eventEndDate +
               ", inGameMultiplier=" + inGameMultiplier +
               ", rewardEpoch=" + rewardEpoch +
               '}';
    }
}