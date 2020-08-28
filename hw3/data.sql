BEGIN;
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id BIGSERIAL PRIMARY KEY , name VARCHAR(255));
INSERT INTO users (name)
VALUES ('u1'),('u2'),('u3'),('u4'),('u5'),('u6'),('u7'),('u8');

DROP TABLE IF EXISTS lots CASCADE;
CREATE TABLE lots(id BIGSERIAL PRIMARY KEY, name VARCHAR(255), current_price DECIMAL DEFAULT 0, version int DEFAULT 0, user_id BIGINT REFERENCES users(id));
INSERT INTO lots (name)
VALUES ('Notebook'),('Table'),('Lamp'),('Masterpiece');
COMMIT;