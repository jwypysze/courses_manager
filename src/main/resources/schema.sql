CREATE TABLE IF NOT EXISTS users(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    type ENUM('ADMIN','TEACHER','STUDENT') NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    is_active ENUM('YES','NO') NOT NULL
);

CREATE TABLE IF NOT EXISTS courses(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS registrations(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    `date` DATETIME NOT NULL,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT registrations_to_users_fk FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT registrations_to_courses_fk FOREIGN KEY (course_id) REFERENCES courses(id)
);



CREATE TABLE IF NOT EXISTS blocks(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    block_title VARCHAR(255) NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT blocks_to_courses_fk FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS classes(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(255) NOT NULL,
    `date` DATETIME NOT NULL,
    block_id BIGINT NOT NULL,
    CONSTRAINT classes_to_blocks_fk FOREIGN KEY (block_id) REFERENCES blocks(id)
);

CREATE TABLE IF NOT EXISTS notifications(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(255) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    class_id BIGINT NOT NULL,
    CONSTRAINT notifications_to_classes_fk FOREIGN KEY (class_id) REFERENCES classes(id)
);

CREATE TABLE IF NOT EXISTS users_notifications(
    users_id BIGINT NOT NULL,
    notifications_id BIGINT NOT NULL
);











