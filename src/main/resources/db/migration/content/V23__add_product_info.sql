insert into products_sizes (product_id, size_id)
values (1, 1),
       (1, 2),
       (1, 3);

insert into products_colours (product_id, colour_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 5);

insert into prices (price, valid_from, product_id)
-- values (79, CURRENT_TIMESTAMP, 1);
values (79, PARSEDATETIME('2022-06-09-20.57.17','yyyy-MM-dd-HH.mm.ss'), 1);

insert into prices (price, valid_from, product_id)
-- values (89, CURRENT_TIMESTAMP, 1);
values (79, PARSEDATETIME('2022-06-09-21.10.00','yyyy-MM-dd-HH.mm.ss'), 1);

insert into prices (price, valid_from, product_id)
values (119, PARSEDATETIME('2022-06-09-22.15.30','yyyy-MM-dd-HH.mm.ss'), 1);