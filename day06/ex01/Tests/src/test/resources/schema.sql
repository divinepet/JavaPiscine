create table product (
    identifier integer identity primary key,
    name varchar(20) not null unique,
    price integer not null
);