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