CREATE TABLE accounts_tb (
     account_id   INT AUTO_INCREMENT PRIMARY KEY,
     description  VARCHAR(255),
     user_id      INT NOT NULL,
     FOREIGN KEY (user_id) REFERENCES user_tb(user_id) ON DELETE CASCADE
);
