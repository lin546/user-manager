-- MySQL schema for user-manager
CREATE DATABASE IF NOT EXISTS user_manager DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE user_manager;

CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com'), ('Bob', 'bob@example.com');

-- TODO
-- ALTER TABLE user ADD COLUMN tags VARCHAR(255) DEFAULT NULL;