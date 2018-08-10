CREATE SCHEMA `demo_schema` DEFAULT CHARACTER SET utf8mb4 ;

##

CREATE TABLE `demo_schema`.`user_info` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `gender` VARCHAR(45) NULL,
  `date_of_birth` DATE NULL,
  `create_at` DATETIME NOT NULL,
  `modify_at` DATETIME NOT NULL,
  `create_by` VARCHAR(45) NULL,
  `modify_by` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
