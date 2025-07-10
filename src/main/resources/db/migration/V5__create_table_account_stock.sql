CREATE TABLE account_stock_tb (
     account_id INT    NOT NULL,
     stock_id   VARCHAR(50) NOT NULL,
     quantity  INT,
     PRIMARY KEY (account_id, stock_id),
     FOREIGN KEY (account_id) REFERENCES accounts_tb(account_id),
     FOREIGN KEY (stock_id) REFERENCES stock_tb(stock_id)
);
