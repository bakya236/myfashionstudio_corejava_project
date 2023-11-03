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

  CREATE TABLE IF NOT EXISTS `colors` (
  `id` INT AUTO_INCREMENT,
  `color_name` VARCHAR(45) UNIQUE NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `colors` 
ADD `color_hex_code` VARCHAR(7) UNIQUE NOT NULL;

-- Inserting color data with names and hex codes
INSERT INTO colors (color_name, color_hex_code) VALUES
    ('Black', '#000000'),
    ('White', '#FFFFFF'),
    ('Red', '#FF0000'),
    ('Green', '#008000'),
    ('Blue', '#ADD8E6'),
    ('Yellow', '#FFFF00'),
    ('Purple', '#800080'),
    ('Pink', '#FFC0CB'),
    ('Orange', '#FFA500'),
    ('Gray', '#808080'),
    ('Brown', '#A52A2A'),
    ('Navy Blue', '#000080'),
    ('Teal', '#008080'),
    ('Lavender', '#E6E6FA'),
    ('Cyan', '#00FFFF'),
    ('Magenta', '#FF00FF'),
    ('Olive', '#808000'),
    ('Beige', '#F5F5DC'),
    ('Maroon', '#800000'),
    ('Turquoise', '#40E0D0');
    
CREATE TABLE IF NOT EXISTS `products` (
  `id` INT AUTO_INCREMENT,
  `image` VARCHAR(2048) NOT NULL,
  `name` VARCHAR(300) NOT NULL,
  `description` TEXT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) DEFAULT '1',
  `category_id` INT NOT NULL,
  `color_id` INT NOT NULL,
  `pattern` VARCHAR(100),  -- Pattern
  `fit` VARCHAR(100),      -- Fit
  `material` VARCHAR(100), -- Material
  `length` VARCHAR(100),   -- Length
  `rise_type` VARCHAR(100), -- Rise Type
  `closure_type` VARCHAR(100), -- Closure Type
  `sleeve_type` VARCHAR(100), -- Sleeve Type
  `neckline_type` VARCHAR(100), -- Neckline Type
  `occasion`  VARCHAR(100),
  `care`  VARCHAR(100),
  `stock` INT,            -- Stock
  `buyers_count` INT,     -- Buyers Count
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`)
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
  `offer` DOUBLE NOT NULL,
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

-- INSERT INTO products (image, name, description, category_id, pattern, fit, material, length, rise_type, closure_type, sleeve_type, neckline_type, occasion ,care, stock, buyers_count, color_id)
-- VALUES ('https://iili.io/HSI6dyg.webp', 'round neck t-shirt',
-- 		'Our Classic Round neck Cotton T-Shirt offers timeless style and all-day comfort. The relaxed fit and premium cotton fabric make it a versatile choice for any casual occasion.',
-- 		1, 'Solid', 'Slim Fit', 'Cotton', 'Regular', null , null, 'Short Sleeve', 'Round Neck','casual','machine wash', 100, 50, 1),
--        ('https://iili.io/HvuoZ12.webp','Urbano Fashion Men\'s Slim Fit Washed Jeans Stretchable',
--        'Care Instructions: Mild Wash, wash separately Fit Type: Slim Material: Cotton with Lycra/Elastane for Stretchability (98% Cotton , 2% Elastane) Color: Black Fit Type: Slim Fit Package Contents: 1 Jeans Occasion: Casual; Other Features: Cotton Lycra Fabric with Stretch; Solid Style; Mid-Rise; Zip Fly with Button Closure',
--        2, 'Solid', 'Slim Fit', 'Cotton', 'Regular', 'Mid-Rise', 'Button Closure', null, null,'casual','machine wash',150, 65, 3);


INSERT INTO products (image, name, description, category_id, pattern, fit, material, length, rise_type, closure_type, sleeve_type, neckline_type, occasion ,care, stock, buyers_count, color_id)
VALUES ('https://iili.io/HvByRhQ.webp', 'Typography Printed Cotton Casual T-Shirt',
		'The typography printed cotton casual t-shirt is a comfortable and stylish piece of clothing made from soft and breathable cotton fabric. It features a unique typographic design printed on the front or back of the t-shirt, adding a touch of personality and creativity to your outfit.',
		1, 'solidcolor', 'Regular Fit', 'Cotton', 'Regular', null , null, 'Short Sleeve', 'Crew Neck','casual','machine wash', 100, 50, 3),
        ('https://iili.io/HSI63ua.webp', 'Men Beige Solid Round Neck T-shirt',
		'Men Beige Solid Round Neck T-shirt is a must-have wardrobe essential for men seeking a simple yet stylish casual look. This beige t-shirt is crafted with a meticulous attention to detail, ensuring both comfort and style.',
		1, 'solidcolor', 'Regular Fit', 'Cotton', 'Regular', null , null, 'Short Sleeve', 'Crew Neck','casual','machine wash', 100, 50, 18),
         ('	https://iili.io/HSI6dyg.webp', 'Men Olive Green Raw Edge Cotton Pure Cotton T-shirt',
		'The Men Olive Green Raw Edge Cotton Pure Cotton T-shirt is a stylish and rugged piece of clothing designed for men who appreciate a laid-back and edgy aesthetic. The t-shirt features a unique combination of olive green color and raw edge detailing, adding a touch of individuality to your casual ensemble.',
		1, 'solidcolor', 'slim Fit', 'Cotton', 'Regular', null , null, 'Short Sleeve', 'Crew Neck','casual','machine wash', 100, 50, 17),
        ('https://iili.io/HvmlzOv.webp', 'Men Green & Black Slim Fit Checked Casual Shirt',
		'The Men Green & Black Slim Fit Checked Casual Shirt is a stylish and versatile piece of clothing designed for men who appreciate a modern and trendy look. This shirt features a combination of green and black colors in a checked pattern, creating a visually appealing and fashionable design.',
		2, 'checks', 'Regular Fit', 'Cotton', 'Regular', null , null, 'Long Sleeve', 'Collared Neck','casual','machine wash', 100, 50, 4),
       ('https://iili.io/HvmlVX2.webp', 'Men Maroon Pure Cotton Sustainable Casual Shirt',
		'The Men Maroon Pure Cotton Sustainable Casual Shirt is a stylish and eco-friendly clothing option designed for men who prioritize sustainability and fashion. This shirt combines a rich maroon color with a pure cotton fabric, ensuring both comfort and environmental consciousness.',
		2, 'solidcolor', 'Slim Fit', 'Cotton', 'Regular', null , null, 'Long Sleeve', 'Collared Neck','casual','machine wash', 100, 50, 19),
        ('https://iili.io/HvmlMzl.webp', 'Men Pink Slim Fit Solid Casual Shirt',
		'The Men Pink Slim Fit Solid Casual Shirt is a stylish and contemporary wardrobe essential. With its solid pink color, this shirt adds a vibrant and trendy element to your casual outfits. Designed with a slim fit silhouette, it offers a modern and tailored look that flatters the body. Made from high-quality fabric, it ensures comfort and durability. This versatile shirt can be easily paired with jeans or chinos, making it a versatile choice for various occasions. Whether you want to make a bold fashion statement or simply add a touch of color to your ensemble, this pink casual shirt is a fashionable and versatile option.',
		2, 'solidcolor', 'slim Fit', 'Cotton', 'Regular', null , null, 'Long Sleeve', 'Collared Neck','formal','machine wash', 100, 50, 8),
        ('https://iili.io/HvmMcNa.webp', 'Men Black Slim Fit Mid-Rise Clean Look Stretchable Jeans',
		'The Men Black Slim Fit Mid-Rise Clean Look Stretchable Jeans are a stylish and comfortable choice for men seeking a sleek and modern denim option. These jeans feature a slim fit and a mid-rise waist, offering a flattering and contemporary silhouette.',
		3, 'solidcolor', 'Slim Fit', 'Cotton denim', 'full-length', 'mid-rise' , 'zippers', null, null ,'casual','machine wash', 100, 50, 1),
        ('https://iili.io/HvmMaAg.webp', 'Men Black Cropped Fit low-Rise Clean Look Cropped Jeans',
		'Crafted from high-quality denim fabric, these jeans offer both style and comfort. The black color adds a sleek and versatile element to the jeans, making them suitable for a variety of occasions and outfit combinations. Black is a timeless hue that pairs well with a range of tops and shoes, allowing for effortless styling.',
		3, 'solidcolor', 'Cropped Fit', 'Cotton denim', 'full-length', 'low-rise' , 'zippers', null, null ,'casual','Dry Clean', 100, 50, 1),
		('https://iili.io/Hvuopee.webp', 'Men Black Slim Fit Mid-Rise Clean Look Jeans',
		'The Men Black Slim Fit Mid-Rise Clean Look Jeans are a versatile and stylish choice for men seeking a classic denim look. These jeans are designed with a slim fit, hugging the legs without being too tight, and featuring a mid-rise waist that sits comfortably on the hips.',
		3, 'solidcolor', 'Slim Fit', 'Cotton denim', 'full-length', 'mid-rise' , 'zippers', null, null ,'casual','machine wash', 100, 50, 1),
        ('https://iili.io/Hvm4cnp.webp', 'Women Blue Printed Round Neck T-shirt',
		'The round neck design of the t-shirt offers a classic and flattering neckline. It suits different body shapes and can be easily layered with jackets, cardigans, or statement necklaces for added style. The crew neck also gives the t-shirt a casual and relaxed vibe, perfect for everyday wear.',
		5, 'solidcolor', 'Slim Fit', 'Cotton', 'Regular', null , null, 'Short Sleeve' , 'Round Neck','casual','machine wash', 100, 50, 5),
		('https://iili.io/Hvm4YtR.webp', 'Striped crew neck T-shirt',
		'The striped pattern on the t-shirt adds visual interest and a sense of timeless elegance. Whether the stripes are horizontal or vertical, they create a flattering and elongating effect, making the t-shirt universally flattering for different body shapes. The stripes can be bold and contrasting or subtle and understated, allowing you to choose a style that suits your personal taste.',
		5, 'stripes', 'Regular Fit', 'Cotton denim', 'full-length', null, null , 'Short Sleeve' , 'crew Neck','casual','machine wash', 100, 50, 5),
        ('https://iili.io/Hvm4lMN.webp', 'Women Black Pure Cotton Printed V-Neck T-shirt',
		'The Women Navy Blue Pure Cotton Printed V-Neck T-shirt is a stylish and versatile piece that combines comfort and fashion. This t-shirt features a V-neckline and is adorned with a printed pattern in navy blue and white, adding a touch of charm and personality to your outfit.',
		5, 'solidcolor', 'Regular Fit', 'Cotton denim', 'Regular', null , null, 'Short Sleeve', 'V-neck' ,'summer','machine wash', 100, 50, 1);
        
        
        
INSERT INTO prices (price, offer, product_id, size_id)
VALUES  (259,14,1,2),
		(299,29,1,3),
        (399,30,1,4),
		(380,40,1,5),
        (263,13,2,2),
		(229,23,2,3),
        (366,10,2,4),
		(400,30,2,5),
		(478,40,3,2),
		(346,23,3,3),
        (329,20,3,5),
        (424,30,4,1),
		(462,43,4,2),
		(455,28,4,4),
        (256,4,4,5),
        (283,1,5,1),
		(390,40,5,2),
		(370,30,5,3),
        (245,4,5,4),
		(459,30,6,1),
		(367,36,6,2),
		(299,12,6,3),
        (389,40,6,4),
		(800,31,7,7),
		(1000,47,7,8),
		(900,34,7,9),
        (920,36,7,10),
		(1000,42,7,11),
		(778,36,8,7),
		(721,12,8,8),
        (891,45,8,9),
		(882,42,8,10),
		(880,40,8,11),
		(600,12,9,7),
        (589,40,9,8),
        (599,12,9,9),
        (578,40,9,10),
        (479,48,10,2),
        (480,38,10,3),
        (482,39,10,4),
        (480,39,10,5),
        (387,38,11,3),
        (345,28,11,4),
        (367,34,11,5),
        (361,32,12,2),
        (378,31,12,4),
        (345,33,12,5);

        
        
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


