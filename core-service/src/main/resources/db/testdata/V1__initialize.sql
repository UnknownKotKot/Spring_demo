create table categories
(
    id              bigserial primary key,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp,
    title           varchar(255)
);
insert into categories (title)
values ('test_category_1'),
       ('test_category_2');

create table products
(
    id              bigserial primary key,
    title           varchar(255),
    price           int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp,
    category_id     bigint references categories (id)
);
insert into products (title, price, category_id)
values
('test_product_4', 25, 1),
('test_product_2', 80, 1),
('test_product_3', 30, 2)
;

create table users (
    id                  bigserial,
    username            varchar(30) not null unique,
    password            varchar(80) not null,
    email               varchar(50) unique,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp,
    primary key (id)
);

insert into users (username, password, email)
values
('user0', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user0@email.com'),
('user1', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user1@email.com'),
('user2', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user2@email.com');

create table roles (
    id                  serial,
    role_name           varchar(50) not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp,
    primary key (id)
);

insert into roles (role_name)
values
('ROLE_USER'), ('ROLE_ADMIN');

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

create table privileges (
    id     bigserial,
    p_name     varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

insert into privileges(id, p_name)
values
(1, 'A'),
(2, 'B');

create table roles_privileges (
    role_id                     bigint not null,
    privilege_id                bigint not null,
    primary key (role_id, privilege_id ),
    foreign key (role_id) references roles (id),
    foreign key (privilege_id) references privileges (id)
);

insert into roles_privileges(role_id, privilege_id)
values
(1, 1),
(2, 1),
(2, 2);

create table orders (
    id              bigserial,
    user_id         bigint references users(id),
    order_price     integer,
    tel_number      varchar(255),
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
    unit_price      integer,
    full_price      integer,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

insert into orders (user_id, address, tel_number, order_price)
values (1, '1111', '1111', 100);

insert into order_items (order_id, product_id, quantity, unit_price, full_price)
values (1, 1, 4, 25, 100);


