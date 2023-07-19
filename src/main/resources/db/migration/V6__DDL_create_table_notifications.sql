CREATE TABLE IF NOT EXISTS notifications(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(255) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    class_id BIGINT NOT NULL,
    CONSTRAINT notifications_to_classes_fk FOREIGN KEY (class_id) REFERENCES classes(id)
);