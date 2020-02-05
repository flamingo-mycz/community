## 社区

## 资料
[Spring 文档](https://spring.io/guides)  
[Spring Web 文档](https://spring.io/guides/gs/serving-web-content/)  
[Git OAuth](https://developer.github.com/apps/building-oauth-apps/)  

## 工具

##快捷键
ctrl + shift + F12 -> 窗口最大化  
ctrl + P -> 查看函数  
ctrl + shift + N -> 跳转到文件  
ctrl + alt + N -> 将变量合并到上下文

##Git OAuth
[homeUrl](https://mycz.community)  
[callbackUrl](http://localhost:8080/callback)

## h2
```sql
CREATE TABLE user
(
    id int AUTO_INCREMENT PRIMARY KEY,
    account_id varchar(100),
    name varchar(50),
    token char(36),
    gmt_create bigint,
    gmt_modified bigint
);
```

## flyway
```bash
mvn flyway:migrate
```

server:
  port=8080

github.client.id=60b9e232c55203724978
github.client.secret=eabf577701f1b3e373e7b289aeb32915278887ce
github.redirect.uri=http://localhost:8080/callback

spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:E:/database/h2
spring.datasource.username=root
spring.datasource.password=123456

mybatis.configuration.map-underscore-to-camel-case=true


