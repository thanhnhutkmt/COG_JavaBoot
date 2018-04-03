drop table users;
drop table verification;
drop table status_update;
drop database springboottutorial;

create database springboottutorial;
use springboottutorial;
show tables;

desc users;
desc verification;
insert into users(email, password) values ('Nhut@ttc.com', 'hello');
select * from users;
select length(password) from users;

ALTER TABLE `users` ADD UNIQUE `unique_index`(`email`);

select * from verification;