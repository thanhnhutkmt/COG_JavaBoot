use springboottutorial;
show tables;
drop table users;

desc users;
insert into users(email, password) values ('Nhut@ttc.com', 'hello');
select * from users;
select length(password) from users;