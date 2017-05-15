#dudu
* `dudu`项目基于Spring boot + Mybatis框架的分布式开发系统架构；采用强大的beetl模板引擎；数据库使用mysql；前端采用bootstrap框架。
* 后台包含：首页、联系人管理、系统管理（组织机构管理、用户管理、角色管理、菜单管理、数据字典管理、配置管理、日志管理）
* github地址：https://github.com/jflyfox/dudu
* 码云地址：http://git.oschina.net/jflyfox/dudu
* 项目交流群：`568909653`

##平台部署和配置说明
1. 通过git下载项目代码，安装jdk、maven、mysql。
2. 在项目目录下运行mvn install，提示BUILD SUCCESS即可。
3. 创建mysql用户和数据库，运行/dudu/sql下对应dudu.sql。
4. 修改配置文件：/dudu/src/main/resources/aoolication.properties
5. 运行：mvn spring-boot:run

##开源捐赠
* 项目的发展，离不开大家得支持~！~
![jflyfox](http://blog.jflyfox.com/static/images/common/pay_weixin.jpg "Open source support(alipay)")
![jflyfox](http://blog.jflyfox.com/static/images/common/pay_alipay.jpg "Open source support(weixin)")

##相关技术

#### 后端技术:
* Spring Framework：http://projects.spring.io/spring-framework/
* SpringMVC：http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc
* Apache Shiro：http://shiro.apache.org/
* Spring session：http://projects.spring.io/spring-session/
* MyBatis：http://www.mybatis.org/mybatis-3/zh/index.html
* Mybatis-Plus：http://mp.baomidou.com
* Druid：https://github.com/alibaba/druid
* Redis：https://redis.io/
* Quartz：http://www.quartz-scheduler.org/
* Beetl：http://ibeetl.com/guide/#beetl
* Jenkins：https://jenkins.io/index.html
* Maven：http://maven.apache.org/

#### 前端技术:
* jQuery：http://jquery.com
* Bootstrap：http://getbootstrap.com