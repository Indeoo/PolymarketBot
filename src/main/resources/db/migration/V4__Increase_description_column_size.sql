-- Increase the size of description column to accommodate larger market descriptions from the API
-- Some markets have very long descriptions that exceed the current 2000 character limit

-- Increase description column size from 2000 to 5000 characters
ALTER TABLE markets ALTER COLUMN description TYPE VARCHAR(5000);