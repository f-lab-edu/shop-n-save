CREATE TABLE order_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_status TINYINT DEFAULT 1,
    orderer_id BIGINT NOT NULL,
    address varchar(255),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP
) engine=InnoDB default character set = utf8;