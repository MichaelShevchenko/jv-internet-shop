CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet_shop`.`products` (
                                           `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                           `product_name` VARCHAR(225) NOT NULL,
                                           `price` DOUBLE NOT NULL,
                                           `deleted` TINYINT(1) NOT NULL DEFAULT 0,
                                           PRIMARY KEY (`product_id`));
CREATE TABLE `internet_shop`.`roles` (
                                         `role_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                         `role_name` VARCHAR(225) NOT NULL,
                                         `deleted` TINYINT(1) NOT NULL DEFAULT 0,
                                         PRIMARY KEY (`role_id`),
                                         UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE);
CREATE TABLE `internet_shop`.`users` (
                                         `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                         `user_name` VARCHAR(225) NOT NULL,
                                         `login` VARCHAR(225) NOT NULL,
                                         `password` VARCHAR(225) NOT NULL,
                                         `salt` VARBINARY(1024) NOT NULL,
                                         `deleted` TINYINT(1) NOT NULL DEFAULT 0,
                                         PRIMARY KEY (`user_id`),
                                         UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);
CREATE TABLE `internet_shop`.`users_roles` (
                                               `user_id` BIGINT(11) NOT NULL,
                                               `role_id` BIGINT(11) NOT NULL,
                                               INDEX `users_fk_idx` (`user_id` ASC) VISIBLE,
                                               INDEX `roles_fk_idx` (`role_id` ASC) VISIBLE,
                                               CONSTRAINT `roles_fk`
                                                   FOREIGN KEY (`role_id`)
                                                       REFERENCES `internet_shop`.`roles` (`role_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `users_fk`
                                                   FOREIGN KEY (`user_id`)
                                                       REFERENCES `internet_shop`.`users` (`user_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION);
CREATE TABLE `internet_shop`.`shopping_carts` (
                                                  `cart_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                                  `user_id` BIGINT(11) NOT NULL,
                                                  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
                                                  PRIMARY KEY (`cart_id`),
                                                  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
                                                  CONSTRAINT `user_cart_fk`
                                                      FOREIGN KEY (`user_id`)
                                                          REFERENCES `internet_shop`.`users` (`user_id`)
                                                          ON DELETE NO ACTION
                                                          ON UPDATE NO ACTION);
CREATE TABLE `internet_shop`.`shopping_carts_products` (
                                                           `cart_id` BIGINT(11) NOT NULL,
                                                           `product_id` BIGINT(11) NOT NULL,
                                                           INDEX `cart_carts_products_fk_idx` (`cart_id` ASC) VISIBLE,
                                                           INDEX `product_carts_products_fk_idx` (`product_id` ASC) VISIBLE,
                                                           CONSTRAINT `cart_carts_products_fk`
                                                               FOREIGN KEY (`cart_id`)
                                                                   REFERENCES `internet_shop`.`shopping_carts` (`cart_id`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION,
                                                           CONSTRAINT `product_carts_products_fk`
                                                               FOREIGN KEY (`product_id`)
                                                                   REFERENCES `internet_shop`.`products` (`product_id`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION);
CREATE TABLE `internet_shop`.`orders` (
                                          `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                          `user_id` BIGINT(11) NOT NULL,
                                          `deleted` TINYINT(1) NOT NULL DEFAULT 0,
                                          PRIMARY KEY (`order_id`),
                                          INDEX `user_orders_fk_idx` (`user_id` ASC) VISIBLE,
                                          CONSTRAINT `user_orders_fk`
                                              FOREIGN KEY (`user_id`)
                                                  REFERENCES `internet_shop`.`users` (`user_id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION);
CREATE TABLE `internet_shop`.`orders_products` (
                                                   `order_id` BIGINT(11) NOT NULL,
                                                   `product_id` BIGINT(11) NOT NULL,
                                                   INDEX `order_orders_products_fk_idx` (`order_id` ASC) VISIBLE,
                                                   INDEX `product_orders_products_fk_idx` (`product_id` ASC) VISIBLE,
                                                   CONSTRAINT `order_orders_products_fk`
                                                       FOREIGN KEY (`order_id`)
                                                           REFERENCES `internet_shop`.`orders` (`order_id`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION,
                                                   CONSTRAINT `product_orders_products_fk`
                                                       FOREIGN KEY (`product_id`)
                                                           REFERENCES `internet_shop`.`products` (`product_id`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION);
INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('ADMIN');
INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('USER');
