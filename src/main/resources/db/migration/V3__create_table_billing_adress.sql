CREATE TABLE billing_address_tb (
        account_id INT NOT NULL,
        street    VARCHAR(255),
        number    INT,
        PRIMARY KEY (account_id),
        FOREIGN KEY (account_id) REFERENCES accounts_tb(account_id)
);
