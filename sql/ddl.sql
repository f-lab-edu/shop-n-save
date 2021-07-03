create table MEMBER_INFO (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 email VARCHAR(255),
 password VARCHAR(64),
 name VARCHAR(30),
 create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
 role TINYINT DEFAULT 3,
 INDEX (email)
)engine=InnoDB default character set = utf8;

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