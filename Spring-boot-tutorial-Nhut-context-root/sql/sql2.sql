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
desc profile_interests;
insert into users(email, password) values ('Nhut@ttc.com', 'hello');
insert into users(email, password) values ('seafec2014@gmail.com', 'hello');
select * from users;
select * from verification;
select * from status_update;
select * from profile;
select * from profile_interests;
select * from interests;

update profile set about='Hello this is my profile' where id=1;
select length(password) from users;
ALTER TABLE `users` ADD UNIQUE `unique_index`(`email`);

set SQL_Safe_Updates = 0;
