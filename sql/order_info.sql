CREATE TABLE order_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_status TINYINT,
    orderer_id BIGINT NOT NULL,
    address varchar(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) engine=InnoDB default character set = utf8;