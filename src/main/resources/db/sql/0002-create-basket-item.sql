-- liquibase formatted sql

-- changeset liquibase:2
CREATE TABLE basket_item (
  id SERIAL PRIMARY KEY,
  product_id SERIAL NOT NULL,
  amount INT NOT NULL,

  CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id)
);