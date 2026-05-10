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