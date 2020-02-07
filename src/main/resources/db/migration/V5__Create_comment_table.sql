CREATE TABLE comment
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    parent_id int NOT NULL,
    type int NOT NULL,
    commentator int NOT NULL,
    gmt_create bigint NOT NULL,
    gmt_modified bigint NOT NULL,
    like_count int DEFAULT 0
);