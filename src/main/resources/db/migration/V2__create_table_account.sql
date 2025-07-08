-- Cria a tabela de contas e a FK para user_tb
CREATE TABLE accounts_tb (
     account_id   INT AUTO_INCREMENT PRIMARY KEY,
     description  VARCHAR(255),
     user_id      INT NOT NULL,
     CONSTRAINT fk_accounts_user
         FOREIGN KEY (user_id)
             REFERENCES user_tb(user_id)
             ON UPDATE CASCADE
             ON DELETE RESTRICT
);
