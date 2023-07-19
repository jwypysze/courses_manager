CREATE TABLE IF NOT EXISTS users(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    type ENUM('ADMIN','TEACHER','STUDENT') NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    is_active ENUM('YES','NO') NOT NULL
);