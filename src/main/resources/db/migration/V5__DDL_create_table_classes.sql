CREATE TABLE IF NOT EXISTS classes(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(255) NOT NULL,
    `date` DATETIME NOT NULL,
    block_id BIGINT NOT NULL,
    CONSTRAINT classes_to_blocks_fk FOREIGN KEY (block_id) REFERENCES blocks(id)
);