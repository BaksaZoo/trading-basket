-- liquibase formatted sql

-- changeset liquibase:3
CREATE TABLE users (
id UUID PRIMARY KEY,
username VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE basket (
  id SERIAL PRIMARY KEY,
  user_id UUID UNIQUE NOT NULL,

  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);


TRUNCATE TABLE basket_item;

ALTER TABLE basket_item ADD COLUMN basket_id SERIAL NOT NULL;
ALTER TABLE basket_item ADD CONSTRAINT fk_basket FOREIGN KEY (basket_id) REFERENCES basket(id);