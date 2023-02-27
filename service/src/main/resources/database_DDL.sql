CREATE TABLE IF NOT EXISTS categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS
    change_types
(
    id          SERIAL PRIMARY KEY,
    description VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS
    brands
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(32) NOT NULL,
    category_id INTEGER REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS
    users
(
    id           SERIAL PRIMARY KEY,
    full_name    VARCHAR(32) NOT NULL,
    phone_number VARCHAR(32) NOT NULL,
    password     VARCHAR(16) NOT NULL,
    address      VARCHAR(64),
    role         VARCHAR(16) NOT NULL,
    username     VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    id             SERIAL PRIMARY KEY,
    category_id    INTEGER REFERENCES categories (id),
    description    TEXT             NOT NULL,
    media_name     VARCHAR(1024),
    timestamp      TIMESTAMP,
    user_id        INTEGER          NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    price          DOUBLE PRECISION NOT NULL,
    brand_id       INTEGER          NOT NULL REFERENCES brands (id),
    closed         BOOLEAN DEFAULT FALSE,
    change_type_id INTEGER          NOT NULL REFERENCES change_types (id),
    change_value   DOUBLE PRECISION,
    change_wish    VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS
    guitars
(
    id               SERIAL PRIMARY KEY REFERENCES products (id) ON DELETE CASCADE,
    model            VARCHAR(32) NOT NULL,
    year             INTEGER     NOT NULL,
    country          VARCHAR(32) NOT NULL,
    pick_ups         VARCHAR(32),
    fingerboard_wood VARCHAR(32)

);

CREATE TABLE IF NOT EXISTS
    pedals
(
    id         SERIAL PRIMARY KEY REFERENCES products (id) ON DELETE CASCADE,
    model      VARCHAR(32) NOT NULL,
    shop_price DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS
    offers
(
    id             SERIAL PRIMARY KEY,
    buyer_id       INTEGER REFERENCES users (id)        NOT NULL,
    seller_id      INTEGER REFERENCES users (id)        NOT NULL,
    change_type_id INTEGER REFERENCES change_types (id) NOT NULL,
    change_value   DOUBLE PRECISION,
    timestamp      TIMESTAMP                            NOT NULL,
    accepted       BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS
    offers_products
(
    id         SERIAL PRIMARY KEY,
    offer_id   INTEGER   NOT NULL REFERENCES offers (id) ON DELETE CASCADE,
    product_id INTEGER   NOT NULL REFERENCES products (id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS
    users_liked_products
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER   NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    product_id INTEGER   NOT NULL REFERENCES products (id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL
);




