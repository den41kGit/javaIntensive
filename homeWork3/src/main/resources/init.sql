DROP TABLE IF EXISTS shops_books;
DROP TABLE IF EXISTS shops;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors
(
  author_id SERIAL primary key,
  author_name VARCHAR(50) NOT NULL,
  author_surname VARCHAR(50) NOT NULL
);

CREATE TABLE books
(
  book_id SERIAL primary key,
  author_id int REFERENCES authors(author_id),
  book_title VARCHAR(80) NOT NULL,
  book_price int NOT NULL
);

CREATE TABLE shops
(
  shop_id SERIAL primary key,
  shop_city VARCHAR(50) NOT NULL,
  shop_phone int NOT NULL
);

CREATE TABLE shops_books
(
	shop_id int REFERENCES shops(shop_id),
	book_id int REFERENCES books(book_id)
);

INSERT INTO authors (author_name, author_surname) VALUES ('Dan', 'Chern');
INSERT INTO authors (author_name, author_surname) VALUES ('Bob', 'Burn');
INSERT INTO authors (author_name, author_surname) VALUES ('Nil', 'Black');
INSERT INTO authors (author_name, author_surname) VALUES ('Gru', 'Turn');

INSERT INTO books (author_id, book_title, book_price) VALUES (2, 'Moon', 350);
INSERT INTO books (author_id, book_title, book_price) VALUES (2, 'Sun', 731);
INSERT INTO books (author_id, book_title, book_price) VALUES (1, 'Blabla', 17350);
INSERT INTO books (author_id, book_title, book_price) VALUES (1, 'Add For Blala', 200350);
INSERT INTO books (author_id, book_title, book_price) VALUES (4, 'Spice', 50);
INSERT INTO books (author_id, book_title, book_price) VALUES (3, 'Clear Sky', 1350);

INSERT INTO shops (shop_city, shop_phone) VALUES ('Minsk', 8031223);
INSERT INTO shops (shop_city, shop_phone) VALUES ('Gomel', 8032234);
INSERT INTO shops (shop_city, shop_phone) VALUES ('Brest', 8031234);

INSERT INTO shops_books(shop_id, book_id) VALUES (1, 3);
INSERT INTO shops_books(shop_id, book_id) VALUES (1, 1);
INSERT INTO shops_books(shop_id, book_id) VALUES (1, 2);
INSERT INTO shops_books(shop_id, book_id) VALUES (2, 5);
INSERT INTO shops_books(shop_id, book_id) VALUES (2, 3);
INSERT INTO shops_books(shop_id, book_id) VALUES (2, 4);
INSERT INTO shops_books(shop_id, book_id) VALUES (3, 6);
INSERT INTO shops_books(shop_id, book_id) VALUES (3, 2);
