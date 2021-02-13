create table member_info (
 id INTEGER AUTO_INCREMENT PRIMARY KEY,
 email VARCHAR(128),
 password VARCHAR(128),
 name VARCHAR(128),
 INDEX (email)
)engine=InnoDB default character set = utf8;
