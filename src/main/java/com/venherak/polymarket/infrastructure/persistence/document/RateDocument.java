package com.venherak.polymarket.infrastructure.persistence.document;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Elasticsearch document representing a rate object within rewards.
 * Stores rate data as native Elasticsearch object for efficient querying.
 */
public class RateDocument {
    
    @Field(type = FieldType.Keyword)
    private String assetAddress;
    
    @Field(type = FieldType.Integer)
    private Integer rewardsDailyRate;

    // Constructors
    public RateDocument() {}

    public RateDocument(String assetAddress, Integer rewardsDailyRate) {
        this.assetAddress = assetAddress;
        this.rewardsDailyRate = rewardsDailyRate;
    }

    // Getters and setters
    public String getAssetAddress() {
        return assetAddress;
    }

    public void setAssetAddress(String assetAddress) {
        this.assetAddress = assetAddress;
    }

    public Integer getRewardsDailyRate() {
        return rewardsDailyRate;
    }

    public void setRewardsDailyRate(Integer rewardsDailyRate) {
        this.rewardsDailyRate = rewardsDailyRate;
    }

    @Override
    public String toString() {
        return "RateDocument{" +
               "assetAddress='" + assetAddress + '\'' +
               ", rewardsDailyRate=" + rewardsDailyRate +
               '}';
    }
}