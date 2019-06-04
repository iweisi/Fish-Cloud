# Fish Cloud
[![star](https://img.shields.io/github/stars/Devifish/Fish-Cloud.svg?logo=github)](https://github.com/Devifish/Fish-Cloud)
[![star](https://gitee.com/Devifish/Fish-Cloud/badge/star.svg)](https://gitee.com/Devifish/Fish-Cloud)
[![language](https://img.shields.io/badge/language-Kotlin-yellow.svg)](https://kotlinlang.org/)
[![license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

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
  
## 模块说明
```
└── fish-admin ---------------------------------- 系统后台服务聚合包
     ├── fish-admin-server ---------------------- 系统后台服务
     ├── fish-admin-common ---------------------- 系统后台公共组件
     └── fish-admin-rpc ------------------------- 系统后台远程调用接口
└── fish-auth ----------------------------------- OAuth2 授权服务聚合包
     ├── fish-auth-server ----------------------- OAuth2 授权服务
     ├── fish-auth-common ----------------------- OAuth2 授权服务公共组件
     └── fish-auth-rpc -------------------------- OAuth2 授权服务远程调用接口
├── fish-common --------------------------------- 公共组件工具包
├── fish-config --------------------------------- 服务配置中心
└── fish-file ----------------------------------- 文件服务聚合包 [计划中]
     ├── fish-file-server ----------------------- 文件服务 [计划中]
     ├── fish-file-common ----------------------- 文件服务公共组件 [计划中]
     └── fish-file-rpc -------------------------- 文件服务远程调用接口 [计划中]
├── fish-gateway -------------------------------- Gateway 路由网关
└── fish-library -------------------------------- library 聚合包 [计划中]
     ├── fish-library-office -------------------- Office 操作封装库 [计划中]
     └── fish-library-swagger ------------------- Swagger 文档封装库 [计划中]
└── fish-registry ------------------------------- 服务注册与发现
```