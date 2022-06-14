---
typora-copy-images-to: assets
---

# Spring框架

### 概述

Spring是轻量级开源的JavaEE框架，引入jar包很少。提供了功能强大的IOC和Aop（两大核心部分）及Web MVC等功能。

**目的：**用于解决企业应用开发的复杂性。

**IOC：**控制反转，把创建对象过程交给Spring进行管理。

**Aop：** 面向切面，不修改源代码的前提下进行功能增强。

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

**结构：**一个XML文件通常包含**文件头**和**文件体**两大部分

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

**基本要点：**

* IOC思想需要基于IOC容器，IOC底层是一个对象工厂；
* Spring提供了IOC容器实现的两种方式：
  * BeanFactory接口：是IOC容器基本实现方式，是Spring内部的使用接口，不提供给开发人员进行使用（加载配置文件时候**不会创建对象**，在获取对象时才会创建对象。）
  * ApplicationContext接口：BeanFactory接口的子接口，提供更多更强大的功能，提供给开发人员使用（加载配置文件时候就会把在配置文件的对象进行创建）【推荐使用，服务器在启动过程中就加载对象】



### 基于xml的Bean管理

主要负责（1）Spring创建对象；（2）Spring注入属性。

管理操作主要有两种方式：（1）基于**xml配置文件**方式实现；（2）基于**注解方式**实现。



##### **创建对象**

在spring配置文件中，使用bean标签，标签里添加对应属性，即可完成对象的创建。

**语法：**``<bean id="user" class="com.company.User"></bean>``

**注意事项：**

* bean标签有很多属性，常用属性如下：
  * id属性【常用】：给相应对象添加别名，id是bean的唯一标识，IoC容器中bean的id不能重复，否则报错。
  * class属性【常用】：对象所属类的全路径，为bean的全限定类名，指向classpath下类定义所在位置。
  * name属性：name属性基本等同于id属性，不常用，name属性不能重复，且id和name属性也不能重复，和id的区别主要在于name属性可以添加符号。
  * factory-method工厂方法属性：通过该属性，我们可以调用一个指定的静态工厂方法，创建bean实例。
  * factory-bean属性：factory-bean是生成bean的工厂对象，factory-bean属性和factory-method属性一起使用，首先要创建生成bean的工厂类和方法。
* 创建对象时，默认调用类中的无参构造器方法，如果需要使用有参构造器，需要在bean标签中添加properties；



##### **注入属性**

语法：`<property name="xxx" value=“xxx”></property>`	name表示类中的属性名，value表示向属性注入的值；（置于bean标签结构体内）

**知识点：**

* 如果不写bean标签的方法体，则会默认调用无参构造器；
* 如果类有有参构造器，**必须**添加<constructor-arg>标签并添加**所有**属性的值。

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

使用p名称空间注入，可以简化xml配置方式。（注意p名称空间注入相当于set()方法注入，需要类中定义setter方法）

**步骤：**

(1) 添加p名称空间在配置文件头部

`xmlns:p="http://www.springframework.org/schema/p"`

(2) 在bean标签进行属性注入（set方式注入的简化操作）

`<bean id="book" class="com.atguigu.spring5.Book" p:bname="very" p:bauthor="good"></bean>`



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
        System.out.println("service add...............");
        userDao.update();//调用dao方法
    }
}
```
```java
//另一个包中
public class UserDaoImpl implements UserDao {//dao类
    @Override
    public void update() {
        System.out.println("dao update...........");
    }
}
//
public interface UserDao {
	void update();
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



**xml自动装配**

根据指定的装配规则（属性名称或属性类型），Spring会自动将匹配的属性值进行注入。autowire是在bean标签中设置的。**（较少使用）**

（1）根据属性名称进行自动装配

在bean标签中添加`autowire="byName"`，再添加需要插入的属性的bean标签

**注意点：**注入值bean的id与需要插入的类属性名称一致，否则注入失败（不报错）；（2）必须存在set方法，否则注入失败（不报错）

（2）根据属性类型进行自动装配

在bean标签中添加`autowire="byType"`，再添加需要插入的属性的bean标签

**注意点：**如果有多个相同类的bean对象，编译器无法确认使用哪个对象；（2）必须存在set方法，否则注入失败（不报错）



**引入外部属性文件**

需求：

（1）配置德鲁伊连接池

（2）引入德鲁伊依赖jar包

1、直接配置数据库信息

demo（手动配置）:

```xml
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/userDb"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</bean>
```



2、通过引入外部属性文件配置数据库

（1）创建外部属性文件，properties格式文件，写入数据库信息（这里属性名前加prop是为了避免属性名冲突）

```properties
prop.driverClass=com.mysql.jdbc.Driver
prop.url=jdbc:mysql://localhost:3306/userDb
prop.userName=root
prop.password=root
```

（2）将外部properties属性引入到spring配置文件中

* 首先在spring配置文件中引入context名称空间

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/beans/spring-context.xsd">
```

* 在spring配置文件使用标签引入外部属性文件

```xml
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${prop.driverClass}"/>
    <property name="url" value="${prop.url}"/>
    <property name="username" value="${prop.userName}"/>
    <property name="password" value="${prop.password}"/>
</bean>
<!--引入外部属性文件-->
<context:property-placeholder location="classpath:jdbc.properties"/>
```



### 基于注解的Bean管理

**知识点**

（1）注解是代码的特殊标记，格式为`@注解名称(属性名1=属性值1，属性名2=属性值2...)`；

（2）注解可以作用在**类、方法、属性**中；

（3）注解的目的：用更简洁的方式配置管理xml文件；



##### 组件扫描

组件扫描用于从指定的classpath下自动扫描，侦测和实例化具有特定注解的bean。

对于扫描到的组件，Spring有**默认**的命名策略：使用非限定类名，第一个字母小写。
也可以在注解中通过value属性值标识组建的名称。不使用value属性标识Bean的名称，默认以类名小写命名。

当在组件类上使用了特定的注解之后, 还需要在 Spring 的配置文件中配置以下项：
base-package 属性指定一个需要扫描的基类包，Spring 容器将会扫描这个基类包里及其子包中的所有类，当需要扫描多个包时, 可以使用逗号分隔。

用法1（include-filter）

```xml
<context:component-scan base-package="com.twhupup" use-default-filters="false">
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>
</context:component-scan>
```

- `use-default-filters`属性用于选择是否使用默认filter，如果值为false表示不扫描所有内容，而使用自己配置的filter（不设置`use-default-filters`属性，则其设为默认值true，表示需要扫描所有内容）
- `<context:include-filter>`标签写在`<context:component-scan>`的结构体内 ，设置filter中对指定的内容进行扫描
- `type="annotation" expression="org.springframework.stereotype.Service"/>` 表示在base-package的范围内只扫描Service的注解类



用法2（exclude-filter）

```xml
<context:component-scan base-package="com.twhupup">
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

- `<context:exclude-filter>`标签写在`<context:component-scan>`的结构体内 ，设置filter中哪些内容不进行扫描



##### 创建对象

**方式：**

（1）@Component	普通注解

（2）@Service	一般用于service层的对象创建，也允许用在其他位置

（3）@Controller		一般用于web层，也允许用在其他位置

（4）@Repository	一般用于dao层，也允许用在其他位置

上述的四个注解功能都一样，都可以用于创建bean实例，分为若干方式主要用于方便开发



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

* 在测试方法中，读取配置文件，获取bean对象，使用反射机制调用方法

```java
@Test
public void testService(){
    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    UserService userService = context.getBean("userService", UserService.class);
    System.out.println(userService);
    userService.add();
}
```



##### 注入属性

**方式：**

（1）**@AutoWired**	根据属性类型进行自动装配**（常用）**

（2）@Qualifier		根据属性名称进行注入

（3）@Resource		可以根据类型和名称注入

（4）@Value		注入普通类型属性（int/float/String...）



**实现：**

（1）@AutoWired 根据类型注入

* 把service和dao对象进行创建，在service和dao类添加创建对象注释

  ```java
  @Service(value="userService")//相当于在bean文件中配置：<bean id="userService" class="com.twhupup.service.UserService"/>
  public class UserService {
      public void add(){
          System.out.println("Service add");
      }
  }
  //另一个包内
  @Repository
  public class UserDaoImp1 implements UserDao{
      @Override
      public void add() {
          System.out.println("dao add...");
      }
  }
  ```

* 在service中注入dao对象，在service类中添加dao类型属性，在属性上使用注释注入属性

  ```java
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

  **注意点：**

  * 使用注解方式注入属性不需要类具有set方法；
  * 如果定义的接口对象有多个实现类则会报错，因为spring无法确认注入哪一个实现类对象；
  * 注释中的value值如果默认则为类名+首字母改小写



（2）@Qualifier 根据名称注入，一般和@AutoWired一起使用（这里的名称指注释的value值，默认为类名+首字母小写）

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



（3）@Resource 可以根据类型注入，也可以根据名称注入

* 根据类型注入时，和@AutoWired用法一样；

* 根据名称注入时：使用name属性指定名称，e.g.`@Resource(name="userDaoImpl")`

**注意点：**Resource是javax扩展包中的注解，不是spring官方的注解。



（4）@Value	注入普通类型属性

@Value(value=xxx)可以将value值注入到普通类型属性中，相当于bean手动配置中的property

**注意点：**@Value注释的注入属性会滞后于属性的自定义赋值



**纯注解开发**

使用配置类代替配置文件，SpringBoot的使用主要就是基于纯注解开发。

（1）创建配置类，替代xml配置文件

```java
@Configuration
@ComponentScan(basePackages="com.twhupup")
public class SpringConfig {
}

```

在配置类上使用@Configuration注解表示该类为注解类；

@ComponentScan用于指定组件扫描区域，替代xml配置文件

（2）编写测试类

`ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);`	加载配置类的方，其他和原来一致



---



### Bean基本知识

##### FactoryBean

在Spring中有两种类型的bean，一种为普通bean，另外一种叫做FactoryBean。

普通bean：在bean配置文件中定义的类型返回对应的类型；

工厂bean：在bean配置文件中定义类型和返回类型不一致。



创建工厂bean的步骤：

* 创建类，让这个类作为工厂bean，实现接口FactoryBean；
* 实现接口中的方法，在实现方法中定义返回bean的类型。



##### Bean作用域

在 Spring 中默认情况 bean 是单实例对象。需要设置作用域时：

（1）在 spring 配置文件 bean 标签里面有属性（scope）用于设置单实例还是多实例

（2）scope 属性值（默认）singleton，表示是单实例对象；另一种属性值 prototype，表示是多实例对象

```xml
<bean id="book" class="com.atguigu.spring5.collectiontype.Book" scope="prototype"><!--设置为多实例-->
        <property name="list" ref="bookList"></property>
</bean>
```



单实例和多实例的创建时刻：

设置 scope 值是 singleton 时候，**加载 spring 配置文件时**就会创建单实例对象 ；设置 scope 值是 prototype 时候，不是在加载 spring 配置文件时候创建对象，**在调用 getBean 方法时**创建多实例对象




##### Bean生命周期

生命周期是对象从创建到销毁的过程，bean生命周期也就是bean对象的创建到销毁过程。

bean的生命周期过程：

（1）通过构造器创建bean对象实例（无参构造）

（2）为bean属性设置值和其它bean引用（调用set方法）

（3）将bean实例传递给bean的后置处理器方法postProcessBeforeInitialization

（4）调用bean的初始化init-method方法（需要进行配置初始化）

（5）将bean实例传递给bean的后置处理器方法postProcessAfterInitialization

（6）得到bean对象，使用bean

（7）容器关闭后，调用bean的销毁方法（需要进行配置销毁）



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



### 基于注解的操作

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





### 基于配置文件的操作

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



# 事务

### 概念

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



### 声明式事务管理

##### 基于注解方式（常用）

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



##### 基于XML文件

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



### 纯注解声明式事务管理

**注解功能**

* `@Configuration`	表示该类为注解类

  * 不加`@Configuration`，Spring依然可以扫描类中的注解也可以完成IOC和DI。但是无法实现对象的单例模式，也就是说每调用一次方法就会创建一个新的对象。

    添加`@Configuration`后，配置类会被代理，然后代理对象被Spring进行扫描。在代理对象的方法中会优先检查容器中是否已经存在某个类的对象，如果已经存在则从容器中取出该对象进行使用。如果容器不存在才会调用真正的配置类中的方法来进行对象的创建。

  * 注解类的使用方法：在测试时使用`AnnotationConfigApplicationContext(A.class)方法表示返回注解类A的ApplicationContext对象`

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




