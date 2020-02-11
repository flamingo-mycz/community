## 社区

## 资料
[Spring 文档](https://spring.io/guides)  
[Spring Web 文档](https://spring.io/guides/gs/serving-web-content/)  
[Git OAuth](https://developer.github.com/apps/building-oauth-apps/)  

## 工具
Postman

##快捷键
ctrl + shift + F12 -> 窗口最大化  
ctrl + P -> 查看函数  
ctrl + shift + N -> 跳转到文件  
ctrl + alt + N -> 将变量合并到上下文

##Git OAuth
[homeUrl](https://mycz.community)  
[callbackUrl](http://localhost:8080/callback)

## h2
url:jdbc:h2:E:\database\h2
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

```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```

## 遗留问题  
1.页尾  
2.通知

## 部署
###依赖
- Git
- JDK
- Maven
- MySQL
###步骤
1: yum update
2: yum install git
3: mkdir App
4: cd App
5: git clone https://github.com/flamingo-mycz/community.git
6: yum install maven
7: mvn clean compile package
8: mvn package
9: java -jar -Dspring.profiles.active=product *.jar



 

 


