CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    age INT
);

CREATE TABLE products
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    price float8
);
