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

### orm
```java
@Table("T_USER")//表名
public class User {
	
	@Id//主键
	@Column("id")//对应数据库字段
	private String id;
	
	@Column("name")
	private String name;
}
```

### mvc

```java
@Act//标识controller
@IocBean//交给ioc管理
@Request("/user")//地址映射
public class UserController {
	
	@Inject//注入
	private Dao dao;
	
	
	@Request("/list")
	public Result getUsers(){
		//获取所有用户 -select * from T_USER
		List<User> list = dao.query(User.class, Cnd.where());
		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		//返回json
		return new Result(data, View.Json);
	}

}
```

### dao
dao在项目启动时已交给ioc管理

使用@Inject即可使用
```java
    @Inject//注入
	private Dao dao;
```

* 查询
```java
    //获取所有用户 -select * from T_USER
	List<User> list = dao.query(User.class, Cnd.where());
	//获取用户 分页 -select * from T_USER limit 0,10
	List<User> list2 = dao.query(User.class, Cnd.where(),new Pager());
	//获取用户 -select * from T_USER where age = 16
	List<User> list3 = dao.query(User.class, Cnd.where().and("age", "=", 16));
	//获取用户 -select * from T_USER where id = 1
	User user = dao.fetch(User.class, 1);
```
* 新增
```java
    dao.insert(user); 
```
* 删除
```java
    dao.delete(user);
```
* 更新
```java
    dao.update(user);
```

### ioc

使用@IocBean标记的类将交给ioc管理，由ioc负责创建和注入。







