CREATE TABLE notification
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    notifier int NOT NULL,
    receiver int NOT NULL,
    type int NOT NULL,
    resource_id int NOT NULL,
    gmt_create bigint NOT NULL,
    status int DEFAULT 0 NOT NULL
);