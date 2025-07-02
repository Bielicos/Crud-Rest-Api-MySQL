CREATE TABLE user_tb (
     user_id INT AUTO_INCREMENT PRIMARY KEY,

     username VARCHAR(50)   NOT NULL,

     email    VARCHAR(100)  NOT NULL UNIQUE,

     password VARCHAR(100)  NOT NULL,

     creation_time_stamp     DATETIME(6) NOT NULL
            DEFAULT CURRENT_TIMESTAMP(6),

     modification_time_stamp DATETIME(6) NOT NULL
            DEFAULT CURRENT_TIMESTAMP(6)
            ON UPDATE CURRENT_TIMESTAMP(6)
);
