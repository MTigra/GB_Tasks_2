BEGIN;
DROP TABLE IF EXISTS consumers CASCADE;
CREATE TABLE consumers
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT
);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id    BIGSERIAL PRIMARY KEY,
    name  TEXT,
    price FLOAT
);

DROP TABLE IF EXISTS products_consumers CASCADE;
CREATE TABLE products_consumers
(
    id         BIGSERIAL NOT NULL,
    consumer_id   BIGINT    NOT NULL,
    product_id BIGINT,
    price      FLOAT,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (consumer_id) REFERENCES consumers (id)
);

INSERT INTO products
    (name, price)
    VALUES
        ('Notebook', 1150.99),
        ('Dishwasher', 500),
        ('Bread', 3),
        ('Jacket', 200);

INSERT INTO consumers
    (name)
    VALUES
        ('John'),
        ('Mary'),
        ('Stephan');

INSERT INTO products_consumers
    (consumer_id, product_id, price)
    VALUES
        (1, 1, 1000),
        (1, 2, 567),
        (1, 1, 1150),
        (2, 4, 245),
        (3, 3, 7),
        (3, 1, 888),
        (2, 1, 999);
COMMIT;