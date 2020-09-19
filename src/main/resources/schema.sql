ALTER TABLE Order_items DROP FOREIGN KEY FK_ProductOrder;
ALTER TABLE Products DROP FOREIGN KEY FK_BrandProduct;
ALTER TABLE Products DROP FOREIGN KEY FK_CategoryProduct;
ALTER TABLE Order_items DROP FOREIGN KEY FK_ProductItem;
ALTER TABLE Orders DROP FOREIGN KEY FK_CustomerOrder;

DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Brands;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Users; 
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Order_items;

CREATE TABLE IF NOT EXISTS Users (
  user_id INTEGER NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(64) NOT NULL,
  role varchar(45) NOT NULL,
  enabled tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS Customers (
  customer_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  street VARCHAR(100) NOT NULL,
  city VARCHAR(50) NOT NULL,
  post_code INTEGER (5) NOT NULL
);

CREATE TABLE IF NOT EXISTS Order_items (
  order_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  quantity INTEGER NOT NULL,
  list_price INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS Orders (
  order_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id INTEGER NOT NULL,
  order_date datetime NOT NULL
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

ALTER TABLE Orders ADD CONSTRAINT FK_CustomerOrder FOREIGN KEY (customer_id) REFERENCES Customers(customer_id);
ALTER TABLE Order_items ADD CONSTRAINT FK_ProductOrder FOREIGN KEY (order_id) REFERENCES Orders(order_id);
ALTER TABLE Order_items ADD CONSTRAINT FK_ProductItem FOREIGN KEY (product_id) REFERENCES Products(product_id);
ALTER TABLE Products ADD CONSTRAINT FK_BrandProduct FOREIGN KEY (brand_id) REFERENCES Brands(brand_id);
ALTER TABLE Products ADD CONSTRAINT FK_CategoryProduct FOREIGN KEY (category_id) REFERENCES Categories(category_id);