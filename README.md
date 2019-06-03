[![star](https://img.shields.io/github/stars/Devifish/Fish-Cloud.svg?logo=github)](https://github.com/Devifish/Fish-Cloud)
[![star](https://gitee.com/Devifish/Fish-Cloud/badge/star.svg?theme=gray)](https://gitee.com/Devifish/Fish-Cloud/stargazers)
[![language](https://img.shields.io/badge/language-Kotlin-yellow.svg)](https://kotlinlang.org/)
[![license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

# Fish Cloud
> 基于 Spring Cloud Greenwich 微服务框架开发<br/>
> 脱离Java语法的束缚, 使用Kotlin语言编写, 享受Kotlin带来的便捷<br/>
> 使用现代化的函数式编码, 如Lambda、 Java Stream Api、 Kotlin 高阶函数

## 依赖环境
依赖 | 版本
---|---
Java Language | 1.8
Kotlin Language | 1.3
Mysql Database | 5.7
Redis Database | 3.2
Spring Boot | 2.1.5.RELEASE
Spring Cloud | Greenwich.SR1
Mybatis Plus | 3.1.1

- 兼容 ```Java 11+``` 部分依赖库存在非法反射警告，但不影响功能使用
- 由于使用了 ```Undertow``` Servlet容器，默认在 ```spring-boot-starter-web``` 内屏蔽了 ```Tomcat``` <br/>
  请在 ```pom.xml``` 内导入 ```Undertow```