create table example (id int auto_increment primary key, datetime text, expr text);

insert into example(datetime, expr) values ('Sun Apr 28 03:39:45 FET 2013', '(+ 111 222)');
insert into example(datetime, expr) values ('Sun Apr 28 04:07:01 FET 2013', '(* (/ 1 2) 2)');
