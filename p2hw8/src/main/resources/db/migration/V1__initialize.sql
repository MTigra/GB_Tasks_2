create table books (id bigserial primary key, title varchar(255), description varchar(5000), price numeric(8, 2), publish_year int);
insert into books (title, description, price, publish_year) values
('Harry Potter 1', 'Description 1', 300.0, 2000),
('Harry Potter 2', 'Description 2', 400.0, 2001),
('Harry Potter 3', 'Description 3', 500.0, 2002),
('Harry Potter 4', 'Description 4', 700.0, 2007),
('Harry Potter 5', 'Description 5', 440.0, 2004),
('Harry Potter 6', 'Description 6', 650.0, 2007),
('Harry Potter 7', 'Description 7', 200.0, 2006),
('LOTR 1', 'Description 8', 1200.0, 2006),
('LOTR 2', 'Description 9', 900.0, 2004),
('LOTR 3', 'Description 10', 600.0, 2001),
('Hobbit', 'Description 11', 500.0, 2001);

create table books_genres (book_id bigint not null, genre_id bigint not null,primary key(book_id,genre_id),
foreign key (book_id) references books(id));
insert into books_genres (book_id, genre_id) values
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(2, 3),
(4, 1),
(4, 3),
(5,1),
(6,1),
(7,1),
(8,1),
(9,2),
(10,1),
(11,1);