CREATE TABLE order_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    count INT,
    order_price BIGINT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(order_id)
        REFERENCES order_info(id),
    FOREIGN KEY(product_id)
        REFERENCES product(id)
) engine=InnoDB default character set = utf8;