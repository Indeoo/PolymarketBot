package com.venherak.polymarket.exception;

/**
 * Exception thrown when there's an error persisting data to Elasticsearch.
 */
public class DataPersistenceException extends RuntimeException {
    
    public DataPersistenceException(String message) {
        super(message);
    }
    
    public DataPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static DataPersistenceException forSaveError(String message, Throwable cause) {
        return new DataPersistenceException("Failed to save data: " + message, cause);
    }
    
    public static DataPersistenceException forIndexError(String indexName, Throwable cause) {
        return new DataPersistenceException("Failed to access index: " + indexName, cause);
    }
}