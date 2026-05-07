DROP DATABASE IF EXISTS bookshelf;
CREATE DATABASE bookshelf;
USE bookshelf;

DROP TABLE IF EXISTS book;

CREATE TABLE book (	
	code int PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	author VARCHAR(20) NOT NULL,
	genre VARCHAR(20) NOT NULL,
	description VARCHAR(100),
	price DECIMAL(10,2) DEFAULT 0.00,
	quantity INT DEFAULT 0
);

INSERT INTO book (name, author, genre, description, price, quantity) VALUES
	('Divina Commedia', 'Dante Alighieri', 'poem', '', 20.00, 1),
	('I promessi sposi', 'Alessandro Manzoni', 'novel', '', 15.00, 5),
	('Le ultime lettere di Jacopo Ortis', 'Ugo Foscolo', 'novel', '', 24.00, 3),
	('I Malavoglia', 'Giovanni Verga', 'novel', '', 10.00, 4);