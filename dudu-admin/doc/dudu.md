# 使用说明

### action介绍

* /admin/*** 管理后台
* /admin/login 管理后台登陆
* /admin/logout 管理后台登出
* /admin/home 管理后台首页
* /admin/system/*** 管理后台系统管理
* /admin/report/*** 管理后台报表
* /admin/demo/*** 管理后台demo

### 日志系统
* 采用 slf4j + logback 
* 输出 dudu.log 和 dudu-error.log 

### 拦截器说明
* SQLInjectInterceptor：SQL注入拦截器拦截，通过@SQLInject(false)取消验证
* LoginInterceptor：后台登陆拦截
* FormTokenInterceptor：后台重复提交拦截Token方式，通过@FormToken(save=true)和@FormToken(remove=true)配合实现；前台需要传递formToken参数；
* 同上拦截器，使用方法一致；可以防止Csrf攻击；
* SameUrlDataInterceptor：后台重复提交拦截URL Data相同验证方式，后台默认使用；通过@SameUrlData实现；
* CommonAttrInterceptor：公共参数拦截器拦截
* BaseController（非拦截器）：XSS攻击处理，通过继承BaseController实现，initBinder中通过StringEscapeEditor处理

### 使用框架 
spring session + redis 支持session集群化，让session通过redis保存