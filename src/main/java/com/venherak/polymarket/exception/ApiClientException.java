package com.venherak.polymarket.exception;

/**
 * Exception thrown when there's an error communicating with the Polymarket API.
 */
public class ApiClientException extends RuntimeException {
    
    public ApiClientException(String message) {
        super(message);
    }
    
    public ApiClientException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static ApiClientException forHttpError(int statusCode, String message) {
        return new ApiClientException("HTTP " + statusCode + " error: " + message);
    }
    
    public static ApiClientException forNetworkError(String message, Throwable cause) {
        return new ApiClientException("Network error: " + message, cause);
    }
}