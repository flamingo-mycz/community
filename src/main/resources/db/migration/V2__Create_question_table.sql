CREATE TABLE question
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    title varchar(64) NOT NULL,
    description text NOT NULL,
    gmt_create bigint,
    gmt_modified bigint,
    creator int NOT NULL,
    comment_count int DEFAULT 0,
    view_count int DEFAULT 0,
    like_count int DEFAULT 0,
    tag varchar(256)
);