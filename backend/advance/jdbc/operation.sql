-- create database
CREATE DATABASE 016_25batch;
use 016_25batch;

create table employee(
id int primary key,
name varchar(20),
salary decimal(10,2)
);

INSERT INTO 016_25batch.employee
(
id, name, salary
)
VALUES (
113, 'Poonam', 4582233.36
);