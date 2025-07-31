package com.venherak.polymarket.Polymarket.Bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;

/**
 * Model class representing a Polymarket market.
 */
public class Market {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("question")
    private String question;
    
    @JsonProperty("slug")
    private String slug;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("image_url")
    private String imageUrl;
    
    @JsonProperty("expires_at")
    private Instant expiresAt;
    
    @JsonProperty("category")
    private String category;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("outcomes")
    private List<Outcome> outcomes;
    
    // Nested class for market outcomes
    public static class Outcome {
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("value")
        private String value;
        
        @JsonProperty("probability")
        private String probability;
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getValue() {
            return value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
        
        public String getProbability() {
            return probability;
        }
        
        public void setProbability(String probability) {
            this.probability = probability;
        }
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getSlug() {
        return slug;
    }
    
    public void setSlug(String slug) {
        this.slug = slug;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Instant getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<Outcome> getOutcomes() {
        return outcomes;
    }
    
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }
}