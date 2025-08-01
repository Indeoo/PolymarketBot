package com.venherak.polymarket.document;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Elasticsearch document representing a market token.
 * Stores token data as native Elasticsearch object for efficient querying.
 */
public class TokenDocument {
    
    @Field(type = FieldType.Keyword)
    private String tokenId;
    
    @Field(type = FieldType.Keyword)
    private String outcome;
    
    @Field(type = FieldType.Double)
    private Double price;
    
    @Field(type = FieldType.Boolean)
    private Boolean winner;

    // Constructors
    public TokenDocument() {}

    public TokenDocument(String tokenId, String outcome, Double price, Boolean winner) {
        this.tokenId = tokenId;
        this.outcome = outcome;
        this.price = price;
        this.winner = winner;
    }

    // Getters and setters
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "TokenDocument{" +
               "tokenId='" + tokenId + '\'' +
               ", outcome='" + outcome + '\'' +
               ", price=" + price +
               ", winner='" + winner + '\'' +
               '}';
    }
}