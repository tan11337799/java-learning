# SpringBoot基础

### 概念

**简介**

Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。



**微服务**

微服务是指一种架构风格（服务微化），它将系统围绕业务领域进行构建，服务间使用轻量级接口通信（通常是基于HTTP的RESTful API），各业务领域服务可独立部署，可独立数据存储，可使用不同的技术栈。

微服务提倡将单一应用程序划分成一组小的服务，服务之间互相协调、互相配合，为用户提供最终价值。

SpringBoot中嵌入了Tomcat、Jetty或Undertow，可以独立运行，不需要部署；为了独立性，SpringBoot直接把web服务集成在框架里，项目创建后，直接启动就可以是一个微服务，这种方式省去了部署过程。



**特点**

- 自动配置
  Spring Boot的自动配置是一个运行时（更准确地说，是应用程序启动时）的过程，考虑了众多因素，才决定
  Spring配置应该用哪个，不该用哪个。该过程是SpringBoot自动完成的。
- 起步依赖
  起步依赖本质上是一个Maven项目对象模型（Project Object Model，POM），定义了对其他库的传递依赖
  ，这些东西加在一起即支持某项功能。
  简单的说，起步依赖就是将具备某种功能的坐标打包到一起，并提供一些默认的功能。
- 辅助功能
  提供了一些大型项目中常见的非功能性特性，如嵌入式服务器、安全、指标，健康检测、外部配置等。



**起步依赖**

实现：项目继承spring-boot-start-parent坐标，spring-boot-start-parent坐标下又继承spring-boot-dependencies，该坐标下存放springboot依赖各包的版本信息。

作用：

- 在spring-boot-starter-parent中定义了各种技术的版本信息，组合了一套最优搭配的技术版本。
- 在各种starter中，定义了完成该功能需要的坐标合集，其中大部分版本信息来自于父工程。
- 我们的工程继承parent，引入starter后，通过依赖传递，就可以简单方便获得需要的jar包，并且不会存在
  版本冲突等问题。



### 配置文件

##### 介绍

SpringBoot是基于约定的，我们可以使用全局配置文件对自定义属性进行配置。主要包括application.properties或者application.yml进行配置。

**作用：**修改SpringBoot自动配置的默认值

**注意点:**

* yaml和yml都是指同一种文件类型
* 默认配置文件名称：application，springboot会自动读取该文件名的配置文件。
* 如果同时有多种配置文件，则在同一级目录下优先级为：properties > yml > yaml

**分类：**

(1) properties格式

properties⽂件是⼀种属性⽂件，这种⽂件以key=value格式存储内容。

(2) yml格式

YAML是一种直观的能够被电脑识别的的数据数据序列化格式，并且容易被人类阅读，容易和脚本语言交互的，可以被支持YAML库的不同的编程语言程序导入。YML文件是以数据为核心的，比传统的xml方式更加简洁。

语法格式：`key:(空格)value`  数据值value前边必须有空格



##### **语法**

**具体细节：**

- 大小写敏感；
- 数据值前边必须有空格，作为分隔符；
- 使用缩进表示层级关系；
- 缩进时不允许使用Tab键，只能使用空格；缩进的空格数目不重要，只要相同层级的元素左侧对齐即可；
- \# 表示注释，从这个字符一直到行尾，都会被解析器忽略；
- “ ” 双引号，不会转义字符串里面的特殊字符；‘ ’单引号，会转义特殊字符

**数据类型：**

yaml支持的三种数据结构

- 对象：键值对的集合

  ```yaml
  person:
    name: zhangsan
  ```

  我们常会将字面量的值传入到对象内，比如：

  ```yaml
  name: zhangsan
  person:
    name: ${name}
  ```

- 数组：一组按次序排列的值

  ```yaml
  address:
    - beijing
    - shanghai
  ```

- 字面量：单个的、不可再分的值

  ```yaml
  msg1: 'hello \n world'  # 单引忽略转义字符
  msg2: "hello \n world"  # 双引识别转义字符
  ```



##### 配置读取

（1）在需要注入的属性上添加注解：`@Value()`

将yml文件中的普通属性注入类属性，再获取配置属性。（适合于需要注入值较少的情况，其他情况较为繁琐）

```java
//将yml文件中person对象的name属性进行注入
@Value("${name}")
private String name1;

@Value("${person.name}")
private String name2;
```

（2）通过Environment对象获取配置文件中的属性值。（在springboot启动时会在容器中自动创建Environment对象用于存放各类配置）

再通过env对象的getProperty方法获取配置属性。

```java
//将容器中的Environment对象属性注入到env中
@Autowired
private Environment env;

@GetMapping("/hello")
public String hello(){
    System.out.println(env.getProperty("person.age"));
    return "success";
}
```

（3）通过在类上添加注解：`@ConfigurationProperties()`以及`@Component`

@ConfigurationProperties注解可以获取配置文件中的属性并绑定到Java Bean或属性中。

这里需要添加prefix属性表示自动添加前缀，即对应yml配置文件中的对象、properties文件中的前缀。

```java
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private int age;
    //...
    //getter setter toString
}
```

获取配置：

```java
@Autowired
private Person person;
@GetMapping("/hello")
public String hello(){
    System.out.println(person);
    System.out.println(person.getName());
    System.out.println(person.getAge());
    System.out.println(Arrays.toString(person.getAddress()));
    return "success";
}
```

