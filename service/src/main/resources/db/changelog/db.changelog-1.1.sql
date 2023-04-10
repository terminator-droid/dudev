--liquibase formatted sql
--changeSet dus:1

CREATE TABLE IF NOT EXISTS category (id SERIAL PRIMARY KEY,
                                     name VARCHAR (32) NOT NULL,
    created_at TIMESTAMP NOT NULL);

--changeSet dus:2

CREATE TABLE IF NOT EXISTS change_type (id SERIAL PRIMARY KEY,
                                        description VARCHAR (64) NOT NULL,
    created_at TIMESTAMP NOT NULL);

--changeSet dus:3

CREATE TABLE IF NOT EXISTS brand (id SERIAL PRIMARY KEY,
                                  name VARCHAR (32) NOT NULL,
    category_id INTEGER REFERENCES category (id),
    created_at TIMESTAMP NOT NULL);

--changeSet dus:4

CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY,
                                  full_name VARCHAR (32) NOT NULL,
    phone_number VARCHAR (32) NOT NULL,
    password VARCHAR (64) NOT NULL,
    address VARCHAR (64),
    ROLE VARCHAR (16) NOT NULL,
    username VARCHAR (32) NOT NULL,
    created_at TIMESTAMP NOT NULL);

--changeSet dus:5

CREATE TABLE IF NOT EXISTS product
(id SERIAL PRIMARY KEY,
 category_id INTEGER REFERENCES category (id),
    description TEXT NOT NULL,
    media_name VARCHAR (1024),
    created_at TIMESTAMP NOT NULL,
    user_id INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    price DOUBLE PRECISION NOT NULL,
    brand_id INTEGER NOT NULL REFERENCES brand (id),
    closed BOOLEAN DEFAULT FALSE,
    change_type_id INTEGER NOT NULL REFERENCES change_type (id),
    change_value DOUBLE PRECISION, change_wish VARCHAR (32));

--changeSet dus:6

CREATE TABLE IF NOT EXISTS guitar
(id SERIAL PRIMARY KEY REFERENCES product (id) ON DELETE CASCADE,
    model VARCHAR (32) NOT NULL,
    YEAR INTEGER,
    country VARCHAR (32),
    pick_ups VARCHAR (32),
    fingerboard_wood VARCHAR (32));

--changeSet dus:7

CREATE TABLE IF NOT EXISTS pedal
(id SERIAL PRIMARY KEY REFERENCES product (id) ON DELETE CASCADE,
    model VARCHAR (32) NOT NULL,
    shop_price DOUBLE PRECISION);

--changeSet dus:8

CREATE TABLE IF NOT EXISTS offer (id SERIAL PRIMARY KEY,
                                  buyer_id INTEGER REFERENCES users (id) NOT NULL,
    seller_id INTEGER REFERENCES users (id) NOT NULL ON DELETE CASCADE,
    change_type_id INTEGER REFERENCES change_type (id) NOT NULL ON DELETE CASCADE,
    change_value DOUBLE PRECISION, created_at TIMESTAMP NOT NULL,
    accepted BOOLEAN DEFAULT FALSE);

--changeSet dus:9

CREATE TABLE IF NOT EXISTS offer_product
(id SERIAL PRIMARY KEY,
 offer_id INTEGER NOT NULL REFERENCES offer (id) ON DELETE CASCADE,
    product_id INTEGER NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL);

--changeSet dus:10

CREATE TABLE IF NOT EXISTS users_liked_product
(id SERIAL PRIMARY KEY,
 user_id INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    product_id INTEGER NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL);