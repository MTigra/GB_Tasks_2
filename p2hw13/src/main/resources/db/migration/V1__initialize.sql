create table books (
 id                   bigserial primary key,
 title                varchar(255),
 description          varchar(5000),
 price                numeric(8, 2),
 publish_year         int
);

insert into books
(title, description, price, publish_year)
values
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

create table books_genres(
 book_id        bigint not null,
 genre_id       bigint not null,
 primary        key(book_id,genre_id),
 foreign        key(book_id) references books(id)
);

insert into books_genres (book_id, genre_id) values
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(2, 2),
(4, 1),
(4, 3),
(5,1),
(6,1),
(7,1),
(8,1),
(9,2),
(10,3),
(11,1);

create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary    key (user_id, role_id),
  foreign    key (user_id) references users (id),
  foreign    key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('DELETE_USERS_PERMISSION');

insert into users (username, password, email)
values
('Bob Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('John Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 3);


create table orders (
id bigserial,
user_id bigint not null,
total_price numeric(8, 2) not null,
status varchar(20),
primary key(id),
foreign key (user_id) references users (id));

create table orders_items (
id bigserial,
order_id bigint not null,
book_id bigint not null,
quantity int,
total_price numeric(8, 2),
primary key(id),
foreign key (book_id) references books (id),
foreign key (order_id) references orders (id));
