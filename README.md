服务规范
=====
# 1 微服务基础服务服务规范

## 1.1 服务器
	x.x.x.xxx pro-microserver-xxx.rxo.com
	x.x.x.xxx pro-microserver-xxx.rxo.com
	x.x.x.xxx pro-microserver-xxx.rxo.com
## 1.2 Spring cloud 
    端口:400xx ~ 402xx
### 1.2.1 Eureka
    端口40000
---
	pro-microserver-xxx.rxo.com:40000
	pro-microserver-xxx.rxo.com:40000
	pro-microserver-xxx.rxo.com:40000
	
### 1.2.2 zipkin 链路追踪
    端口:40010
    	
### 1.2.2 zuul 链路追踪
    端口:40020

## 1.3 Apollo 
    端口:403xx
### 1.3.1 apollo-portal
    端口:40300
    超级管理员账号/密码：apollo/admin
---
	pro-microserver-xxx.rxo.com:40300
### 1.3.2 apollo-config
    端口:40310
    
### 1.3.3 apollo-admin
    端口:40320
	
## 1.4 RabbitMQ
    端口:404xx
---    
    管理员 账号/密码：admin/admin
---

---	
	集群 SLB 地址 
	
## 1.5 xxl-job-admin 
    端口:405xx
---
    端口:40500
    管理员 账号/密码：admin / admin@xxljob!
---    
    pro-microserver-xxx.rxo.com:40500
    pro-microserver-xxx.rxo.com:40500
    pro-microserver-xxx.rxo.com:40500

# 2 业务服务规范

## 2.1 项目名称
    rxo : 处方外流 301xx
---
    项目基础端口：30100、30110、30120、...
    00 ： web 管理员后台服务
    10 ： esi 对外服务接口
    ...
    
## 2.2 项目模块
    基础包：com.xxx.rxo
    
### 2.2.1 基础工具服务模块
    为各业务模块提供基础, 公共工具, mybaits、redis、spring等封装。被引用
    rxo-platform
---
    com.xxx.rxo.mybatis 数据库相关
    com.xxx.rxo.redis 缓存redis相关
    com.xxx.rxo.springmvc spring相关
    com.xxx.rxo.util 工具类相关
    
### 2.2.2 领域对象模块
    为各业务模块提供领域对象,被引用
    rxo-domain
---
    com.xxx.rxo.entity 数据库实体对象
    com.xxx.rxo.vo 前端展示对象
    
### 2.2.3 管理员后台模块
    端口 : 30100（内部孵化3010x）
    rxo-web
---
    系统服务:com.xxx.rxo.admin包
    如account、role、resource、dict等 
    
    com.xxx.rxo.admin.controller
    com.xxx.rxo.admin.service
    com.xxx.rxo.admin.service.impl
    com.xxx.rxo.admin.dao
    com.xxx.rxo.admin.mapper
---
    业务服务:com.xxx.rxo.business包
    
    com.xxx.rxo.business.controller
    com.xxx.rxo.business.service
    com.xxx.rxo.business.service.impl
    com.xxx.rxo.business.dao
    com.xxx.rxo.business.mapper
    
### 2.2.4 对外服务接口模块
    端口 : 30110（内部孵化3011x）
    rxo-esi
