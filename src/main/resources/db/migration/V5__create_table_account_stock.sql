-- Cria a tabela de associação ManyToMany entre contas e ações
CREATE TABLE accountStock_tb (
     accountId INT    NOT NULL,
     stockId   VARCHAR(50) NOT NULL,
     quantity  INT,
     PRIMARY KEY (accountId, stockId),
     CONSTRAINT fk_accountstock_account
         FOREIGN KEY (accountId)
             REFERENCES accounts_tb(account_id)
             ON UPDATE CASCADE
             ON DELETE CASCADE,
     CONSTRAINT fk_accountstock_stock
         FOREIGN KEY (stockId)
             REFERENCES stock_tb(stockId)
             ON UPDATE CASCADE
             ON DELETE RESTRICT
);
