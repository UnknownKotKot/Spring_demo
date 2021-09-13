create table categories
(
    id      bigserial primary key,
    title   varchar(255)
);
insert into categories (title)
values ('Food'),
       ('NotFood');

create table products
(
    id      bigserial primary key,
    title   varchar(255),
    price   int,
    category_id bigint references categories (id)
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
    id          bigserial,
    username    varchar(30) not null unique,
    password    varchar(80) not null,
    email       varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

insert into users (username, password, email)
values
('user0', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user0@email.com'),
('user1', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user1@email.com'),
('user2', '$2a$12$hzUtHGx1y0QNeFyF7j2vKePDyS2CIvsDD96PIBydjIbNZvtqRhjja', 'user2@email.com');

create table roles (
    id              serial,
    role_name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
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
                        order_id        bigserial,
                        order_title     varchar(255),
                        order_price     bigint,
                        tel_number      varchar(255),
                        address         varchar(255),
                        created_at      timestamp default current_timestamp,
                        updated_at      timestamp default current_timestamp,
                        primary key (order_id)
);
create table order_items (
                             id     bigserial,
                             product_id       bigint          references products (id),
                             product_title    varchar(255)    references products (title),
                             quantity         bigint,
                             unit_price       bigint,
                             full_price       bigint,
                             order_id bigint references orders (order_id),
                             created_at timestamp default current_timestamp,
                             updated_at timestamp default current_timestamp,
                             primary key (id)
);


