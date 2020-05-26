CREATE TABLE IF NOT EXISTS tbl_account_holder(
    account_number BIGINT generated always as identity (START WITH 10000),
    account_first_name VARCHAR(30),
    account_last_name VARCHAR(30),
    date_of_birth DATE,
    account_type VARCHAR(9),
    account_start_date TIMESTAMP,
    account_balance FLOAT,
    mobile_number VARCHAR(10),
    address VARCHAR(255),
    PRIMARY KEY (account_first_name,account_last_name,date_of_birth)

);

CREATE TABLE IF NOT EXISTS tbl_transaction(
    account_number BIGINT NOT NULL,
    deposit_amount FLOAT,
    deposit_type VARCHAR(30),
    source_account_number BIGINT,
    transaction_date TIMESTAMP,
    withdraw_amount FLOAT,
    withdraw_type VARCHAR(30),
    transaction_id VARCHAR(255)

);
