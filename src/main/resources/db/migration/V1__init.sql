create table categories
(
    id              bigserial primary key,
    title           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);
insert into categories (title)
values ('Food'),
       ('NotFood');

create table products
(
    id              bigserial primary key,
    title           varchar(255),
    price           int,
    category_id     bigint references categories (id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id)
values
('Bread', 25, 1),
('Milk', 80, 1),
('Aqua', 30, 1),
('Salt', 20, 1),
('Sugar', 40, 1),
('Potato', 16, 1),
('Tomato', 256, 1),
('Cucumber', 140, 1),
('Tuna', 2000, 1),
('Meat', 1100, 1),
('Table', 831230, 2),
('Chair', 8320, 2),
('Computer', 321312, 2),
('Ball', 87680, 2),
('Telephone', 450, 2),
('USB', 7680, 2),
('Pen', 5450, 2),
('HDD', 3210, 2),
('SDD', 9870, 2),
('MotherBoard', 49870, 2);

create table users (
    id                  bigserial,
    first_name          varchar(80) not null,
    last_name           varchar(80) not null,
    username            varchar(30) not null unique,
    password            varchar(80) not null,
    email               varchar(50) unique,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp,
    primary key (id)
);

insert into users (username, first_name, last_name, password, email)
values
('user0', 'name0', 'name00', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user0@email.com'),
('user1', 'name1', 'name11', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user1@email.com'),
('user2', 'name2', 'name22', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user2@email.com');

create table roles (
    id                  serial,
    role_name           varchar(50) not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp,
    primary key (id)
);

insert into roles (role_name)
values
('ROLE_USER'),
('ROLE_ADMIN');

create table users_roles (
    user_id     bigint not null,
    role_id     bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 1),
(3, 2);

create table orders (
    id              bigserial,
    user_id         bigint references users(id),
    price     integer,
    phone      varchar(255),
    address         varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp,
    primary key (id)
);
create table order_items (
    id              bigserial,
    order_id          bigint references orders (id),
    product_id        bigint references products (id),
    quantity        integer,
    price_per_product      integer,
    price      integer,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

insert into orders (user_id, address, phone, price)
values (1, '1111', '1111', 100);

insert into order_items (order_id, product_id, quantity, price_per_product, price)
values (1, 1, 4, 25, 100);


