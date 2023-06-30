-- liquibase formatted sql

-- changeset liquibase:3
CREATE TABLE users (
id SERIAL PRIMARY KEY,
username VARCHAR(30) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL
);

CREATE TABLE basket (
  id SERIAL PRIMARY KEY
  user_id SERIAL UNIQUE NOT NULL,

  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);


TRUNCATE TABLE basket_item;

ALTER TABLE basket_item ADD COLUMN basket_id SERIAL NOT NULL;
ALTER TABLE basket_item ADD CONSTRAINT fk_basket FOREIGN KEY (basket_id) REFERENCES basket(id);