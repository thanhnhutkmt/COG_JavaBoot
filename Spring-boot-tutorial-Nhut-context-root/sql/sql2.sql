drop table users;
drop table verification;
drop table status_update;
drop database springboottutorial;

create database springboottutorial;
use springboottutorial;
show tables;

desc status_update;
desc users;
desc verification;
desc profile;
insert into users(email, password) values ('Nhut@ttc.com', 'hello');
insert into users(email, password) values ('seafec2014@gmail.com', 'hello');
select * from users;
select * from verification;
select * from status_update;
select * from profile;

update profile set about='Hello this is my profile' where id=1;
select length(password) from users;
ALTER TABLE `users` ADD UNIQUE `unique_index`(`email`);

