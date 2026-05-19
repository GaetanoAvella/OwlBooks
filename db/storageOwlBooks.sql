DROP DATABASE IF EXISTS storageOwlBooks;
CREATE DATABASE storageOwlBooks;
USE storageOwlBooks;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
	id               INT           PRIMARY KEY AUTO_INCREMENT, 
    name             VARCHAR(100)  NOT NULL,
    surname          VARCHAR(100)  NOT NULL,
	address 		 VARCHAR(255)  NOT NULL,
    email            VARCHAR(255)  NOT NULL UNIQUE,
    password         VARCHAR(255)  NOT NULL,
    admin            BOOLEAN       NOT NULL DEFAULT FALSE,
    pic				 LONGBLOB	   DEFAULT NULL,
    mime_type		 VARCHAR(50)   DEFAULT NULL
);

DROP TABLE IF EXISTS book;

CREATE TABLE book (
    id              INT           PRIMARY KEY AUTO_INCREMENT,
    code           	VARCHAR(50)   NOT NULL UNIQUE,
    name            VARCHAR(255)  NOT NULL,
    author			VARCHAR(255)  NOT NULL,
    genre           VARCHAR(100)  NOT NULL,
    price           DECIMAL(10,2) NOT NULL,
    description     VARCHAR(255),
    stock_quantity  INT           NOT NULL DEFAULT 0,
    editor          VARCHAR(150)
);

DROP TABLE IF EXISTS purchase_order;

CREATE TABLE purchase_order (
    id               INT           PRIMARY KEY AUTO_INCREMENT,
    user_id          INT           NOT NULL,
    order_code       VARCHAR(100)  NOT NULL UNIQUE,	
    order_date       DATE          NOT NULL,
    total            DECIMAL(10,2) NOT NULL,
    payment_method	 VARCHAR(50)   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
        ON DELETE RESTRICT
);

DROP TABLE IF EXISTS detail_order;

CREATE TABLE detail_order (
    id               INT           PRIMARY KEY AUTO_INCREMENT,
    order_id         INT           NOT NULL,
    book_id          INT           NOT NULL,
    quantity         INT           NOT NULL,
    price_at_purchase DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES purchase_order(id)
        ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES book(id)
        ON DELETE RESTRICT
);