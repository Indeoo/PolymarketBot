# Database Migration and JPA Entity for Polymarket Markets

This document explains the database implementation for storing Polymarket market data.

## Overview

The implementation includes:

1. A JPA entity (`MarketEntity`) for storing market data
2. Flyway migration scripts for database schema creation
3. Repository and service classes for database operations
4. A data initializer for populating the database with market data from the API

## Database Schema

The database schema is defined in the Flyway migration script `V1__Create_markets_table.sql`. It creates a `markets` table with the following key columns:

- `id`: Primary key
- `condition_id`: Unique identifier for the market condition
- `question_id`: Identifier for the market question
- `question`: The market question text
- `description`: Detailed description of the market
- `market_slug`: Unique slug for the market
- Various boolean flags: `active`, `closed`, `archived`, etc.
- Date fields: `end_date`, `game_start_time`, etc.
- JSON fields for complex data: `tags_json`, `tokens_json`, `rewards_json`

## JPA Entity

The `MarketEntity` class in `com.venherak.polymarket.entity` package maps to the `markets` table. It includes:

- All fields corresponding to the database columns
- Appropriate JPA annotations for mapping
- Conversion logic for dates and complex objects

## Service Classes

1. `MarketMappingService`: Handles conversion between API models and database entities
2. `PolymarketService`: Provides methods to fetch markets from the API and save them to the database

## Usage

### Initializing the Database

To initialize the database with market data from the API, run the application with the `init-data` profile:

```
./gradlew bootRun --args='--spring.profiles.active=init-data'
```

This will:
1. Create the database schema if it doesn't exist
2. Fetch all markets from the Polymarket API
3. Save them to the database

### Accessing Market Data

The `PolymarketService` provides methods to access market data:

- `getAllMarketsFromDatabase()`: Retrieves all markets from the database
- `getActiveMarketsFromDatabase()`: Retrieves only active markets

### Repository Methods

The `MarketRepository` interface provides additional query methods:

- `findByConditionId(String conditionId)`: Find a market by its condition ID
- `findByMarketSlug(String marketSlug)`: Find a market by its slug
- `findByActiveTrue()`: Find all active markets
- `findByClosedFalse()`: Find all markets that are not closed