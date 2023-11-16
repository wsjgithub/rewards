DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
                         id int NOT NULL AUTO_INCREMENT,
                         name text,
                         username varchar(40),
                         PRIMARY KEY (id),
                         CONSTRAINT unique (username)
);

CREATE TABLE transactions (
                       id int NOT NULL AUTO_INCREMENT,
                       value int,
                       time datetime,
                       customer_id int,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_customer foreign key (customer_id) references customers(id) on delete no action
);
