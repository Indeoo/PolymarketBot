-- Increase the size of JSON columns to accommodate larger data from the API
-- Some markets have extensive token and tag data that exceeds current limits

-- Increase tokens_json column size from 2000 to 10000 characters
ALTER TABLE markets ALTER COLUMN tokens_json TYPE VARCHAR(10000);

-- Increase tags column size from 1000 to 3000 characters  
ALTER TABLE markets ALTER COLUMN tags TYPE VARCHAR(3000);

-- Increase rewards_json column size from 1000 to 3000 characters
ALTER TABLE markets ALTER COLUMN rewards_json TYPE VARCHAR(3000);