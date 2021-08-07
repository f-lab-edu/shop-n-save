CREATE TABLE order_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_status TINYINT,
    orderer_id BIGINT NOT NULL,
    receiver_name VARCHAR(30) NOT NULL,
    receiver_phone VARCHAR(30) NOT NULL,
    receiver_address varchar(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) engine=InnoDB default character set = utf8;