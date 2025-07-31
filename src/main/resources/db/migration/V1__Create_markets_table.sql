CREATE TABLE markets (
    id BIGSERIAL PRIMARY KEY,
    condition_id VARCHAR(255) UNIQUE NOT NULL,
    question_id VARCHAR(255),
    question VARCHAR(1000),
    description VARCHAR(2000),
    market_slug VARCHAR(255) UNIQUE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE,
    game_start_time TIMESTAMP WITH TIME ZONE,
    seconds_delay INTEGER,
    fpmm VARCHAR(255),
    maker_base_fee INTEGER,
    taker_base_fee INTEGER,
    minimum_order_size INTEGER,
    minimum_tick_size DOUBLE PRECISION,
    active BOOLEAN,
    closed BOOLEAN,
    archived BOOLEAN,
    accepting_orders BOOLEAN,
    accepting_order_timestamp TIMESTAMP WITH TIME ZONE,
    enable_order_book BOOLEAN,
    notifications_enabled BOOLEAN,
    neg_risk BOOLEAN,
    neg_risk_market_id VARCHAR(255),
    neg_risk_request_id VARCHAR(255),
    is_50_50_outcome BOOLEAN,
    tags VARCHAR(1000),
    tokens_json VARCHAR(2000),
    rewards_json VARCHAR(1000),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create indexes for frequently queried columns
CREATE INDEX idx_markets_active ON markets(active);
CREATE INDEX idx_markets_closed ON markets(closed);
CREATE INDEX idx_markets_end_date ON markets(end_date);
CREATE INDEX idx_markets_market_slug ON markets(market_slug);