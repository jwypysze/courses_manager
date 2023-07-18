CREATE TABLE IF NOT EXISTS users(
    id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    type ENUM('ADMIN','TEACHER','STUDENT') NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    is_active ENUM('YES','NO') NOT NULL
);

CREATE TABLE IF NOT EXISTS registrations(
    id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    `date` DATE NOT NULL,
    user_id INT NOT NULL,
    course_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS courses(
    id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    block_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS blocks(
    id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    block_title VARCHAR(255) NOT NULL,
    class_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS classes(
    id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(255) NOT NULL,
    `date` DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS notifications(
    id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(255) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    class_id INT NOT NULL
);


ALTER TABLE registrations ADD CONSTRAINT registrations_to_users_fk FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE registrations ADD CONSTRAINT registrations_to_courses_fk FOREIGN KEY (course_id) REFERENCES courses(id);
ALTER TABLE courses ADD CONSTRAINT courses_to_blocks_fk FOREIGN KEY (block_id) REFERENCES blocks(id);
ALTER TABLE blocks ADD CONSTRAINT blocks_to_classes_fk FOREIGN KEY (class_id) REFERENCES classes(id);
ALTER TABLE notifications ADD CONSTRAINT notifications_to_classes_fk FOREIGN KEY (class_id) REFERENCES classes(id);












