CREATE TABLE notification
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    notifier int NOT NULL,
    receiver int NOT NULL,
    type int NOT NULL,
    resourceId int NOT NULL,
    gmt_create bigint NOT NULL,
    status int DEFAULT 0 NOT NULL
);