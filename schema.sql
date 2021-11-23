DROP SCHEMA IF EXISTS ms;
CREATE SCHEMA ms;
USE ms;

DROP TABLE IF EXISTS message;
CREATE TABLE message (
    id BINARY(16) NOT NULL,
    author_id BINARY(16) NOT NULL,
    content VARCHAR(500) NOT NULL,
    date DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS subscription;
CREATE TABLE subscription (
    subscriber_id BINARY(16) NOT NULL,
    publisher_id BINARY(16) NOT NULL,
    PRIMARY KEY (subscriber_id, publisher_id)
);

