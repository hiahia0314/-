CREATE TABLE IF NOT EXISTS Event (
    user INT,
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) ,
    uid INT,
    description VARCHAR(255),
    type VARCHAR(255),
    date date
);

CREATE TABLE  IF NOT EXISTS user(
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    age INT,
    e_mail VARCHAR(255)
)