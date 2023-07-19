CREATE TABLE IF NOT EXISTS blocks(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    block_title VARCHAR(255) NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT blocks_to_courses_fk FOREIGN KEY (course_id) REFERENCES courses(id)
);