CREATE TABLE IF NOT EXISTS registrations(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    `date` DATE NOT NULL,
    `time` TIME NOT NULL,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT registrations_to_users_fk FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT registrations_to_courses_fk FOREIGN KEY (course_id) REFERENCES courses(id)
);