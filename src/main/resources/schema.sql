DROP TABLE IF EXISTS loans;
CREATE TABLE IF NOT EXISTS loans(
    id BIGINT AUTO_INCREMENT,
    loan_no VARCHAR(255),
    customer_acct_no VARCHAR(255),
    disbursement_date DATE,
    outstanding_amount NUMERIC,
    status VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS customers;
CREATE TABLE IF NOT EXISTS customers(
    id BIGINT AUTO_INCREMENT,
    customer_acct_no VARCHAR(10),
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    status VARCHAR(20),
    PRIMARY KEY (id)
);