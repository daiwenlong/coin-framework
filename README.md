# coin-framework

## 项目目标

实现一个简易的java web框架。

## 项目模块

*  ioc
*  mvc
*  orm
*  dao
*  aop
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
   #ioc扫描的package 多个用","隔开,应该包括controller,service和Aspect
   coinioc_package com.dwl
   
   #controller所在package 多个用","隔开
   coinact_package com.dwl
   
   #数据库连接基本信息
   jdbc.driver com.mysql.jdbc.Driver
   jdbc.url jdbc:mysql://localhost:3306/reports?useUnicode=true&characterEncoding=UTF-8
   jdbc.username root
   jdbc.password 111111
   
   #静态资源路径 ，不配置时默认webapp下的static文件夹
   static_path /static/
   
   #jsp路径，不配置时默认WEB-INF下的jsp文件夹
   jsp_path /WEB-INF/jsp/
   
```

web.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	version="3.0">
  <display-name>coin-framework</display-name>
  <servlet>
        <servlet-name>coinServlet</servlet-name>
        <servlet-class>com.me.coin.framework.mvc.DispatchServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
        <servlet-name>coinServlet</servlet-name>
		<url-pattern>/*</url-pattern>
  </servlet-mapping>
  <welcome-file-list>
		<welcome-file>/index.html</welcome-file>
  </welcome-file-list>
</web-app>

```

### 对象映射

* @Table 表名
* @Id 主键
* @Column 字段映射

```java
@Table("T_USER")
public class User {
	
	@Id
	@Column("id")
	private String id;
	
	@Column("name")
	private String name;
	
	//getter/setter省略
}
```


### 业务层 

* @Service 标识service层
* @Inject 依赖注入

```java
@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private Dao dao;

	@Override
	public List<User> getList(String name) {
		return dao.query(User.class, Cnd.where().and("name", "=", name));
	}

	@Override
	@Transaction
	public long addUser(User user) {
	    return dao.insert(user);
	}

}
```

### 控制层

* @Act 标识控制层
* @Request 地址映射
* @Inject 依赖注入

```java
@Act
@Request("/user")
public class UserController {
	
	@Inject
	private UserService userService;
	
	
	/**
	 * 获取执行姓名的用户
	 * @param name
	 * @return Result
	 */
	@Request("/list")
	public Result getUsers(@Param("name") String name){
		//获取用户 -select * from T_USER where name = ?
		List<User> list = userService.getList(name);
		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		return new Result(data, View.Json);
		
	}
	
	/**
	 * 添加用户，返回主键
	 * @param user
	 * @return Result
	 */
	@Request("/list")
	public Result addUser(@Entity("user.")User user){
		long pk = userService.addUser(user);
		Map<String, Object> data = new HashMap<>();
		data.put("pk", pk);
		return new Result(data, View.Json);
	}
}

```

#### 参数映射

* @Param 基本参数类型及其包装类
* @Entity 对象参数类型

使用@Entity("user.")时，前端传过来的参数user.name,user.age,user.sex都会封装到user对象中。

#### 视图类型

定义了三种基本视图

* View.Json 返回json
* View.Jsp 返回jsp
* View.Redirect 重定向

### dao操作
dao在项目启动时已交给ioc管理

使用@Inject即可使用
```java
@Inject
private Dao dao;
```

* 查询
```java
//获取所有用户 -select * from T_USER
List<User> list1 = dao.query(User.class, Cnd.where());

//获取用户 分页 -select * from T_USER limit 0,10
List<User> list2 = dao.query(User.class, Cnd.where(),new Pager());

//获取用户 -select * from T_USER where age = 16
List<User> list3 = dao.query(User.class, Cnd.where().and("age", "=", 16));

//获取用户 -select * from T_USER where id = 1
User user = dao.fetch(User.class, 1);
```
* 新增
```java
int sus = dao.insert(user);//返回执行成功的条数

long id = dao.insert(user,Long.class);//返回自增主键

```
* 删除
```java
dao.delete(user);//删除对象

dao.delete(User.class,1);//按主键删除

dao.delete(User.class, Cnd.where().and("name", "=", "dwl"));//按条件删除
```
* 更新
```java
dao.update(user);
```
* 统计
```java
long count = dao.count(User.class, Cnd.where());//统计全表

long count = dao.count(User.class, Cnd.where().and("age", ">", 35));//按条件统计
```

### 依赖注入

使用@Act,@Service,@IocBean标记的类将交给ioc管理，由ioc负责创建和注入。

* @Act  controller类
* @Service  service类
* @IocBean  组件类
* @Inject  需要注入的属性

### 事务管理

事务管理在service层进行，@Transaction与@Service需配合使用才能生效，暂不支持事务传播行为。

* @Transaction 声明式事务管理

```java
@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private Dao dao;

	@Override
	@Transaction
	public long addUser(User user) {
	    return dao.insert(user);
	}

}
```

### 切面编程

使用@Aspect声明切面类，切面也需要交给ioc管理。

支持对同一切入点的多重切面。

* @Aspect(pointCut="")  切面，切入点
* @Before  前置通知
* @After  后置通知
* @Throwing 异常通知

```java
@IocBean
@Aspect(pointCut="com.dwl.controller")
public class AspectT {
	
	@Before
	void start(Method method,Object[] args){
		System.out.println("前置通知");
		System.out.println(method.getName());
	}
	
	@After
	void end(Method method,Object[] args){
		System.out.println("后置通知");
	}
	
	@Throwing
	void error(Method method,Object[] args,Throwable throw){
		System.out.println("异常通知");
	}

}

```



