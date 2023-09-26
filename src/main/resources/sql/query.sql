DROP DATABASE IF EXISTS carhire;
CREATE DATABASE IF NOT EXISTS carhire;
USE carhire;

CREATE TABLE category(
    typeId varchar(15) primary key,
    type varchar(50) not null,
    description text not null
);
CREATE TABLE customer(
    id varchar(15),
    national_id varchar(15),
    first_name varchar(50)not null,
    middle_name varchar(50)not null,
    last_name varchar(50),
    address text not null,
    phone_num varchar(15) not null,
    PRIMARY KEY(id,national_id)
);
CREATE TABLE car(
    car_id varchar(15),
    number varchar(15),
    typeId varchar(15),
    year INT(4)not null,
    brand varchar(25)not null,
    colour varchar(30)not null,
    PRIMARY KEY(car_id,number),
    FOREIGN KEY (typeId)REFERENCES category(typeId)
);




