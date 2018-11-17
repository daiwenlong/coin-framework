# coin-framework

## 项目目标

实现一个简易的java web框架。

## 项目模块

*  ioc
*  mvc
*  orm
*  dao
*  aop 暂未实现
*  其他...

## 使用说明

### Maven
Download项目代码后使用maven install到本地仓库后就可以使用。

```xml
		<dependency>
			<groupId>com.coin</groupId>
			<artifactId>coin-framework</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
```

### 配置文件
在resources下新建一个coin.properties
```
   #ioc扫描的package 多个用","隔开
   coinioc_package com.dwl
   #controller所在package 多个用","隔开
   coinact_package com.dwl
   #数据库连接基本信息
   jdbc.driver com.mysql.jdbc.Driver
   jdbc.url jdbc:mysql://localhost:3306/reports?useUnicode=true&characterEncoding=UTF-8
   jdbc.username root
   jdbc.password 111111
```




