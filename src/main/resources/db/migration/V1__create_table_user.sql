CREATE TABLE user_tb (
                         user_id INT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(255),
                         email VARCHAR(255),
                         password VARCHAR(255),
                         creation_time_stamp DATETIME(6) NOT NULL
    DEFAULT CURRENT_TIMESTAMP(6),
                         modification_time_stamp DATETIME(6) NOT NULL
    DEFAULT CURRENT_TIMESTAMP(6)
    ON UPDATE CURRENT_TIMESTAMP(6)
);

