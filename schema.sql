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

DROP TABLE IF EXISTS publisher;
CREATE TABLE publisher (
    id BINARY(16)
);

DROP TABLE IF EXISTS subscription;
CREATE TABLE subscription (
    subscriber_id BINARY(16) NOT NULL,
    publisher_id BINARY(16) NOT NULL,
    PRIMARY KEY (subscriber_id, publisher_id)
);

-- Robin subscribes to Batman and Joker
INSERT INTO subscription (subscriber_id, publisher_id) VALUES (X'D3A9A38D0903448A92A706F00372544F', X'16601887A8FF48C78A16F0CB82765759');
INSERT INTO subscription (subscriber_id, publisher_id) VALUES (X'D3A9A38D0903448A92A706F00372544F', X'E09917C95357494FAE17A1DAAEC79B3A');

-- Joker subscribes to Batman
INSERT INTO subscription (subscriber_id, publisher_id) VALUES (X'E09917C95357494FAE17A1DAAEC79B3A', X'16601887A8FF48C78A16F0CB82765759');

-- Create messages
INSERT INTO message (id, author_id, content) VALUES (X'7A071CC4007A4C598F558C1C24A11343', X'16601887A8FF48C78A16F0CB82765759', 'The Bat Signal is out of order. Please text until further notice.');
INSERT INTO message (id, author_id, content) VALUES (X'721C21F5B1D24149827305DB4DEC8165', X'E09917C95357494FAE17A1DAAEC79B3A', 'Let\'s put a smile on that face!');
INSERT INTO message (id, author_id, content) VALUES (X'006BD4A24A094B609602317AFCE2B26E', X'16601887A8FF48C78A16F0CB82765759', 'WARNING: Joker has put poison in the tap water again. Please don\'t drink it.');
INSERT INTO message (id, author_id, content) VALUES (X'DDBCF9CDB28D482B8B88F37DD6249EB7', X'16601887A8FF48C78A16F0CB82765759', 'The Bat Signal is working again.');
INSERT INTO message (id, author_id, content) VALUES (X'68053A364574458DA30BF1D7C1510CDC', X'E09917C95357494FAE17A1DAAEC79B3A', 'Haven\'t you ever heard of the healing power of laughter?');

-- Insert publishers
INSERT INTO publisher (id) VALUES (X'16601887A8FF48C78A16F0CB82765759');
INSERT INTO publisher (id) VALUES (X'E09917C95357494FAE17A1DAAEC79B3A');