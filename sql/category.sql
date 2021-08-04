CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parent_id INT,
    category_name VARCHAR(30),
    FOREIGN KEY(parent_id) REFERENCES category(id),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) engine=InnoDB default character set = utf8;