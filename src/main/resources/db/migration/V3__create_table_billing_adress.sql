-- Cria a tabela de endereço de cobrança com PK compartilhada via MapsId
CREATE TABLE billingAdress_tb (
        accountId INT PRIMARY KEY,
        street    VARCHAR(255),
        number    INT,
        CONSTRAINT fk_billingaddress_account
          FOREIGN KEY (accountId)
              REFERENCES accounts_tb(account_id)
              ON UPDATE CASCADE
              ON DELETE CASCADE
);
