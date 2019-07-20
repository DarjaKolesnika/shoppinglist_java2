CREATE SCHEMA IF NOT EXISTS shoppinglist DEFAULT CHARACTER SET utf8 ;
USE shoppinglist;

CREATE TABLE IF NOT EXISTS products (
  product_id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100) NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  cart_id BIGINT NULL,
  PRIMARY KEY (product_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1002;

CREATE TABLE IF NOT EXISTS carts (
                                     cart_id BIGINT NOT NULL AUTO_INCREMENT,
                                     name VARCHAR(100) NOT NULL,
                                     created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     PRIMARY KEY (cart_id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1002;

ALTER TABLE products ADD CONSTRAINT product_cart_fk FOREIGN KEY (cart_id) REFERENCES carts(cart_id);


CREATE TABLE `carts_products` (
                                  product_id BIGINT NOT NULL,
                                  cart_id BIGINT NOT NULL,
                                  PRIMARY KEY (product_id,cart_id),
                                  KEY cart_id (cart_id),
                                    CONSTRAINT product_cart_fk_1
                                        FOREIGN KEY (product_id) REFERENCES products (product_id),
                                  CONSTRAINT product_cart_fk_2
                                      FOREIGN KEY (cart_id) REFERENCES carts (cart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

