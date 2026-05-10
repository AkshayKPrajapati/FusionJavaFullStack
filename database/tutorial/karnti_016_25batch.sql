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

-- create location table 
create table location (
	location_id int primary key,
    city varchar(20),
    state varchar(20),
    pin_code char(6)
);

-- create department table
create table department(
	dept_id int primary key,
    dept_name varchar(20) not null,
    loc_id int ,
    constraint dept_loc_fk foreign key(loc_id) references location(location_id)
);


-- create table employeeFussion
CREATE TABLE employeeFussion (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(15) NOT NULL,
    salary CHAR(8),
    
    dept_id INT,
    CONSTRAINT empfusion_dept_fk
        FOREIGN KEY (dept_id)
        REFERENCES department(dept_id),
    
    manager_id INT,
    CONSTRAINT empfusion_manager_fk
        FOREIGN KEY (manager_id)
        REFERENCES employeeFussion(emp_id)
);
 
-- craete product tabel
create table product(
	product_id int primary key,
    product_name varchar (20) not null,
    product_price decimal(10,2),
    produce_create timestamp
);

-- create order table 
CREATE TABLE orders (
    order_id INT PRIMARY KEY,

    order_status ENUM(
        'pending',
        'confirmed',
        'processing',
        'packed',
        'shipped',
        'out_for_delivery',
        'delivered',
        'cancelled',
        'returned',
        'refunded',
        'failed',
        'ordernow'
    ),

    customer_id INT,
    CONSTRAINT orders_customer_fk
        FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id),

    product_id INT,
    CONSTRAINT orders_product_fk
        FOREIGN KEY (product_id)
        REFERENCES product(product_id)
);