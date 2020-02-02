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
