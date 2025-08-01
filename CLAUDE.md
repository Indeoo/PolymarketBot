# Polymarket Bot - Claude Assistant Guide

## Project Overview

**Polymarket Bot** is a Spring Boot application designed to fetch, store, and analyze market data from the Polymarket API. The project has recently migrated from PostgreSQL/JPA to Elasticsearch for improved search capabilities and scalability.

**Tech Stack:**
- Java 21
- Spring Boot 3.5.4
- Elasticsearch (Spring Data Elasticsearch)
- MapStruct for object mapping
- Gradle 8.14.3

## Architecture

The application follows a clean, layered architecture with clear separation of concerns:

### Core Components

1. **API Client Layer** (`PolymarketApiClient`)
   - Handles HTTP communication with Polymarket API
   - Manages pagination and request parameters

2. **Service Layer** 
   - `PolymarketService`: Business logic and orchestration
   - `MarketDataService`: Elasticsearch operations and data management

3. **Data Layer**
   - `MarketDocument`: Elasticsearch document representation
   - `MarketDocumentRepository`: Elasticsearch repository with custom queries
   - `Market`: API response model

4. **Mapping Layer**
   - `MarketMapper`: MapStruct-based mapping between models and documents

## Project Structure

```
src/main/java/com/venherak/polymarket/
├── PolymarketBotApplication.java          # Main Spring Boot application
├── client/
│   └── PolymarketApiClient.java          # HTTP client for Polymarket API
├── config/
│   └── AppConfig.java                    # Application configuration
├── document/
│   ├── MarketDocument.java               # Elasticsearch document
│   ├── RateDocument.java                 # Rate data document
│   ├── RewardsDocument.java              # Rewards data document
│   └── TokenDocument.java                # Token data document
├── mapper/
│   └── MarketMapper.java                 # MapStruct mapping between models/documents
├── model/
│   ├── Market.java                       # API response model
│   └── MarketsResponse.java              # API pagination response wrapper
├── repository/
│   └── MarketDocumentRepository.java     # Elasticsearch repository
├── runner/
│   └── MarketDataInitializer.java        # Data initialization runner
└── service/
    ├── MarketDataService.java            # Data persistence service
    └── PolymarketService.java            # Business logic service
```

## Configuration

### Application Configuration (`application.yaml`)

```yaml
spring:
  application:
    name: PolymarketBot
  elasticsearch:
    uris: http://localhost:9200
    connection-timeout: 10s
    socket-timeout: 30s

polymarket:
  api:
    base-url: https://clob.polymarket.com
```

### Dependencies (`build.gradle`)

Key dependencies:
- `spring-boot-starter-data-elasticsearch`: Elasticsearch integration
- `mapstruct:1.6.0`: Object mapping
- `spring-boot-starter-web`: REST capabilities
- PostgreSQL support commented out (migration completed)

## Development Workflow

### Building and Running

1. **Build the project:**
   ```bash
   ./gradlew build
   ```

2. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

3. **Run demo mode:**
   ```bash
   ./run-demo.sh
   ```

### Testing

Run tests with:
```bash
./gradlew test
```

### Prerequisites

Before running the application, ensure the following services are running:

1. **Elasticsearch** (port 9200):
   ```bash
   docker run -d --name elasticsearch \
   -p 9200:9200 -p 9300:9300 \
   -e "discovery.type=single-node" \
   -e "xpack.security.enabled=false" \
   docker.elastic.co/elasticsearch/elasticsearch:8.13.4
   ```

2. **PostgreSQL** (legacy - can be removed):
   ```bash
   docker run --name postgres-db \
   -e POSTGRES_PASSWORD=mysecretpassword \
   -e POSTGRES_USER=myuser \
   -e POSTGRES_DB=mydatabase \
   -p 5432:5432 \
   -v pgdata:/var/lib/postgresql/data \
   -d postgres
   ```

## Key Features

### Data Fetching
- Fetches all market data from Polymarket API with pagination support
- Handles rate limiting with built-in delays
- Robust error handling and logging

### Market Search & Filtering
The application provides extensive search capabilities:

- **Basic queries**: Active markets, closed markets, archived markets
- **Text search**: Search by question or description text
- **Reward-based queries**: Markets with rewards, high multipliers, specific epochs
- **Token-based queries**: Markets with winning tokens, high-priced tokens
- **Tag-based filtering**: Search markets by tags
- **Complex combinations**: Active markets with rewards, etc.

### Data Models

**Market** contains:
- Basic market info (question, description, slug)
- Trading parameters (fees, order sizes, tick sizes)
- Status flags (active, closed, archived)
- Nested rewards with rates and multipliers
- Token information with prices and outcomes
- Metadata (tags, dates, IDs)

## API Integration

### Endpoints Used
- `GET /markets`: Fetches paginated market data
- Supports `next_cursor` parameter for pagination

### Data Flow
1. `PolymarketService.fetchAndSaveAllMarkets()` initiates the process
2. `PolymarketApiClient` fetches data in batches of 500
3. `MarketMapper` converts API models to Elasticsearch documents
4. `MarketDataService` persists data to Elasticsearch
5. Built-in queries enable fast market retrieval and analysis

## Migration Notes

The project recently migrated from PostgreSQL to Elasticsearch:

- **Removed**: JPA entities, Flyway migrations, PostgreSQL dependencies
- **Added**: Elasticsearch documents, repositories, and custom queries
- **Enhanced**: Search capabilities with full-text search and complex filtering
- **Retained**: Same business logic and API integration

## Common Tasks

### Adding New Market Queries
1. Add method to `MarketDocumentRepository` interface
2. Use Spring Data Elasticsearch naming conventions
3. Add corresponding method to `MarketDataService`
4. Expose through `PolymarketService` if needed

### Extending Market Data
1. Update `Market` model with new fields
2. Update `MarketDocument` with corresponding Elasticsearch fields
3. Update `MarketMapper` with new mappings
4. Elasticsearch will auto-create new field mappings

### Debugging
- Check Elasticsearch status: `curl http://localhost:9200/_cluster/health`
- View market index: `curl http://localhost:9200/polymarket-markets/_search`
- Monitor application logs for API rate limiting or parsing errors

## Recent Changes (Git History)

- **6fb787d**: Added support for nested Rate objects in Market model and RewardsDocument
- **c996364**: Complete refactor from PostgreSQL JPA to Elasticsearch
- **34365f5**: Initial Elasticsearch migration
- **14bc552**: Replaced custom mapping service with MapStruct
- **a34ae72**: Refactored service layer with better separation of concerns

## Performance Considerations

- Markets are fetched in batches of 500 with 100ms delays to respect API limits
- Elasticsearch provides fast full-text search and aggregations
- MapStruct generates efficient mapping code at compile time
- Connection pooling and timeouts configured for optimal performance

## Testing

Test files are located in `src/test/java/`:
- `PolymarketBotApplicationTests.java`: Integration tests
- `PolymarketServiceTest.java`: Service layer unit tests

For comprehensive testing, ensure Elasticsearch is running locally or use test containers.