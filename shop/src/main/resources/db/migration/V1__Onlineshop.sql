CREATE TABLE IF NOT EXISTS supplier (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
 );

 CREATE TABLE IF NOT EXISTS product_category (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  description VARCHAR(300) NOT NULL,
  PRIMARY KEY (id)
 );

 CREATE TABLE IF NOT EXISTS customer (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(32) NOT NULL,
  last_name VARCHAR(32) NOT NULL,
  username VARCHAR(32) NOT NULL,
  password VARCHAR(60) NOT NULL,
  email_address VARCHAR(32) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (username),
  UNIQUE (password),
  UNIQUE (email_address)
 );

 CREATE TABLE IF NOT EXISTS location (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  address_country VARCHAR(32) NOT NULL,
  address_city VARCHAR(32) NOT NULL,
  address_county VARCHAR(32) NOT NULL,
  address_street_address VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
 );

  CREATE TABLE IF NOT EXISTS revenue (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  date DATE NOT NULL,
  sum DECIMAL NOT NULL,
  location_id INT NOT NULL,
  FOREIGN KEY ( location_id ) REFERENCES location ( id ),
  PRIMARY KEY (id)
 );

   CREATE TABLE IF NOT EXISTS product (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  description VARCHAR(300) NOT NULL,
  price DECIMAL NOT NULL,
  weight DOUBLE NOT NULL,
  category_id INT NOT NULL,
  image_url VARCHAR (300),
  FOREIGN KEY ( category_id ) REFERENCES product_category ( id ),
  supplier_id INT NOT NULL,
  FOREIGN KEY ( supplier_id ) REFERENCES supplier ( id ),
  PRIMARY KEY (id)
 );

   CREATE TABLE IF NOT EXISTS order_table (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  location_id INT NOT NULL,
  FOREIGN KEY ( location_id ) REFERENCES location ( id ),
  customer_id INT NOT NULL,
  FOREIGN KEY ( customer_id ) REFERENCES customer ( id ),
  created_at TIMESTAMP NOT NULL,
  address_country VARCHAR(32) NOT NULL,
  address_city VARCHAR(32) NOT NULL,
  address_county VARCHAR(32) NOT NULL,
  address_street_address VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
 );

 CREATE TABLE IF NOT EXISTS stock (
     product_id INT UNSIGNED NOT NULL,
     FOREIGN KEY ( product_id ) REFERENCES product ( id ),
     location_id INT UNSIGNED NOT NULL,
     FOREIGN KEY ( location_id ) REFERENCES location ( id ),
     quantity INT NOT NULL,
     PRIMARY KEY (product_id,location_id)
 );

  CREATE TABLE IF NOT EXISTS order_detail (
     product_id INT UNSIGNED NOT NULL,
     FOREIGN KEY ( product_id ) REFERENCES product ( id ),
     order_id INT UNSIGNED NOT NULL,
     FOREIGN KEY ( order_id ) REFERENCES order_table ( id ),
     quantity INT NOT NULL,
     PRIMARY KEY (product_id,order_id)
 );

