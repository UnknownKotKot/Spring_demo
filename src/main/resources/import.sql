begin;

insert into products (title, cost) values ('Meat', 100), ('Sugar', 200), ('Aqua', 300), ('Bread', 400);
insert into customers (name) values ('John'), ('Tom');
insert into orders (customer, product) values  (1, 2), (1, 3), (2, 1), (2, 4);

commit;
