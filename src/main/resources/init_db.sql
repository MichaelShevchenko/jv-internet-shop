CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet_shop`.`products` (
                                           `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                           `product_name` VARCHAR(225) NOT NULL,
                                           `price` DOUBLE NOT NULL,
                                           `deleted` TINYINT(1) NOT NULL DEFAULT 0,
                                           PRIMARY KEY (`product_id`));
