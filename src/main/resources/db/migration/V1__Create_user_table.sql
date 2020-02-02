CREATE TABLE user
(
    id int AUTO_INCREMENT PRIMARY KEY,
    account_id varchar(100),
    name varchar(50),
    token char(36),
    gmt_create bigint,
    gmt_modified bigint
);