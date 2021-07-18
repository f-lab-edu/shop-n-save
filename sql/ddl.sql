CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    sales_count INT NOT NULL,
    seller_id BIGINT NOT NULL,
    FOREIGN KEY(seller_id)
    REFERENCES member_info(id) ON DELETE CASCADE,
    sales_yn CHAR(1) NOT NULL DEFAULT 'Y',
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX (product_name)
) engine=InnoDB default character set = utf8;

CREATE TABLE product_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sales_count INT NOT NULL DEFAULT 0,
    content VARCHAR(800),
    rating TINYINT,
    product_id BIGINT NOT NULL,
    FOREIGN KEY(product_id)
    REFERENCES product(id) ON DELETE CASCADE,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP
) engine=InnoDB default character set = utf8;