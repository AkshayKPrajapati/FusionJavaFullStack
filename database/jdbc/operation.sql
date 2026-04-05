create database 016_25batch;
use 016_25batch;

create table employee(
    id int primary key,
    name varchar(20),
    salary decimal(10,2)
);


insert into employee(id, name, salary) values(113, 'Poonam', 4582233.36);
insert into employee(id, name, salary) values(114, 'Akshay', 5000000.00);
insert into employee(id, name, salary) values(115, 'Rahul', 6000000.00);
insert into employee(id, name, salary) values(116, 'Raj', 7000000.00);
insert into employee(id, name, salary) values(117, 'Ravi', 8000000.00);
insert into employee(id, name, salary) values(1001, 'Neha', 2500.00);

select * from employee;
truncate table employee;

select * from employee where id=1001;
update employee set salary=salary+100000 where id=1001; -- update query

delete from employee where id=1001; -- delete query
DELETE FROM `016_25batch`.`employee` WHERE (`id` = '1001');

-- store procedure 
DELIMITER //

CREATE PROCEDURE getemployeeInfoById(
    IN emp_id INT,
    OUT emp_name VARCHAR(20),
    OUT emp_salary DECIMAL(10,2)
)
BEGIN
    SELECT name, salary
    INTO emp_name, emp_salary
    FROM employee
    WHERE id = emp_id;
END //

DELIMITER ;
-- call function
CALL getemployeeInfoById(1003, @name, @salary);
SELECT @name, @salary;


-- Alter table employee add constraint primary key (id);