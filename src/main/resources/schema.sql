ALTER TABLE Products DROP CONSTRAINT FK_BrandProduct FOREIGN KEY (brand_id) REFERENCES Brands(brand_id);
ALTER TABLE Products DROP CONSTRAINT FK_CategoryProduct FOREIGN KEY (category_id) REFERENCES Categories(category_id);

DROP TABLE IF EXISTS Brands;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Users; 

CREATE TABLE IF NOT EXISTS Users (
  user_id INTEGER NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(64) NOT NULL,
  role varchar(45) NOT NULL,
  enabled tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS Brands (
	brand_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Categories (
	category_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	category_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Products (
	product_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product_name VARCHAR(255) NOT NULL,
	model_year INTEGER NOT NULL,
	list_price DECIMAL (10, 2) NOT NULL,
	quantity INTEGER NOT NULL,
	brand_id INTEGER NOT NULL,
	category_id INTEGER NOT NULL,
	imgUrl varchar(255)
);

DROP TABLE IF EXISTS Brands;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Users; 

CREATE TABLE IF NOT EXISTS Users (
  user_id INTEGER NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(64) NOT NULL,
  role varchar(45) NOT NULL,
  enabled tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS Brands (
	brand_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Categories (
	category_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	category_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Products (
	product_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product_name VARCHAR(255) NOT NULL,
	model_year INTEGER NOT NULL,
	list_price DECIMAL (10, 2) NOT NULL,
	quantity INTEGER NOT NULL,
	brand_id INTEGER NOT NULL,
	category_id INTEGER NOT NULL,
	imgUrl varchar(255)
);

ALTER TABLE Products ADD CONSTRAINT FK_BrandProduct FOREIGN KEY (brand_id) REFERENCES Brands(brand_id);
ALTER TABLE Products ADD CONSTRAINT FK_CategoryProduct FOREIGN KEY (category_id) REFERENCES Categories(category_id);