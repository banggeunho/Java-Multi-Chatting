create database demo;
use demo;
CREATE TABLE account
( first_name varchar(250) NOT NULL,
  last_name varchar(250) NOT NULL,
  user_name varchar(250) NOT NULL,
  password varchar(250),
  email_id varchar(250) NOT NULL,
  mobile_number varchar(250) NOT NULL
);