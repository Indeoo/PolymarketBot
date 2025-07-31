-- Remove UNIQUE constraint on condition_id and make it nullable
-- This allows markets with null or duplicate condition_id values

-- Drop the unique constraint on condition_id
ALTER TABLE markets DROP CONSTRAINT IF EXISTS markets_condition_id_key;

-- Make condition_id nullable by removing NOT NULL constraint
ALTER TABLE markets ALTER COLUMN condition_id DROP NOT NULL;