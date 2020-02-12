CREATE TABLE user
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    account_id varchar(100) NOT NULL,
    name varchar(50) NOT NULL,
    token char(64) NOT NULL,
    avatar_url varchar(128) NOT NULL,
    bio varchar(256) ,
    gmt_create bigint,
    gmt_modified bigint
);