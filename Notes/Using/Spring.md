---
typora-copy-images-to: assets
typora-root-url: assets
---

# Spring框架

### 概述

Spring是轻量级开源的JavaEE框架，引入jar包很少。提供了功能强大的IOC和Aop（两大核心部分）及Web MVC等功能。

**目的：**用于解决企业应用开发的复杂性。

**IOC：**控制反转，把创建对象过程交给Spring进行管理。

**AOP：**面向切面，不修改源代码的前提下进行功能增强。

**特点：**

（1）方便解耦，简化开发；（2）AOP编程支持；（3）方便程序的测试；（4）方便和其它框架进行整合；（5）方便进行事务管理操作；（6）降低Java API的开发和使用难度

**基本jar包：**Beans、Core、Context、Expression



**创建Bean对象过程：**

(1) 在src下创建xml配置文件Spring config，并添加bean标签<bean id="user" class="com.company.User"></bean>

(2) 在测试类中添加测试方法，用`@Test`修饰；

(3) 读取配置文件：`ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");`

(4) 创建对象：`User user = context.getBean("user", User.class);`

(5) 调用对象进行代码测试。



### Java结构

Java的结构大致可以分为三层：

1. Controller层（控制层）：接收客户端的请求，然后调用Service层接口控制业务逻辑，获取到数据，传递数据给页面；
2. Service层（业务层、服务层）：接收Controller层的请求，然后调用Dao层，获取到数据，service层也叫服务层，主要负责业务模块的逻辑；
3. DAO 层（数据操作层）：连接数据库，对数据库进行操作，具体到对于某个表的增删改查，也就是说某个DAO一定是和数据库的某一张表一一对应的，其中封装了增删改查基本操作

**调用流程：**

![1654739823993](D:\WorkSpace\git\Mine\Notes\Using\assets\1654739823993.png)

Service层是建立在DAO层之上的，建立了DAO层后才可以建立Service层，而Service层又是在Controller层之下的，因而 Service层应该既调用DAO层的接口，又要提供接口给Controller层的类来进行调用，它刚好处于一个中间层的位置。 每个模型都有一个Service接口，每个接口分别封装各自的业务处理方法。



### XML文件

##### **介绍**

xml（Extensible Markup Language）可拓展标记语言。是一种用于标记电子文件使其具有结构性的标记语言。

**特点：**

* XML简单易于在任何应用程序中读/写数据
* xml可用作数据的说明、储存、传输
* xml文件现在多用于作配置文件
* 作为系统与系统之间的传输数据的格式

**xml和html比较：**

* xml文档的标记可以随意扩展，html的标记是预定义的
* xml区分大小写，html不区分大小写
* html主要是用来显示数据的，xml是用来保存数据的
* html中，空格会自动过滤，而xml不会
* html中可以有多个根节点，在xml里面只有一个



##### **语法**

**结构：**一个XML文件通常包含**文件头**和**文件体**两大部分，具体可以分为XML声明（必须拥有）、DTD文档类型定义、XML正文。

**文件头（处理指令）**

处理指令主要用于来给处理XML文件的应用程序提供信息的，处理指令的格式：<?处理指令名称 处理指令信息?>

e.g. `<?xml version="1.0" encoding="UTF-8" standalone="yes"？>`

- version：用来表示XML的版本号
- encoding：指定XML编码格式
- standalone：用来表示XML文件是否依赖外部的文件

**文件体**

XML元素定义：`<元素名></元素>`

属性定义：`<元素名 属性="属性值" 属性2="属性值2"></元素>`

注释定义`<!-- 这是一条注释-->`



**转义字符**

\&gt;	大于>

\&lt;	小于<

\&amp;	和号&

\&apos：	单引号‘

\&quot：	引号“



**注意点：**

* xml的标记不能以xml，数字或者下划线开头
* 标签中的属性值要加双引号
* XML**标记**与HTML标记相同，“<”表示一个标记的开始，“>” 表示一个标记的结束。XML中只要有起始标记，就必须有结束标记，而且在使用嵌套结构时，标记之间不能交叉。
* 在XML中不含任何内容的标记叫做**空标记**，格式为：<标记名称/>



##### **结构**

**文件头**

XML文件头由XML声明与DTD文件类型声明组成。其中DTD文件类型声明是可以缺少的，而XML声明是必须要有的，以使文件符合XML的标准规格。**XML声明必须出现在文档的第一行。**

e.g.: `<?xml version="1.0" encoding="gb2312"?>`

解释：

* “< ?”代表一条指令的开始， “?>”代表一条指令的结束；

* “xml”代表此文件是XML文件；
* “ version="1.0" ”代表此文件用的是XML1.0标准；
* “ encoding="gb2312" ” 代表此文件所用的字符集，默认值为Unicode，如果该文件中要用到中文，就必须将此值设定为gb2312。



**文件体**

文件体中包含的是XML文件的内容，XML元素是XML文件内容的基本单元。从语法讲，一个元素包含一个**起始标记**、一个**结束标记**以及标记之间的**数据内容**。

所有的数据内容都必须在某个标记的开始和结束标记内，而每个标记又必须包含在另一个标记的开始与结束标记内，形成嵌套式的分布，只有最外层的标记不必被其他的标记所包含。最外层的是**根元素(Root)**，又称**文件(Document)元素**，所有的元素都包含在根元素内。

**语法：**<标记名称 属性名1="属性值1" 属性名2=“属性值2" ……>内容</标记名称>	(XML元素与HTML元素的格式基本相同)



---



# IOC容器

主要内容：IOC底层原理、IOC接口（BeanFactory)、IOC具体操作（Bean管理【基于xml、基于注解】）

### 概念

控制反转(Inversion of Control, IOC)，是面向对象编程中的一种设计原则，用来**减低代码之间的耦合度**。通过控制反转，对象在被创建的时候，由一个调控系统内所有对象的外界实体将其所依赖的对象的引用传递给它。

也就是说：**把对象创建和对象之间的调用过程，交给Spring进行管理**。



### 底层原理

**使用技术：**（1）使用设计模式：工厂模式；（2）xml解析；（3）使用反射。

e.g.

```java
class UserFactory{
    public static UserDao getDao(){
        String classValue = "className";
        Class clazz = Class.forName(classValue);//通过反射创建对象
        return (UserDao)clazz.newInstance();
    }
}
```

**结构图：**

![1655309650669](/1655309650669.png)

**基本要点：**

* IOC思想需要基于IOC容器，IOC底层是一个对象工厂；
* Spring提供了IOC容器实现的两种方式：
  * BeanFactory接口：是IOC容器基本实现方式，是Spring内部的使用接口，不提供给开发人员进行使用（加载配置文件时候**不会创建对象**，在获取对象时才会创建对象。）

  * ApplicationContext接口：BeanFactory接口的子接口，提供更多更强大的功能，提供给开发人员使用（加载配置文件时候就会把在配置文件的对象进行创建）【推荐使用，服务器在启动过程中就加载对象】功能包括：

    * 访问应用程序组件的Bean工厂方法。从org.springframework.beans.factory.ListableBeanFactory继承。

    * 以通用方式加载文件资源的能力。继承自org.springframe .core.io。ResourceLoader接口。

    * 向注册侦听器发布事件的能力。继承自ApplicationEventPublisher接口。

    * 解析消息的能力，支持国际化。继承自MessageSource接口。



### FactoryBean

在Spring中有两种类型的bean，一种为普通bean，另外一种叫做FactoryBean。

普通bean：在bean配置文件中定义的类型返回对应的类型；

工厂bean：在bean配置文件中定义类型和返回类型不一致。



**创建工厂bean的步骤：**

- 创建类，让这个类作为工厂bean，实现接口FactoryBean；
- 实现接口中的方法，在实现方法中定义返回bean的类型。

实现：

```java
class factoryBean implements FactoryBean<BeanImp> {
    @Override
    public BeanImp getObject() throws Exception {
        return new BeanImp();
    }

    @Override
    public Class<?> getObjectType() {
        return BeanImp.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
```



### API

（1）ApplicationContext

作用：ApplicationContext是接口类型，代表应用上下文，可以通过其实例获得 Spring 容器中的 Bean 对象。

![1656174235208](/1656174235208.png)



---

（2）ApplicationContext的实现类

* ClassPathXmlApplicationContext：从类的根路径下加载配置文件（常用）
* FileSystemXmlApplicationContext：从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置
* AnnotationConfigApplicationContext：当使用注解配置容器对象时，需要使用此类来创建 spring 容器。用于读取注解。

（3）getBean()方法

```java
//常用的三种重载
//参数列表为bean的id
public Object getBean(String name) throws BeansException {
        this.assertBeanFactoryActive();
        return this.getBeanFactory().getBean(name);
    }

//参数为Class类型
public <T> T getBean(Class<T> requiredType) throws BeansException {
        this.assertBeanFactoryActive();
        return this.getBeanFactory().getBean(requiredType);
}

//根据bean的id和Class类型确定bean实例
public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
    this.assertBeanFactoryActive();
    return this.getBeanFactory().getBean(name, requiredType);
}
```



当参数的数据类型是字符串时，表示根据bean的id从容器中获得Bean实例，返回是Object，需要强转。

当参数的数据类型是Class类型时，表示根据类型从容器中匹配Bean实例，当容器中相同类型的Bean有多个时，该方法报错。

当使用第三种方式获取bean实例时，不需要进行强转，也不需要担心有多个重复bean（因为id只有一个）。



---





# Bean基本知识



### Bean作用域

在 Spring 中默认情况 bean 是单实例对象。需要设置作用域时：

（1）在 spring 配置文件 bean 标签里面有属性（scope）用于设置单实例还是多实例

（2）scope 属性值（默认）**singleton**，表示是单实例对象；另一种属性值 **prototype**，表示是多实例对象

```xml
<bean id="book" class="com.atguigu.spring5.collectiontype.Book" scope="prototype"><!--设置为多实例-->
        <property name="list" ref="bookList"></property>
</bean>
```

**注意点：**

设置 scope 值是 **singleton** 时，加载 spring 配置文件时就会创建单实例对象 ；

设置 scope 值是 **prototype** 时，不是在加载 spring 配置文件时候创建对象，在调用 getBean 方法时创建多实例对象



### Bean生命周期

生命周期是对象从创建到销毁的过程，bean生命周期也就是bean对象的创建到销毁过程。

![1655310053331](/1655310053331.png)

**在配置文件中的生命周期配置：**

`init-method`：指定类中的初始化方法

`destroy-method`：指定类中的销毁方法



**bean的生命周期过程：**

（1）通过反射调用构造器创建bean对象实例（无参构造）；

（2）通过依赖注入为bean对象装配属性（调用set方法）；

（3）实现了Aware接口的Bean，执行接口方法：如顺序执行BeanNameAware、BeanFactoryAware、ApplicationContextAware的接口方法；

（3）将bean实例传递给bean的前置处理器方法postProcessBeforeInitialization；

（4）顺序执行@PostConstruct注解方法、InitializingBean接口方法、init-method方法进行初始化；

（5）将bean实例传递给bean的后置处理器方法postProcessAfterInitialization

（6）得到bean对象，使用bean

（7）容器关闭后，调用bean的销毁方法，顺序是：@PreDestroy注解方法、DisposableBean接口
方法、destroy-method。



---




# 基于xml的Bean管理

**开发步骤：**

* 导入相关jar包
* 创建需要导入的Bean对象
* 创建context.xml配置文件，进行对象创建和属性注入
* 创建ApplicationContext对象获取容器中的bean



**重点配置：**

```xml
<bean>标签
id属性:在容器中Bean实例的唯一标识，不允许重复
class属性:要实例化的Bean的全限定名
scope属性:Bean的作用范围，常用是Singleton(默认)和prototype
<property>标签：属性注入
name属性：属性名称
value属性：注入的普通属性值
ref属性：注入的对象引用值
<list>标签
<map>标签
<properties>标签
<constructor-arg>标签
<import>标签:导入其他的Spring的分文件
```



### 创建对象

在spring配置文件中，使用bean标签，标签里添加对应属性，即可完成对象的创建。

使用配置文件创建Bean对象有三种方式：

**（1）使用无参构造器实例化**

**语法：**``<bean id="user" class="com.company.User"></bean>``

**注意事项：**

* bean标签有很多属性，常用属性如下：
  * id属性【常用】：给相应对象添加别名，id是bean的唯一标识，IoC容器中bean的id不能重复，否则报错。
  * class属性【常用】：对象所属类的全路径，为bean的全限定类名，指向classpath下类定义所在位置。
  * name属性：name属性基本等同于id属性，不常用，name属性不能重复，且id和name属性也不能重复，和id的区别主要在于name属性可以添加符号。
* 创建对象时，默认调用类中的无参构造器方法，如果需要使用有参构造器，需要在bean标签中添加properties；



**（2）使用静态工厂实例化**

使用工厂的静态方法返回Bean实例。

**实现：**

以下程序实现了通过createUserDao这一工厂方法创建UserDao实例，并注入UserDaoImpl属性

```java
public class StaticFactoryBean{
    public static UserDao createUserDao(){
        return new UserDaoImpl();
    }
}
//配置文件中
<bean id="userDao" class="com.twhupup.factory.StaticFactoryBean" factory-method="createUserDao">
```



**（3）使用实例工厂实例化**

使用工厂的非静态方法返回Bean实例（需要先创建工厂类的bean实例）

**实现：**

以下程序实现了通过createUserDao这一工厂方法创建UserDao实例，并注入UserDaoImpl属性，但该方法是非静态的，因此需要在配置文件中先创建工厂的bean对象，再进行工厂bean和工厂方法的配置。

```java
public class DynamicFactoryBean{
    public UserDao createUserDao(){
        return new UserDaoImpl();
    }
}

//配置文件中
<bean id="factoryBean" class="com.twhupup.factory.DynamicFactoryBean">
<bean id="userDao" factory-bean="factoryBean" factory-method="createUserDao"/>
```



### 注入属性的方式

使用xml配置文件注入属性一般有两种方式：（1）通过构造方法注入；（2）通过setter方法注入。

**知识点：**

* 如果不写bean标签的方法体，则会默认调用无参构造器；
* 如果类有有参构造器，必须添加`<constructor-arg>`标签并添加所有属性的值。

**步骤：**

(1) 程序中创建需要实例化的类，定义相关属性；

(2) 在spring配置文件配置对象创建，配置属性注入；

(3) 在测试程序中加载xml文件，获取对象。



**方式一：用set()方法注入属性**

当类中存在set()方法时：

```xml
<bean id="book" class="com.company.Book">
    <!--在xml文件中进行属性注入-->
    <property name="name" value="红楼梦"></property>
</bean>
```



**方式二：通过有参构造器注入属性**

当类中存在有参构造器时：

```xml
<bean id="order" class="com.company.Order">
	<!--name属性也可以替换为index表示索引（如index=0表示有参构造器参数列表中的第一个属性），但不能和已经定义的属性重复-->
    <constructor-arg name="address" value="苏州"></constructor-arg>
    <constructor-arg name="name" value="快递"></constructor-arg>
</bean>
```



**方式三：p名称空间注入（了解）**

使用p名称空间注入，可以简化xml配置方式。p命名空间注入本质也是set方法注入，但比起上述的set方法注入更加方便。（需要定义setter方法）



**步骤：**

(1) 添加p名称空间在配置文件头部

`xmlns:p="http://www.springframework.org/schema/p"`

(2) 在bean标签进行属性注入（set方式注入的简化操作）

`<bean id="book" class="com.atguigu.spring5.Book" p:bname="very" p:bauthor="good"></bean>`



### 根据类型注入属性

**注入其他类型属性**

(1) 向属性中设置空值：

```xml
<bean id="book" class="com.company.Book">
    <!--在xml文件中进行属性注入-->
    <property name="name" value="红楼梦"></property>
    <property name="price">
        <null/>
    </property>
</bean>
```

(2) 向属性中设置特殊值：

```xml
<bean id="book" class="com.company.Book">
    <!--在xml文件中进行属性注入-->
    <property name="name" value="红楼梦"></property>
	<property name="name">
    <!--将带特殊符号的内容写入到CDATA-->
    	<value><![CDATA[<<红楼梦>>]]></value>
	</property>
    <property name="price" value="10"></property>
</bean>
```



**注入集合类型属性**

（1）一般注入

注入数组类型属性

 ```xml
<property name="courses">
    <array>
        <value>java课程</value>
        <value>sql课程</value>
        <value>spring课程</value>
    </array>
</property>
 ```

注入List集合类型属性

```xml
<property name="list">
    <list>
        <value>twh</value>
        <value>sjy</value>
        <value>lm</value>
    </list>
</property>
```

注入Map集合类型属性

```xml
<property name="map">
    <map>
        <entry key="JAVA" value="java"/>
        <entry key="PHP" value="php"/>
    </map>
</property>
```

注入Set集合类型属性

```xml
<property name="set">
    <set>
        <value>java</value>
        <value>php</value>
        <value>mysql</value>
    </set>
</property>
```

(2) 在集合中设置对象类型值

```xml
<!--创建多个Course对象-->
<bean id="course1" class="com.twhupup.collectiontype.Course">
    <property name="name" value="Spring5框架"/>
</bean>
<bean id="course2" class="com.twhupup.collectiontype.Course">
    <property name="name" value="Mybatis框架"/>
<bean id="Stu" class="com.twhupup.collectiontype.Stu">
    <!--将创建的对象注入到集合类型中-->    
	<property name="courseList">
        <list>
            <ref bean="course1"/>
            <ref bean="course2"/>
        </list>
	</property>
</bean>
```

(3) 使用util标签提取list集合属性注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       <!--在spring配置文件中引入名称空间util-->
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
    <!--1.提取list集合类型属性-->
    <util:list id="bookList">
        <value>java指南</value>
        <value>php指南</value>
        <value>python指南</value>
    </util:list>
    <!--2.list集合类型属性注入-->
    <bean id="book" class="com.twhupup.collectiontype.Book">
        <property name="list" ref="bookList"/>
    </bean>
</beans>
```



**外部bean注入**

使用场景：调用**其他包**中的类

**步骤：**

(1) 创建service类和dao类，在service中调用dao的方法。

```java
public class UserService {//service类
    //创建UserDao类型属性，生成set方法
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        userDao.update();//调用dao方法
    }
}
```
(2) 在spring配置文件中进行配置

```xml
<!--1 service和dao对象创建-->
<bean id="userService" class="com.atguigu.spring5.service.UserService">
    <!--注入userDao对象
        name属性：类里面属性名称
        ref属性：创建userDao对象bean标签id值
    -->
    <property name="userDao" ref="userDaoImpl"></property>
</bean>
<bean id="userDaoImpl" class="com.atguigu.spring5.dao.UserDaoImpl"></bean>
```



**内部bean注入**

使用场景：一个类中包含另一个类的对象

(1) 在同一个包内创建Dept类和Emp类，并生成set方法。

```java
//部门类
public class Dept {
    private String dname;
    public void setDname(String dname) {
        this.dname = dname;
    }
}

//员工类
public class Emp {
    private String ename;
    private String gender;
    //员工属于某一个部门，使用对象形式表示
    private Dept dept;
    
    public void setDept(Dept dept) {
        this.dept = dept;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
```

(2) 在spring配置文件中配置

```xml
    <bean id="emp" class="com.company.Emp">
        <property name="name" value="twh"/>
        <property name="age" value="18"/>
        <property name="dep">
            <bean class="com.company.Dep">
                <property name="name" value="microsoft"/><!--内部bean注入-->
            </bean>
        </property>
    </bean>
```

(2.1) 也可以通过级联赋值注入属性（类似于外部注入），在spring配置文件中配置

```xml
    <bean id="emp" class="com.twhupup.Emp">
        <property name="name" value="twh"/>
        <property name="age" value="18"/>
    	<property name="dep" ref="dep"></property>
    </bean>
    <bean id="dep" class="com.twhupup.Dep">
        <property name="name" value="microsoft"></property>
    </bean>
```





---



# 基于注解的Bean管理

**知识点**

（1）注解是代码的特殊标记，格式为`@注解名称(属性名1=属性值1，属性名2=属性值2...)`；

（2）注解可以作用在**类、方法、属性**中；

（3）注解的目的：用更简洁的方式配置管理xml文件；



### 组件扫描

组件扫描用于从指定的classpath下自动扫描，侦测和实例化具有特定注解的bean。

对于扫描到的组件，Spring有**默认**的命名策略：使用非限定类名，第一个字母小写。
也可以在注解中通过value属性值标识组建的名称。不使用value属性标识Bean的名称，默认以类名小写命名。

当在组件类上使用了特定的注解之后, 还需要在 Spring 的配置文件中配置以下项：
base-package 属性指定一个需要扫描的基类包，Spring 容器将会扫描这个基类包里及其子包中的所有类，当需要扫描多个包时, 可以使用逗号分隔。

**用法1（include-filter）**

```xml
<context:component-scan base-package="com.twhupup" use-default-filters="false">
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>
</context:component-scan>
```

- `use-default-filters`属性用于选择是否使用默认filter，如果值为false表示不扫描所有内容，而使用自己配置的filter（不设置`use-default-filters`属性，则其设为默认值true，表示需要扫描所有内容）
- `<context:include-filter>`标签写在`<context:component-scan>`的结构体内 ，设置filter中对指定的内容进行扫描
- `type="annotation" expression="org.springframework.stereotype.Service"/>` 表示在base-package的范围内只扫描Service的注解类

**用法2（exclude-filter）**

```xml
<context:component-scan base-package="com.twhupup">
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

- `<context:exclude-filter>`标签写在`<context:component-scan>`的结构体内 ，设置filter中哪些内容不进行扫描



##### @ComponentScan

我们使用`@ComponentScan`用于指定创建容器时要扫描的包。该注解在指定扫描的位置时，可以指定包名，也可以指定扫描的类。同时支持定义扫描规则，例如包含哪些或者排除哪些。同时，它还支持自定义Bean的命名规则。

在spring4.3版本之后还加入了一个@ComponentScans的注解，该注解就是支持配置多个@ComponentScan。

**属性：**

* value: 用于指定要扫描的包。当指定了包的名称之后，spring会扫描指定的包及其子包下的所有类。
* basePackages: 它和value作用是一样的。
* basePackageClasses: 指定具体要扫描的类的字节码。    
* nameGenrator: 指定扫描bean对象存入容器时的命名规则。
* scopeResolver: 用于处理并转换检测到的Bean的作用范围。
* soperdProxy: 用于指定bean生成时的代理方式。默认是Default，则不使用代理。
* resourcePattern: 用于指定符合组件检测条件的类文件，默认是包扫描下的  **/*.class
* useDefaultFilters: 是否对带有@Component @Repository @Service @Controller注解的类开启检测,默认是开启的。
* includeFilters: 自定义组件扫描的过滤规则，用以扫描组件。
  * FilterType有5种类型：
    ANNOTATION, 注解类型 默认
    ASSIGNABLE_TYPE,指定固定类
    ASPECTJ， ASPECTJ类型
    REGEX,正则表达式
    CUSTOM,自定义类型
* excludeFilters: 自定义组件扫描的排除规则。
* lazyInit: 组件扫描时是否采用懒加载 ，默认不开启。

**使用场景：**
 在注解驱动开发时，我们自己编写的类都使用注解的方式进行配置，要想让spring添加到ioc容器中，就需要使用此注解来实现组件的扫描。



### 注入时机和条件

有三个注解用于设定注入的时机和注入的条件

##### @DependsOn

**作用：**用于指定某个类的创建依赖的bean对象先创建。spring中没有特定bean的加载顺序，使用此注解则可指定bean的加载顺序。(在基于注解配置中，是按照类中方法的书写顺序决定的)

**使用场景：**
在观察者模式中，分为事件，事件源和监听器。一般情况下，我们的监听器负责监听事件源，当事件源触发了事件之后，监听器就要捕获，并且做出相应的处理。以此为前提，我们肯定希望监听器的创建时间在事件源之前，此时就可以使用此注解。

**属性：**

* value： 用于指定bean的唯一标识。被指定的bean会在当前bean创建之前加载。



##### @Lazy

**作用：**用于指定单例bean对象的创建时机。在没有使用此注解时，单例bean的生命周期与容器相同。但是当使用了此注解之后，单例对象的创建时机变成了第一次使用时创建。

**使用场景：**
在实际开发中，当我们的Bean是单例对象时，并不是每个都需要一开始都加载到ioc容器之中，有些对象可以在真正使用的时候再加载，当有此需求时，即可使用此注解。值得注意的是，此注解只对单例bean对象起作用，当指定了@Scope注解的prototype取值后，此注解不起作用。

**属性：**

* value: 指定是否采用延迟加载。默认值为true，表示开启。



##### @Conditional

**作用：**它的作用是根据条件选择注入的bean对象。

**使用场景：**

当我们在开发时，可能会使用多平台来测试，例如我们的测试数据库分别部署到了linux和windows两个操作系统上面，现在根据我们的工程运行环境选择连接的数据库。此时就可以使用此注解。同时基于此注解引出的@Profile注解，就是根据不同的环境，加载不同的配置信息，详情请参考第五章第9小节@Profile的使用。

**属性：**

* value: 用于提供一个Condition接口的实现类，实现类中需要编写具体代码实现注入的条件。



### 创建Bean对象

**方式：**

（1）@Component	普通注解

（2）@Service	一般用于service层的对象创建，也允许用在其他位置

（3）@Controller		一般用于web层，也允许用在其他位置

（4）@Repository	一般用于dao层，也允许用在其他位置

上述的四个注解功能都一样，都可以用于创建bean实例，分为若干方式主要用于方便开发

**属性：**

*  value：用于指定存入容器时bean的id。当不指定时，默认值为当前类的名称。

**实现：**

* 首先引入spring-aop的jar包（maven实现）

* 添加context名称空间

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
```

* 开启组件扫描，提示spring使用注解方式（扫描多个包时，base-package的值可以填上层目录，或使用逗号隔开）

```xml
<context:component-scan base-package="com.twhupup"/>
```

* 创建相关类，在类上添加注解（注意，注入的bean名称为首字母小写后的类名）

```java
//在注解中,value属性值可以省略，默认为类名（首字母修改为小写）
@Service(value="userService")//相当于在bean文件中配置：<bean id="userService" class="com.twhupup.service.UserService"/>
public class UserService {
    public void add(){
        System.out.println("Service add");
    }
}
```



### 注入属性

**方式：**

（1）@AutoWired	根据属性类型进行自动装配

（2）@Qualifier		根据属性名称进行注入

（3）@Resource		可以根据类型和名称注入

（4）@Value		注入普通类型属性（int/float/String...）



##### @AutoWired

@AutoWired是一种注解，可以对**成员变量、方法和构造函数**进行标注。

**属性：**

* required：是否必须注入成功。默认值是true，表示必须注入成功。当取值为true的时候，注入不成功会报错。

**注意点：**

* 使用注解方式注入属性不需要类具有set方法；
* @Autowired是根据类型进行自动装配的，如果需要按名称进行装配，则需要配合@Qualifier使用；

* @Autowired标注可以放在任意方法上表示，自动执行当前方法，如果方法有参数，会在IOC容器中自动寻找同类型参数为其传值。

* 一般不建议直接在成员变量上使用@Autowired注解，因为在java底层，是优先对构造方法进行初始化，再进行注解的解析的。因此我们会选择将@Autowired放在构造方法上，在构造方法中实现变量的注入，比如：

  ```java
  class UserAccountServiceImpl{
      //如果将注解写在该位置，则无法进行注入，因为jvm优先执行构造器，在构造方法中操作了被@Autowired修饰的user对象,实际上user的bean对象未注入属性，因此报空指针异常
      private User user;
      private String school;
  
      @Autowired
      public UserAccountServiceImpl(User user){
          this.user = user;
          this.school = user.getSchool();
      }
  }
  ```

**实现**

把service和dao对象进行创建，在service和dao类添加创建对象注释，在service中注入dao对象。此时Spring的组件扫描就会找到这两个类，将其初始化为bean对象注入到IOC容器中。

```java
@Repository
public class UserDaoImp1 implements UserDao{
    @Override
    public void add() {
        System.out.println("dao add...");
    }
}
//另一个包
@Service(value="userService")//相当于在bean文件中配置：<bean id="userService" class="com.twhupup.service.UserService"/>
public class UserService {
    @Autowired
    private UserDao userDao;//在这里使用AutoWired注释表示注入UserDao类型的对象（UserDao作为一个接口，根据多态原理会寻找其实现类）
    public void add(){
        System.out.println("Service add");
        userDao.add();
    }
}
```

这里使用spring进行注入而不通过new创建对象，是因为：**直接创建对象时会增加耦合性，将对象的创建交给ioc容器可以降低耦合性，同时对于对象的单例和多例可以有效的控制而不需要自己采取维护措施**



##### @Qualifier 

根据名称注入（这里的名称指注释的value值，默认为类名+首字母小写）。

当使用自动按类型注入时，遇到有多个类型匹配的时候，就可以使用此注解来明确注入哪个bean对象。注意它通常情况下都必须配置@Autowired注解一起使用

```java
@Service
public class UserService {
    @Autowired
    @Qualifier(value = "userDaoImp1")//根据名称注入；和AutoWired注释联合使用，在类型注入的同时确定注入类的名称
    private UserDao userDao;
    public void add(){
        System.out.println("Service add");
        userDao.add();
    }
}
```



##### @Resource 

此注解来源于JSR规范（Java Specification Requests），其作用是找到依赖的组件注入到应用来，它利用了JNDI（Java Naming and Directory Interface Java命名目录接口 J2EE规范之一）技术查找所需的资源。

默认情况下，即所有属性都不指定，它默认按照byType的方式装配bean对象。如果指定了name，没有指定type，则采用byName。如果没有指定name，而是指定了type，则按照byType装配bean对象。当byName和byType都指定了，两个都会校验，有任何一个不符合条件就会报错。

**注意点：**Resource是javax扩展包中的注解，不是spring官方的注解。

**属性：**

* name：资源的JNDI名称。在spring的注入时，指定bean的唯一标识。
* type：指定bean的类型。



##### @Value	

用于注入基本类型和String类型的数据。它支持spring的EL表达式，可以通过${} 的方式获取配置文件中的数据。配置文件支持properties,xml和yml文件。

**属性：**

* value: 指定注入的数据或者spring的el表达式。

**使用场景：**
在实际开发中，像连接数据库的配置，发送邮件的配置等等，都可以使用配置文件配置起来。此时读取配置文件就可以借助spring的el表达式读取。

```java
@PropertySource("classpath:jdbc.properties")
public class JdbcConfig {
    @Value("${driver}")
    private String driver;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    //其它注入
}
```

**注意点：**@Value注释的注入属性会滞后于属性的自定义赋值



##### @Primary

**作用：**在需要注入的实现类上添加@Primary注解，可以通知IOC容器优先使用该标注的bean对象进行注入。

**使用场景：**当一个类有多个实现类时，ioc容器无法确定使用哪一个实现类进行注入。此时需要指定需要注入的实现类，就可以使用@Primary（使用@Qualifier也可以指定需要注入的bean id，但是这种做法每次都会使用指定的bean进行注入，而使用@Primary可以进行选择使用）

e.g.

```java
@Primary
@Component
public class OperaSinger implements Singer{
    @Override
    public String sing(String lyrics) {
        return "I am singing in Bocelli voice: "+lyrics;
    }
}
```



### 生命周期

##### @Scope

**作用：**用于指定bean对象的作用范围。
**属性：**

* value: 指定作用范围的取值。在注解中默认值是""。但是在spring初始化容器时，会借助ConfigurableBeanFactory接口中的类成员：String SCOPE_SINGLETON = "singleton";
* scopeName:它和value的作用是一样的。
* proxyMode:指定bean对象的代理方式的。指定的是ScopedProxyMode枚举的值
  ​            DEFAULT：默认值。（就是NO）
  ​            NO：不使用代理。
  ​            INTERFACES：使用JDK官方的基于接口的代理。
  ​            TARGET_CLASS：使用CGLIB基于目标类的子类创建代理对象。

**使用场景：**

在实际开发中，我们的bean对象默认都是单例的。通常情况下，被spring管理的bean都使用单例模式来创建。但是也有例外，例如Struts2框架中的Action，由于有模型驱动和OGNL表达式的原因，就必须配置成多例的。

##### @PostConstruct

作用：用于指定bean对象的初始化方法。

##### @PreDestroy

作用：用于指定bean对象的销毁方法。



### 注解驱动

##### @Configuration

**作用：**用于指定当前类是一个Spring配置类，当创建容器时会从该类加载注解（使用配置类代替配置文件，SpringBoot的使用主要就是基于纯注解开发）

**注意点：**

- 不加`@Configuration`，Spring依然可以扫描类中的注解也可以完成IOC和DI。但是无法实现对象的单例模式，也就是说每调用一次方法就会创建一个新的对象。

  添加`@Configuration`后，配置类会被代理，然后代理对象被Spring进行扫描。在代理对象的方法中会优先检查容器中是否已经存在某个类的对象，如果已经存在则从容器中取出该对象进行使用。如果容器不存在才会调用真正的配置类中的方法来进行对象的创建。

- 注解类的使用方法：在测试时使用`AnnotationConfigApplicationContext(A.class)方法表示返回注解类A的ApplicationContext对象`



##### @ComponentScan

**作用：**用于指定组件扫描区域

@ComponentScan注解扫描或解析的bean只能是Spring内部所定义的，比如@Component、@Service、@Controller或@Repository。如果要扫描一些自定义的注解，就可以自定义过滤规则来完成这个操作。



##### **@Bean**

**作用：**使用在方法上，标注该方法的返回值存储到Spring容器中，bean的id为方法名。

**和其它注解的区别：**通常情况下，在基于注解的配置中，我们用于把一个类存入spring的ioc容器中，首先考虑的是使用@Component以及他的衍生注解。但是如果遇到要存入容器的Bean对象不是我们写的类，此时无法在类上添加@Component注解，这时就需要此注解了。

**属性：**

* name：标识存入Spring容器中的bean对象，也就是bean的id，不指定时默认为方法名；
* initMethod: 用于指定初始化方法。
* destroyMethod: 用于指定销毁方法。



##### @PropertySource

**作用：**用于加载.properties文件中的配置

@PropertySource是spring3.1开始引入的基于java config的注解。等同于在xml中配置properties文件。（曾经的配置：`<context:property-placeholder location="classpath:jdbc.properties" />`）

通过@PropertySource注解将properties配置文件中的值存储到Spring的 Environment中，Environment接口提供方法去读取配置文件中的值，参数是properties文件中定义的key值。	

**说明：**

@PropertySource注解用于指定读取资源文件的位置。注意，它不仅支持properties，也支持xml文件，并且通过YAML解析器，配合自定义PropertySourceFactory实现解析yml配置文件（详情请参考第五章第8小节自定义PropertySourceFactory实现YAML文件解析）。

**属性：**

* value：（必要）指定资源的位置。可以是类路径，也可以是文件路径。

* name：指定资源的名称。如果没有指定，将根据基础资源描述生成。


##### @Import

**作用：**该注解是写在类上的，通常都是和注解驱动的配置类一起使用的。其作用是引入其他的配置类。使用了此注解之后，可以使我们的注解驱动开发和早期xml配置一样，分别配置不同的内容，使配置更加清晰。同时指定了此注解之后，被引入的类上可以不再使用@Configuration, @Component等注解。

**使用场景：**当我们在使用注解驱动开发时，由于配置项过多，如果都写在一个类里面，配置结构和内容将杂乱不堪，此时使用此注解可以把配置项进行分门别类进行配置。

**属性：**

* value：用于指定其他配置类的字节码。它支持指定多个配置类。



@Import注解可以导入如下四种类型：

* 导入普通类      

- 导入带有@Configuration的配置类       

- 通过ImportSelector 方式导入的类       

- 通过ImportBeanDefinitionRegistrar方式导入的类 


---

# AOP面向切面

### 概念和术语

AOP意思是面向切面编程。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

应用举例：不修改源代码的情况下，在主干程序中添加新的功能模块。



**术语：**

**连接点**：指类中有哪些方法可以被增强。

**切入点**：实际被真正增强的方法。

**通知（增强）**：实际增强的逻辑部分被称为通知。

- 通知有多种类型：前置通知、后置通知、环绕通知、异常通知、最终通知

**切面**：是指把通知应用到切入点的过程。



### 动态代理

AOP底层使用到了动态代理，动态代理是指在程序运行期，创建目标对象的代理对象，并对目标对象中的方法进行功能性增强的一种技术。在生成代理对象的过程中，目标对象不变，代理对象中的方法是目标对象方法的增强方法。可以理解为运行期间，对象中方法的动态拦截，在拦截方法的前后执行功能操作。

作用：有了动态代理的技术，那么就可以在不修改方法源码的情况下，增强被代理对象的方法的功能（在方法执行前后做任何你想做的事情）

动态代理主要存在两种情况：

**（1）基于接口的动态代理**

使用JDK动态代理，用Proxy类中的方法创建代理对象

**步骤：**

* 创建一个类实现 InvocationHandler 接口
  * 通过有参构造器，传入需要代理的类对象

  * 重写 invoke 方法，在invoke方法中完成需要代理的功能。invoke方法：`public Object invoke(Object proxy, Method method, Object[] args)`有三个参数：

    * proxy : 代理类代理的真实代理对象
    * method : 我们所要调用某个对象真实的方法的Method对象(可以使用`method.invoke(object,args)`的方式调用需要代理类对象的方法)
    * args : 指代理对象方法传递的参数

* 在主方法中创建 JDK 动态代理类对象（使用反射包中的 java.lang.reflect.Proxy 类），通过调用`Proxy.newProxyInstance()`静态方法进行创建。`newPorxyInstance()`方法：`public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)`有三个参数：
  * loader : 需要传入主方法所在类的类加载器（通过class对象获取：`JDKProxy.class.getClassLoader()`）
  * interfaces : 需要传入增强方法所在类实现的接口（可以为多个）
  * h : 传入实现接口InvocationHandler的类对象
* 通过向下转型，调用实现类的getProxy方法。

**实现：**

```java
public class JDKProxy {
    public static void main(String[] args) {
        Class[] interfaces = {UserDao.class};
        UserDaoImpl userDao = new UserDaoImpl();
        UserDao dao = (UserDao)Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
        int result = dao.add(1,2);
        System.out.println("结果为"+result);
    }
}

class UserDaoProxy implements InvocationHandler{
    //把需要代理的类对象传入（一般使用有参构造传入）
    private Object obj;
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法前
        System.out.println("在方法之前执行..."+method.getName()+"传入参数..。"+ Arrays.toString(args));
        //被增强的方法执行(使用反射机制）
        Object res = method.invoke(obj, args);
        //方法后
        System.out.println("方法执行后..."+obj);
        return null;
    }
}
```



**（2）基于类的动态代理**

JDK的动态代理机制只能代理实现了接口的类。而不能实现接口的类就不能使用JDK的动态代理，

**CGLIB是针对类来实现代理的，它的原理是对指定目标类生成一个子类，并覆盖其中的方法实现增强。**但因为采用的是继承，所以不能对final修饰的类进行代理。

实现原理：继承

实现方式：代理类继承的目标类，重写目标类中的方法。CGLIB像是一个拦截器，在调用我们的代理类方法时，代理类(子类)会去找到目标类(父类),此时它会被一个方法拦截器所拦截，在拦截器中才会去实现方法的调用。并且还会对方法进行行为增强。

例子：

```java

package org.example.proxy;
 
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
 
import java.lang.reflect.Method;
 
public class test3 {
    public static void main(String[] args) {
        //得到目标对象
        You you=new You();
        //得到代理类
        Cglib cglib=new Cglib(you);
        //得到代理对象
        Marry marry= (Marry) cglib.getProxy();
        //通过代理对象调用目标对象的方法
        marry.toMarry();
    }
}

class Cglib implements MethodInterceptor {
    //准备一个目标对象
    private Object target;
    //通过构造器传入目标对象
    public Cglib(Object target) {
        this.target = target;
    }
    /*
    * 用来获取代理对象(创建一个代理对象)
    * */
    public Object getProxy(){
        //可以通过Enhancer对象中的create()方法可以去生成一个类，用于生成代理对象
        Enhancer enhancer=new Enhancer();
        //设置父类(将目标类作为代理类的父类)
        enhancer.setSuperclass(target.getClass());
        //设置拦截器(回调对象为本身对象)
        enhancer.setCallback(this);
        //生成一个代理类对象并返回给调用着
        return enhancer.create();
    }
    /*
    * 拦截器
    *       1.目标对象的方法调用
    *       2.行为增强
    *      参数 o: cglib 动态生成的代理类的实例
    *          method:实体类所调用的都被代理的方法的引用
    *          objects 参数列表
    *          methodProxy:生成的代理类对方法的代理引用
    * */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //增强行为
        System.out.println("方法执行前的增强行为");
        //调用目标类中的方法
        Object Objectss=  methodProxy.invoke(target,objects);
        //增强行为
        System.out.println("方法执行后的增强行为");
        return null;
    }
}
```



**（3）JDK代理与CGLIB代理的区别**

- JDK动态代理实现接口，CGLIB动态继承思想
- JDK动态代理(目标对象存在接口时)执行效率高于CGLIB
- 如果对象有接口实现，选择JDK代理，如果没有接口实现选择CGILB代理



### 实现

Spring框架 一般基于AspectJ实现AOP操作。

AspectJ：AspectJ不是Spring的组成部分，是一个独立的AOP框架，一般把AspectJ和Spring框架一起使用完成AOP操作。

基于AspectJ注解方式是常用的AOP实现方式。

**切入点表达式**

作用：表示对哪个类中的哪个方法进行增强。

**语法：**`execution(权限修饰符 [返回类型] 类的全路径.方法名称 (参数列表))`

**举例：**

（1）对com.twhupup.dao.BookDao中的add方法进行增强

`execution(* com.twhupup.dao.BookDao.add())`

（2）对com.twhupup.dao.BookDao中的所有方法进行增强

`execution(* com.twhupup.dao.BookDao.*())`

（3）对com.twhupup.dao包下的所有类中的所有方法进行增强

`execution(* com.twhupup.dao.*.*())`



**步骤：**

* 创建类，在类中定义需要增强的方法

* 创建增强类（编写增强逻辑）
  
  * 在增强类中创建方法，让不同方法代表不同的通知类型
* 进行通知的配置
  * 在spring配置文件中开启注解扫描
  * 使用注解创建被增强类和增强类对象
  * 在增强类上添加注解@Aspect
  * 在spring配置文件中开启生成代理对象
  
* 配置不同类型的通知

  * 在增强类中，在作为通知方法上添加通知类型注释，使用切入点表达式配置。

    前置通知：`Before(value="切入点表达式")`

    后置通知：`AfterReturning(value="切入点表达式")`		方法返回时执行（存在异常时不执行）

    环绕通知：`Around(value="切入点表达式")`

    异常通知：`AfterThrowing(value="切入点表达式")`	需要增强的方法存在异常才会执行

    最终通知：`After(value="切入点表达式")`	方法结束时执行（无论存不存在异常都执行）

    执行顺序：

    ```
    around_before...
    before...
    add...
    afterReturning.../afterThrowing...
    after...
    around_after...
    ```


**举例：**

* 创建User类，在类中定义需要增强的方法add()

* 创建增强类UserProxy

  * 在增强类中创建方法before()
* 进行通知的配置
  * 在spring配置文件中开启注解扫描：`<context:component-scan base-package="com.twhupup.aop_xml"/>`
  * 使用注解创建User和UserProxy对象，在两个类上添加：@Component
  * 在增强类UserProxy上添加注解@Aspect
  * 在spring配置文件中开启生成代理对象（找到带有@Aspect注释的类，为类生成代理对象）：`<aop:aspectj-autoproxy/>`

* 在增强类中配置不同类型的通知

  * 在增强类中，在作为通知方法上添加通知类型注释，使用切入点表达式配置。

    * 前置通知：`@Before(value = "execution(* com.twhupup.aop_xml.User.add(..))")`

    * 后置通知：`@AfterReturning(value = "execution(* com.twhupup.aop_xml.User.add(..))")`

    * 环绕通知：

      ```java
      @Around(value = "execution(* com.twhupup.aop_xml.User.add(..))")
      public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
          System.out.println("环绕前...");
          proceedingJoinPoint.proceed();
          System.out.println("环绕后...");
      }
      ```

    * 异常通知：`@AfterThrowing(value = "execution(* com.twhupup.aop_xml.User.add(..))")`

    * 最终通知：`@After(value = "execution(* com.twhupup.aop_xml.User.add(..))")`



**公共切入点抽取**

在增强类中定义方法，进行公共切入点抽取。其他通知的切入点表达式可以用方法来表示。

**举例：**

```java
@Pointcut(value = "execution(* com.twhupup.aop_xml.User.add(..))")
public void pointdemo(){}

//前置通知
@Before(value = "pointdemo()")
public void before(){
    System.out.println("before...");
}
```

 

**设置增强类优先级**

当有多个增强类对同一个方法进行增强时，需要设置增强类优先级。

在增强类上添加注解：`@Order(数字类型值)`，数字类型值越小优先级越高。





**基于配置文件实现AOP**

了解即可。

```xml
<!--1、创建两个类，增强类和被增强类，创建方法（同上一样）-->
<!--2、在 spring 配置文件中创建两个类对象-->
<!--创建对象-->
<bean id="book" class="com.atguigu.spring5.aopxml.Book"></bean>
<bean id="bookProxy" class="com.atguigu.spring5.aopxml.BookProxy"></bean>
<!--3、在 spring 配置文件中配置切入点-->
<!--配置 aop 增强-->
<aop:config>
 <!--切入点-->
 <aop:pointcut id="p" expression="execution(* com.atguigu.spring5.aopxml.Book.buy(..))"/>
 <!--配置切面-->
 <aop:aspect ref="bookProxy">
 <!--增强作用在具体的方法上-->
 <aop:before method="before" pointcut-ref="p"/>
 </aop:aspect>
</aop:config>
```



---



# JdbcTemplate

Spring 框架对 JDBC 进行封装，使用 JdbcTemplate 方便实现对数据库操作.

`JdbcTemplate`是core包的核心类。它替我们完成了资源的创建以及释放工作，从而简化了我们对JDBC的使用。它还可以帮助我们避免一些常见的错误，比如忘记关闭数据库连接。JdbcTemplate将完成JDBC核心处理流程，比如SQL语句的创建、执行，而把SQL语句的生成以及查询结果的提取工作留给我们的应用代码。它可以完成SQL查询、更新以及调用存储过程，可以对ResultSet进行遍历并加以提取。它还可以捕获JDBC异常并将其转换成 `org.springframework.dao`包中定义的，通用的，信息更丰富的异常。



### JDBC

**步骤：**

* 导入包：需要包含包含数据库编程所需的JDBC类的包。 大多数情况下，使用`import java.sql.*`就足够了。

* 注册JDBC驱动程序：需要初始化驱动程序，以便可以打开与数据库的通信通道。

* 打开一个连接：需要使用`DriverManager.getConnection()`方法创建一个`Connection`对象，它表示与数据库的物理连接。

* 执行查询：需要使用类型为`Statement`的对象来构建和提交SQL语句到数据库。

* 从结果集中提取数据：需要使用相应的`ResultSet.getXXX()`方法从结果集中检索数据。

* 清理环境：需要明确地关闭所有数据库资源，而不依赖于JVM的垃圾收集。

**举例：**

```java
import java.sql.*;//STEP 1: Import the package

public class FirstExample {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/emp";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";

   public static void main(String[] args) {
       Connection conn = null;
       Statement stmt = null;
       try{
          //STEP 2: Register JDBC driver
          Class.forName("com.mysql.jdbc.Driver");

          //STEP 3: Open a connection
          conn = DriverManager.getConnection(DB_URL,USER,PASS);

          //STEP 4: Execute a query
          stmt = conn.createStatement();
          String sql;
          sql = "SELECT id, first, last, age FROM Employees";
          ResultSet rs = stmt.executeQuery(sql);

          //STEP 5: Extract data from result set
          while(rs.next()){
             //Retrieve by column name
             int id  = rs.getInt("id");
             int age = rs.getInt("age");
             String first = rs.getString("first");
             String last = rs.getString("last");

             //Display values
             System.out.print("ID: " + id);
             System.out.print(", Age: " + age);
             System.out.print(", First: " + first);
             System.out.println(", Last: " + last);
          }
          //STEP 6: Clean-up environment
          rs.close();
          stmt.close();
          conn.close();
       }catch(SQLException se){
          //Handle errors for JDBC
          se.printStackTrace();
       }catch(Exception e){
          //Handle errors for Class.forName
          e.printStackTrace();
       }finally{
          //finally block used to close resources
          try{
             if(stmt!=null)
                stmt.close();
          }catch(SQLException se2){
          }// nothing we can do
          try{
             if(conn!=null)
                conn.close();
          }catch(SQLException se){
             se.printStackTrace();
          }//end finally try
       }//end try
       System.out.println("There are so thing wrong!");
	}//end main
}//end FirstExample
```



### JDBC模板配置

（1）引入相关配置文件（com.alibaba.druid/org.springframework.spring-jdbc）

（2）在 spring 配置文件配置数据库连接池

```xml
<!--连接池配置-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
      destroy-method="close">
    <property name="url" value="jdbc:mysql:///test"/>
    <property name="username" value="root" />
    <property name="password" value="root" />
    <property name="driverClassName" value="com.mysql.jdbc.Driver" />
</bean>
```

（3）配置 JdbcTemplate 对象，注入 DataSource

```xml
<!--创建JDBCTemplate对象，注入dataSource属性-->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

（4）创建service类（service中注入dao对象）和dao类，向dao中注入jdbcTemplate对象。

```java
//bean文件中开启组件扫描
<context:component-scan base-package="com.twhupup"></context:component-scan>
//Service包
@Service
public class BookService {
    //注入 dao
    @Autowired
    private BookDao bookDao;
}
//Dao包
@Repository
public class BookDaoImpl implements BookDao {
    //注入 JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
```



### JDBC模板操作

##### **增删改**

（1）根据数据库创建对应的实体类，定义set，get方法。

```java
public class Book {
    private String bookId;
    private String bookName;
    private String bookStatus;

    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookStatus() {
        return bookStatus;
    }
    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }
}
```

（2）在dao层实现数据库的添加、删除、修改操作。

在dao的add方法中调用jdbcTemplate对象的update方法实现添加操作

```java
@Repository
public class BookDaoImpl implements BookDao{
    //注入JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Book book) {
        String sql = "insert into t_book values(?,?,?)";
        Object[] args = {book.getBookId(),book.getBookName(),book.getBookStatus()};
        //调用jdbcTemplate对象的update方法实现添加操作
        int update = jdbcTemplate.update(sql,args);
        System.out.println(update);
    }
    
    @Override
    public void delete(int id) {
        String sql = "delete from t_book where book_id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void update(Book book) {
        String sql = "update t_book set book_name=?,book_status=? where book_id=?";
        Object[] args = {book.getBookName(),book.getBookStatus(),book.getBookId()};
        jdbcTemplate.update(sql,args);
    }
}
//BookDao接口文件
public interface BookDao {
    void add(Book book);
    void delete(int id);
    void update(Book book);
}
```

（3）在Service层调用dao，完成服务。

```java
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void addBook(Book book){
        bookDao.add(book);
    }
    
    public void deleteBook(int id){
        bookDao.delete(id);
    }

    public void updateBook(Book book){
        bookDao.update(book);
    }
}
```

（4）测试方法中获取xml文件的context对象，通过context.getBean()获取Service的类对象。调用Service的方法实现dao中功能。

```java
    public void testBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        BookService bookService = context.getBean("bookService", BookService.class);

        Book addbook = new Book();
        addbook.setBookId("1");
        addbook.setBookName("java");
        addbook.setBookStatus("borrowed");
        bookService.addBook(addbook);
```



##### 查询

**查询返回标量值**

`queryForObject(String sql, Class<T> requiredType)`	方法将根据sql语句和requiredtype返回对应的查询值。

```java
//查询表记录数
@Override
public int selectCount() {
	String sql = "select count(*) from t_book";
    //queryForObject方法中：第一个参数代表--sql语句；第二个参数代表--返回类型class  
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return count;
}
```

**查询返回对象**

场景：查询单条记录

`queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)`方法用于执行mysql执行语句，并将查询内容封装为指定的java对象。

其中BeanPropertyRowMapper将数据库查询结果转换为Java类对象，<T>中填入需要转换的类型。 常应用于使用Spring的JdbcTemplate查询数据库，获取List结果列表，数据库表字段和实体类自动对应。

```java
//查询返回对象
@Override
public Book findBookInfo(String id) {
    String sql = "select * from t_book where user_id=?";
 //调用方法
/*
	queryForObject方法中：
		第一个参数：sql语句
		第二个参数：RowMapper接口针对返回不同类型数据，使用这个接口的实现类 完成数据封装
		第三个参数：sql 填入值
*/
    Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
    return book;
}
```

**查询返回集合**

场景：查询图书列表分页（多条记录）

`query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)`方法用于执行mysql语句，并将查询内容封装为java对象数组。

```java
@Override
public List<Book> findAllBook() {
	String sql = "select * from t_book limit 0,3";
    List<Book> bookList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Book>(Book.class));
    return bookList;
}
```



##### 批量操作

**语法：**`batchUpdate(String sql, List<Object[]> batchArgs)`	batchArgs是一个包含多个一维数组的ArrayList

* ArrayList语法：
  * 创建：`List<T> newList = new ArrayList<>();`
  * 添加元素：`newList.add(Object);`

批量添加、修改、删除：

```java
// 批量添加
@Override
public void batchAddBook(List<Object[]> batchArgs) {
    String sql = "insert into t_book values(?,?,?)";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
}
// 批量修改
@Override
public void batchUpdBook(List<Object[]> batchArgs) {
    String sql = "update t_book set book_name=?,book_status=? where book_id=?";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
}
// 批量删除
@Override
public void batchDelBook(List<Object[]> batchArgs) {
    String sql = "delete from t_book where book_id=?";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
}

// 测试方法中
// 批量添加
List<Object[]> addBatchArgs = new ArrayList<>();
addBatchArgs.add(new Object[]{"3","Mysql","borrowed"});
addBatchArgs.add(new Object[]{"4","go","lost"});
bookService.batchAdd(addBatchArgs);
// 批量修改
List<Object[]> updBatchArgs = new ArrayList<>();
updBatchArgs.add(new Object[]{"c++","sold","3"});
updBatchArgs.add(new Object[]{"linux","sold","4"});
bookService.batchUpdate(updBatchArgs);
// 批量删除
List<Object[]> delBatchArgs = new ArrayList<>();
delBatchArgs.add(new Object[]{"3"});
delBatchArgs.add(new Object[]{"4"});
bookService.batchDelete(delBatchArgs);
```



### 事务

##### 概念

事务是数据库操作最基本单位，要么都成功，要么都失败。（典型场景：转账）

事务四个特性ACID：原子性，一致性，隔离性，持久性。

**知识点：**

* 我们一般会选择将事务添加到Service层（业务逻辑层）.

* Spring事务管理有两种方式：**编程式事务管理 和 声明式事务管理**，一般使用声明式事务管理，底层使用AOP原理。

* 声明式事务管理有两种方式：**基于xml配置方式 和 基于注解方式**，一般使用注解方式。
* 在Spring中进行声明式事务管理，底层使用了AOP原理。

* Spring事务管理提供了一个API接口，叫做**事务管理器**，这个接口针对不同的框架提供不同的实现类。
  * 对于使用JdbcTemplate进行数据库交互，则使用DataSourceTransactionManager实现类；
  * 如果整合Hibernate框架则使用HibernateTransactionManager实现类，具体情况具体使用。



##### 声明式事务管理

**基于注解方式（常用）**

**实现**

（1）在配置文件中创建事务管理器，注入dataSource属性。

```xml
<!--创建事务管理器-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

```

（2）开启tx（transaction）名称空间，开启事务注解。

```xml
<!--开启tx名称空间-->
<beans xmlns:tx="http://www.springframework.org/schema/tx"...>...</beans>
<!--开启事务注解-->
<tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
```

（3）在service类添加事务注解`@Transactional`（或指定service中的方法）

使用方法：添加在类上表示类中的所有方法添加事务，添加在方法上表示为指定方法添加事务。

 ```
@Service
@Transactional
public class UserService {
    ...
    // 相关服务方法
}
 ```



**参数配置**

在service的`@transaction`注解中，可以配置事务相关参数。

例：`@Transactional(timeout = -1,propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)`

![1655005254783](D:\WorkSpace\git\Mine\Notes\Using\assets\1655005254783.png)

* **propagation:** 事务传播行为。描述了多事务方法（对数据库数据进行变化的操作）直接进行调用，这个过程事务是如何进行管理的。

  事务传播行为有7种，常用以下两种：

  * **REQUIRED（默认）：**支持当前事务，如果当前没有事务，就新建一个事务。举例：如果A方法本身有事务，则调用B方法之后，则B方法会使用A方法中的事务；如果A方法本身没有事务，则调用B方法之后，会创建新的事务。
  * **REQUIRED_NEW：**新建事务，如果当前存在事务，把当前事务挂起。 举例：如果A方法调用B方法，无论A方法是否有事务，都会创建新的事务。

* **isolation：**事务的隔离级别。事务存在三个问题：脏读、不可重复读、幻读。

  事务的隔离级别有四种：

  | 隔离级别              | 脏读 | 不可重复读 | 幻读 |
  | --------------------- | ---- | ---------- | ---- |
  | Read uncommitted      | √    | √          | √    |
  | Read committed        | ×    | √          | √    |
  | Repeatable Read(默认) | ×    | ×          | √    |
  | Serializable          | ×    | ×          | ×    |

* **timeout：**事务的超时时间。事务需要一定时间提交，如果不提交进行回滚，默认值为-1表示不进行超时操作，单位为秒。

* **readOnly：**是否只读。readOnly默认值为False，可以查询也可以删除修改。

* **rollBackFor：**回滚。设置出现哪些异常进行事务回滚。

* **noRollBackFor：**不回滚。设置出现哪些异常不进行事务回滚。



**基于XML文件**

实现

（1）配置事务管理器（同上）

（2）配置通知（通知是指对切入点增强的逻辑【事务】）

（3）配置切入点和切面（切入点是指需要增强的方法【service方法】）

```xml
<!--创建事务管理器-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
<!--配置通知-->
<tx:advice id="txadvice">
    <tx:attributes>
        <tx:method name="accountMoney" propagation="REQUIRED" isolation="REPEATABLE_READ"/>
    </tx:attributes>
</tx:advice>
<!--配置切入点和切面-->
<aop:config>
    <aop:pointcut id="pt" expression="execution(* com.twhupup.service.UserService.*(..))"/>
    <aop:advisor advice-ref="txadvice" pointcut-ref="pt"></aop:advisor>
</aop:config>
```



##### 纯注解声明式事务管理

**注解功能**

* `@Configuration`	表示该类为注解类

* `@ComponentScan(basePackages = classpath)`    开启组件扫描

* `@EnableTransactionManagement`    开启事务注解扫描

* `@Bean`    表示方法产生一个由Spring管理的bean对象



**举例：**

```java
@Configuration	//表示该类为注解类
@ComponentScan(basePackages = "com.twhupup")	//开启组件扫描
@EnableTransactionManagement	//开启事务注解
public class TxConfig {
    //创建数据库连接池
    @Bean
    public DruidDataSource getDruidDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///user_db");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    //创建JdbcTemplate对象
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        //到IOC容器中根据类型找到DataSource(防止重复创建datasource对象)
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    //创建事务管理器对象
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}

//测试类中
@Test
public void TestAccountMoney_Annotation(){
    ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
    UserService userService = context.getBean("userService", UserService.class);
    userService.accountMoney();
}
```



# Spring-Web

