# Fish Cloud
> 基于 Spring Cloud Greenwich 微服务框架构建<br/>
> 脱离Java语法的束缚, 使用Kotlin语言(JVM类)编写, 享受Kotlin带来的便捷<br/>
> 使用现代化的编码方式, 如Lambda、 Java Stream Api、 Kotlin 高阶函数

## 依赖环境
依赖 | 版本
---|---
Java Language | 1.8
Kotlin Language | 1.3
Mysql Database | 5.7
Redis Database | 3.2
Spring Boot | 2.1.5.RELEASE
Spring Cloud | Greenwich.SR1
Spring Platform | Cairo-SR8
Spring Security OAuth | 2.3.5.RELEASE
Undertow | 2.0.20
Mybatis Plus | 3.1.1

- 包含+项，则可运行使用更高版本
- 兼容 ```Java 11+``` 部分依赖库存在非法反射警告，但不影响功能使用
- 由于使用了 ```Undertow``` Servlet容器，默认在 ```spring-boot-starter-web``` 内屏蔽了 ```Tomcat``` <br/>
  请在 ```pom.xml``` 内导入 ```Undertow```