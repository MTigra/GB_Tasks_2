DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    age INT
);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    price INT
);
