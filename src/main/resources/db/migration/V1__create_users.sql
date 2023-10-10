drop database bakyalakshmi_angappan_corejava_project;
create database bakyalakshmi_angappan_corejava_project;
USE bakyalakshmi_angappan_corejava_project;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` BIGINT NOT NULL,
  `password` VARCHAR(240) NOT NULL,
  `created_at` TIMESTAMP DEFAULT current_timestamp,
  `modified_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE(`email`)
  );
  
   INSERT INTO users (user_name,email,phone_number,password)
VALUES ('bakyalakshmi','bakyalakshmi623@gmail.com',9789853625,'9789@623bB');


UPDATE users
SET password = SHA2('9789@623bB',256)
WHERE id = 1;

CREATE TABLE IF NOT EXISTS `addresses` (
  `id` INT AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `land_mark` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `pincode` INT NOT NULL, 
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `status` boolean DEFAULT true,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);


  
  CREATE TABLE IF NOT EXISTS `genders` (
  `id` INT AUTO_INCREMENT,
  `gender_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE(`gender_name`)
  );

  CREATE TABLE IF NOT EXISTS `categories` (
  `id` INT AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  `gender_id` INT NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`gender_id`)
    REFERENCES `genders` (`id`),
	CONSTRAINT unique_category_gender UNIQUE (category_name, gender_id)
);
    
    
CREATE TABLE IF NOT EXISTS `products` (
  `id` INT AUTO_INCREMENT,
  `image` VARCHAR(2048) NOT NULL,
  `name` VARCHAR(300) NOT NULL,
  `description` TEXT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`status` tinyint(1) DEFAULT '1',
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`)
    );
    
    
CREATE TABLE IF NOT EXISTS `sizes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE(`value`) 
  );
    
CREATE TABLE IF NOT EXISTS `prices` (
  `id` INT AUTO_INCREMENT,
  `price` DOUBLE NOT NULL,
  `started_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `ended_at` TIMESTAMP DEFAULT NULL,
  `product_id` INT NOT NULL,
  `size_id` INT NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`product_id`)  
   REFERENCES `products` (`id`),    
   FOREIGN KEY (`size_id`)   
   REFERENCES `sizes` (`id`));
   
   

-- CREATE TABLE IF NOT EXISTS `bag` (
--   `id` INT AUTO_INCREMENT,
--   `item_id`
--   `created_at` TIMESTAMP DEFAULT current_timestamp,
--   `modified_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--   `status` tinyint(1) DEFAULT '1',
--   PRIMARY KEY (`id`),
--   UNIQUE(`email`)
--   );
INSERT INTO genders (gender_name)
VALUES ('Men'),
		('Women');

INSERT INTO categories (category_name, gender_id)
VALUES ('T-shirts', 1),
       ('Shirts', 1),
       ('Jeans', 1),
       ('Trousers', 1),
       ('T-shirts', 2),
       ('Shirts', 2),
       ('Jeans', 2),
       ('Skirts', 2);

INSERT INTO sizes (value)
VALUES ('Xs'),
       ('S'),
       ('M'),
       ('L'),
       ('XL'),
       ('XXL'),
       ('28'),
       ('32'),
       ('36'),
       ('38'),
       ('40');




INSERT INTO products (image, name, description, category_id)
VALUES ('https://iili.io/HSI6dyg.webp', 'round neck t-shirt', 'Our Classic Round neck Cotton T-Shirt offers timeless style and all-day comfort. The relaxed fit and premium cotton fabric make it a versatile choice for any casual occasion.', 1),
       ('https://iili.io/HvzmXQj.webp', 'Solid Slim Fit Cotton Casual Shirt', 'Care Instructions: Machine Wash
Fit Type: Slim Fit
Fabric - 100% Premium Cotton, Pre-Washed for an extremely soft finish and Guaranteed 0% Shrinkage Post Washing
Style - Enhance your look by wearing this Casual Stylish Men\'s shirt, Team it with a pair of tapered denimsOr Solid Chinos and Loafers for a fun Smart Casual look
Fit Type - Modern Slim Fit: A good balance between Comfort and Slim Look, the shirt is Slim around the shoulders and Chest and a little Loose around the Waist for optimum Comfort. Size chart - S-38, M-40, L-42, XL-44, XXL-46. Please Check the Size chart before Ordering for the Perfect Fit
About the Brand DENNIS LINGO - Finding Basic Menswear for daily use can be hard among today\'s Overpriced Fast fashion world, where trends change daily. That’s why we started Dennis Lingo, to create a one-stop shop for premium essential clothing for everyday use at the lowest prices and bring Basics back in trend.', 2),
       ('https://iili.io/HvzmWhb.webp','Men\'s Solid Slim Fit Cotton Casual Shirt with Spread Collar & Full Sleeves','Care Instructions: Machine Wash
Fit Type: Slim Fit
Fabric - 100% Premium Cotton, Pre-Washed for extremely soft finish and Guaranteed 0% Shrinkage Post Washing
Style - Enhance your look by wearing this Casual Stylish Men\'s shirt, Team it with a pair of tapered denimsOr Solid Chinos and Loafers for a fun Smart Casual look
Fit Type - Modern Slim Fit: A good balance between Comfort and Slim Look, the shirt is Slim around the shoulders and Chest and little Loose around the Waist for optimum Comfort. Size chart - S-38, M-40, L-42, XL-44, XXL-46. Please Check the Size chart before Ordering for the Perfect Fit
About the Brand DENNIS LINGO - Finding Basic Menswear for daily use can be hard among today\'s Overpriced Fast fashion world, where trends change daily. That’s why we started Dennis Lingo, to create a one-stop shop for premium essential clothing for everyday use at the lowest prices and bring Basics back in trend.', 2),
       ('https://iili.io/HvIgapV.webp','Slim Fit Stretchable Jeans','Care Instructions: Machine Wash
Slim fit - Slightly fitted through the hip and thigh, narrows from knee to ankle
Material - 98% Cotton & 2% Elastane; Stretchable Jeans
Zip fly with button closure
Basic 5 Pocket Jeans for Men; Scoop Pocket
Type of Wash - Mid Wash
Cotton Lycra Fabric with Stretch - To give you extra comfort and agility
Solid; Perfect for casual, party & office wear
Disclaimer: Product colour may slightly vary due to photographic lighting sources or your monitor settings',3),
       ('https://iili.io/HvuoZ12.webp','Urbano Fashion Men\'s Slim Fit Washed Jeans Stretchable','Care Instructions: Mild Wash, wash separately
Fit Type: Slim
Material: Cotton with Lycra/Elastane for Stretchability (98% Cotton , 2% Elastane)
Color: Black
Fit Type: Slim Fit
Package Contents: 1 Jeans
Occasion: Casual; Other Features: Cotton Lycra Fabric with Stretch; Solid Style; Mid-Rise; Zip Fly with Button Closure',3);


INSERT INTO prices (price, product_id, size_id)
VALUES(566,1,2),
		(548,1,3),
        (489,1,4),
		(490,1,5),
        (455,2,2),
        (426,2,3),
        (415,2,4),
        (405,3,1),
        (495,3,2),
        (473,3,3),
        (413,3,4),
		(567,4,8),
		(560,4,7),
        (520,4,10),
		(510,4,9),
        (786,5,6),
        (816,5,7),
        (716,5,8),
        (746,5,9);



   
CREATE TABLE IF NOT EXISTS `orders` (
  `id` INT AUTO_INCREMENT,
  `order_code` varchar(300) NOT NULL,
  `total_price` DOUBLE NOT NULL,
  `user_id` INT NOT NULL, -- Assuming user_id is an integer
  `delivery_address_id` INT NOT NULL,
  `ordered_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `delivered_at` TIMESTAMP ,
  `delivered` TINYINT(1) DEFAULT 0, -- Default to 0 for not delivered
  `is_active` TINYINT(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE(`order_code`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`delivery_address_id`) REFERENCES `addresses` (`id`) 
);

CREATE TABLE IF NOT EXISTS `order_items` (
  `id` INT AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `price_id` INT NOT NULL,
  `quantity` INT NOT NULL,
   `status` VARCHAR(45) DEFAULT 'on the way',
   `is_cancel`  boolean DEFAULT FALSE,
   `cancel_reason` VARCHAR(45) NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`order_id`)  
   REFERENCES `orders` (`id`),
   FOREIGN KEY (`product_id`)  
   REFERENCES `products` (`id`),
	FOREIGN KEY (`price_id`)  
   REFERENCES `prices` (`id`)
   );


-- DELIMITER //
-- CREATE TRIGGER update_delivered_status
-- BEFORE INSERT ON `orders` FOR EACH ROW
-- BEGIN
--   SET NEW.delivered = IF(NEW.delivered_at >= DATE_ADD(NEW.ordered_at, INTERVAL 2 DAY), 1, 0);
-- END;
-- //
-- DELIMITER ;


