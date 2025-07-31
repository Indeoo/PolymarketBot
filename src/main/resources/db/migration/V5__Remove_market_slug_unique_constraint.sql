-- Remove UNIQUE constraint from market_slug to allow duplicate market slugs
-- The Polymarket API may return markets with duplicate slugs, especially for updates

-- Drop the unique constraint on market_slug
ALTER TABLE markets DROP CONSTRAINT IF EXISTS markets_market_slug_key;