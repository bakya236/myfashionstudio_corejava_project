drop database myfashionstudiodb;
Create Database if not exists myfashionstudiodb;

CREATE TABLE IF NOT EXISTS `myfashionstudiodb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT current_timestamp,
  `modified_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
  
  
  CREATE TABLE IF NOT EXISTS `myfashionstudiodb`.`genders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gender_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
      
  CREATE TABLE IF NOT EXISTS `myfashionstudiodb`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  `genders_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX  (`genders_id` ASC) VISIBLE,
    FOREIGN KEY (`genders_id`)
    REFERENCES `myfashionstudiodb`.`genders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
CREATE TABLE IF NOT EXISTS `myfashionstudiodb`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` TINYTEXT NULL,
  `categories_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`categories_id` ASC) VISIBLE,
    FOREIGN KEY (`categories_id`)
    REFERENCES `myfashionstudiodb`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
CREATE TABLE IF NOT EXISTS `myfashionstudiodb`.`sizes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(5) NULL,
  PRIMARY KEY (`id`));

    
CREATE TABLE IF NOT EXISTS `myfashionstudiodb`.`prices` (
  `id` INT NOT NULL,
  `price` DOUBLE NULL,
  `started_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `ended_at` TIMESTAMP NULL DEFAULT NULL,
  `products_id` INT NOT NULL,
  `sizes_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX(`products_id` ASC) VISIBLE,
  INDEX (`sizes_id` ASC) VISIBLE,
    FOREIGN KEY (`products_id`)
    REFERENCES `myfashionstudiodb`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`sizes_id`)
    REFERENCES `myfashionstudiodb`.`sizes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


  
  
  








