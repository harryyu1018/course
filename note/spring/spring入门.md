 ![spring-logo](img/spring-logo.png)







## 2. Spring IOC 容器



### 2.1 什么是IOC

- IOC，控制反转，控制权转移，应用程序本身不负责依赖对象的创建和维护，而是由外部容器负责创建和未付
- DI，依赖注入，是其一种实现方式
- 目的：创建对象并且组装对象之间的关系



#### 扩展理解

> 2004年，Martin Fowler探讨了同一个问题，既然IOC是控制反转，那么到底是“哪些方面的控制被反转了呢？”
>
> 他给出的答案：“获取依赖对象的过程被反转了”。控制被反转之后，获得依赖对象的过程由自身管理变为了由IOC容器主动注入。于是，他给“控制反转”取了一个更合适的名字叫做“依赖注入（Dependency Injection）”。



> 依赖注入，就是由IOC容器在运行期间，动态地将某种依赖关系注入到对象之中。



**小栗子**

用找房屋中介的例子理解IOC

| 房屋中介   | IOC    |
| ------ | ------ |
| 找中介    | 找IOC容器 |
| 中介介绍房子 | 容器返回对象 |
| 租房，入住  | 使用对象   |



### 2.2 Spring Bean的配置

- xml配置
- Annotation注解



xml配置方式

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="xxx" class="top.yujy.interfaces.XxxImpl"></bean>
  
</beans>
```





### 2.3 Bean容器初始化

- 基础：两个包
  - org.springframework.beans
  - org.springframework.context
  - BeanFactory提供配置结构和基本功能，加载并初始化Bean
  - ApplicationContext保存了Bean对象并在Spring中被广泛使用
- 方式，ApplicationContext
  - 本地文件
  - Classpath
  - Web应用中依赖servlet或Listener



**本地文件**

```java
FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("~/project/app-context.xml"); 
```

**Classpath**

```java
ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
```

**Web应用**

```xml
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<servlet>
	<servlet-name>context</servlet-name>
	<servlet-class>org.springframework.web.context.ContextLoaderListener</servlet-class>
  	<load-on-startup>1</load-on-startup>
</servlet>
```



### 2.4 Spring注入

是指在启动Spring容器加载bean配置的时候，完成对变量的赋值行为

常用的两种注入方式：

- 设置注入
- 构造注入



**设置注入**

```xml
<bean id="injectionService" class="top.yujy.ioc.service.InjectionDAOImpl">
	<property name="injectionDAO" ref="injectionDAO" />
</bean>

<bean id="injectionDAO" class="top.yujy.ioc.dao.InjectionDAOImpl"></bean>
```

**构造注入**

```xml
<bean id="injectionService" class="top.yujy.ioc.service.InjectionDAOImpl">
	<constructor-arg name="injectionDAO" ref="injectionDAO" />
</bean>

<bean id="injectionDAO" class="top.yujy.ioc.dao.InjectionDAOImpl"></bean>
```



## 3. Bean

- Bean配置项
- Bean的作用域
- Bean的生命周期
- Bean的自动装载
- Resources & ResourceLoader



### 3.1 Bean配置项

| 配置项                               | 意义   |
| --------------------------------- | ---- |
| Id                                |      |
| Class                             |      |
| Scope                             |      |
| Constructor arguments             |      |
| Properties                        |      |
| Autowiring mode                   |      |
| lazy-initialization mode          |      |
| Initialization/destruction method |      |



### 3.2 Bean作用域

| 选项             | 意义                                       |
| -------------- | ---------------------------------------- |
| singleton      | **(默认)** 单例，指一个Bean容器中只存在一份              |
| prototype      | 每次请求(每次使用) 创建新的实例，destroy方式不生效           |
| request        | 每次http请求创建一个实例且仅在当前request内有效            |
| session        | 同上，每次http请求创建，当前session内有效               |
| global session | 基于portlet的web中有效(portlet定义了global session)，如果是在web中，同session |



### 3.3 Bean的生命周期



- 定义
- 初始化
- 使用
- 销毁



#### 3.3.1 定义

- 实现org.springframework.beans.factory.InitializingBean接口，实现afterPropertiesSet方法
- 配置init-method



**实现接口**

```java
public class ExampleInitBean implements nitializingBean {
  
  @Override
  public void afterPropertiesSet() throws Exception {
    // TODO
  }
}
```

**配置init-method**

```xml
<bean id="exampleInitBean" class="example.ExampleInitBean" init-method="init"></bean>
```



### 3.3.2 销毁

- org.springframework.beans.factory.DisposableBean接口，实现destroy方法
- 配置destroy-method



> **注意：**
>
> 配置全局默认初始化、销毁方法
>
> ```xml
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:p="http://www.springframework.org/schema/p"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd" 
>        default-init-method="init" 
>        default-destroy-method+"destroy">
>   
> </beans>
> ```







