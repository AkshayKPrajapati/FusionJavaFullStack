-- create database
create database karnti_016_25batch; 
USE karnti_016_25batch;

-- create student table
create table student (
    s_id int primary key,
    s_name varchar(20) not null,
    contact bigint,
    location varchar(255) default 'Pune',
    dob date,
    gender enum('Male','Female','Other'),
    age int check(age>=18)
);

-- create customer table 
create table customer(
	customer_id int primary key,
	customer_name varchar(255) not null,
    customer_mob bigint not null,
    customer_email varchar(255) not null,
    customer_address varchar(255) default'Pune'
);
