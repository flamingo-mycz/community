CREATE TABLE comment
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    parent_id int NOT NULL,
    type int NOT NULL,
    commentator int NOT NULL,
    content varchar(1024),
    gmt_create bigint ,
    gmt_modified bigint ,
    like_count int DEFAULT 0
);