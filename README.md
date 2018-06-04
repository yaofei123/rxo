服务规范
=====
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
    
    
### 2.2.3 管理员后台静态化页面模块
    为管理员后台模块提供静态资源,被引用
    rxo-web-ui

### 2.2.4 管理员后台模块
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
    
### 2.2.5 对外服务接口模块
    端口 : 30110（内部孵化3011x）
    rxo-esi
