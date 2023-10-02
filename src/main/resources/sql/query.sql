DROP DATABASE IF EXISTS carhire;
CREATE DATABASE IF NOT EXISTS carhire;
USE carhire;

CREATE TABLE category(
    typeId varchar(15) primary key,
    type varchar(50) not null,
    description text not null
);

CREATE TABLE customer(
    id varchar(15) PRIMARY KEY,
    national_id varchar(15) UNIQUE NOT NULL,
    first_name varchar(50)not null,
    middle_name varchar(50)not null,
    last_name varchar(50),
    address text not null,
    phone_num varchar(15) not null
);

CREATE TABLE car(
    car_id varchar(15) PRIMARY KEY,
    number varchar(15) UNIQUE NOT NULL,
    typeId varchar(15),
    year INT(4)not null,
    brand varchar(25)not null,
    colour varchar(30)not null,
    FOREIGN KEY (typeId)REFERENCES category(typeId)
);

CREATE TABLE rent(
    rent_id varchar(30)PRIMARY KEY,
    id varchar(15),
    car_id varchar(15),
    current_date_time TIMESTAMP,
   // due_date
    FOREIGN KEY(id) REFERENCES customer (id),
    FOREIGN KEY(car_id) REFERENCES car(car_id)
);

CREATE TABLE user(
    user_id varchar(30) PRIMARY KEY,
    employee_id varchar(30) ,
    user_name varchar(50),
    password varchar(200),
    FOREIGN KEY(employee_id) REFERENCES employee(employee_id)
);

CREATE TABLE employee(
    employee_id varchar(30)PRIMARY KEY,
    national_id varchar(30) UNIQUE NOT NULL,
    first_name varchar(50) not null,
    last_name varchar(50)not null,
    DOB varchar(20)not null,
    address text not null,
    phone_num varchar(50)not null,
    salary varchar(20)not null
);




