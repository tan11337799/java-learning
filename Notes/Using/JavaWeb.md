---
typora-copy-images-to: assets
typora-root-url: assets
---

# JavaWeb

### 概念

JavaWeb是指所有通过Java语言编写的通过浏览器访问程序的总称。JavaWeb是基于请求和响应开发的。

请求：请求是指客户端给服务器发送数据，叫请求Request；

响应：响应是指服务器给客户端回传数据，叫响应Response。



**两种模式**

**BS：**浏览器服务器架构模式

* 优点：不需要安装；维护成本低
* 缺点：所有计算和存储均由服务器完成，服务器负荷重；服务器端计算完成后返回给客户端，因此客户端和服务器需要频繁通信，网络负荷重

**CS：**客户端服务器架构模式

* 优点：充分利用客户端计算机资源，减轻服务器的负荷【安全要求低的任务交给客户端执行和存储，同时减轻服务器和网络的压力】
* 缺点：需要安装；升级维护成本较高



**Web资源分类**

Web资源按实现的技术和呈现效果的不同又分为：**静态资源**和**动态资源**。

静态资源可以简单的理解成文本、HTML、图片、JS等，内容是固定的，无需处理、无需查询数据库就可以直接发给请求者。

动态资源则是需要服务器程序进行处理，根据不同条件在页面也显示不同的数据，内容的更新不需要修改页面。缺点就是速度上没有静态资源响应快。客户端在获取动态资源，先将请求交给web的一个存储点，存储点链接数据库，数据库处理数据并发给服务器，服务器再发给客户端渲染展示。

静态资源：html、CSS、txt、MP4、jpg …

动态资源：jsp页面、Servlet程序 …



**常用Web服务器**

**Web服务器**一般指的是“网站服务器”，是某种驻留在因特网上的计算机程序，可以向请求终端提供服务，主要功能时存储、处理和传递网页给“客户”，传递内容一般是HTML文档、图像、样式表或脚本等，也可以放置网站文件以供浏览或下载。

常用Web服务器包括Tomcat、JBoss、Jetty…



### HTTP协议

HTTP被称为超文本传输协议。

**特点：**HTTP是无状态的。

**分类：**HTTP可以分为请求和响应。

**HTTP请求报文**

包含三个部分：1.请求行；2.请求头；3.请求体。

**请求行**展示当前请求的最基本信息，其中包含三个信息：请求的方式（GET/POST/...）、请求的URL、请求的协议（一般是HTTP1.1）

**请求头**通过具体的参数对本次请求进行详细的说明，其中比较重要的请求消息头：

| 名称           | 功能                                                 |
| -------------- | ---------------------------------------------------- |
| Host           | 服务器的主机地址                                     |
| Accept         | 声明当前请求能够接受的『媒体类型』                   |
| Referer        | 当前请求来源页面的地址                               |
| Content-Length | 请求体内容的长度                                     |
| Content-Type   | 请求体的内容类型，这一项的具体值是媒体类型中的某一种 |
| Cookie         | 浏览器访问服务器时携带的Cookie数据                   |

**请求体**作为请求的主体，发送数据给服务器。具体来说其实就是POST请求方式下的请求参数。有三种情况：（1）get方式则没有请求体，请求参数附着在URL地址后，但有queryString；（2）post方式有请求体，form data；（3）json格式，有请求体，request Payload



**HTTP响应报文**

包含三个部分：1.响应行；2.响应头；3.响应体。

**响应行**包含三个信息：HTTP协议版本、响应状态码、响应状态的文字说明

| 状态码 | 含义                                                      |
| ------ | --------------------------------------------------------- |
| 200    | 服务器成功处理了当前请求，成功返回响应                    |
| 302    | 重定向                                                    |
| 400    | [SpringMVC特定环境]请求参数问题                           |
| 403    | 没有权限                                                  |
| 404    | 找不到目标资源                                            |
| 405    | 请求方式和服务器端对应的处理方式不一致                    |
| 406    | [SpringMVC特定环境]请求扩展名和实际返回的响应体类型不一致 |
| 50X    | 服务器端内部错误，通常都是服务器端抛异常了                |

**响应消息头**是响应体的说明书，用于服务器端对浏览器设置数据：

| 名称           | 功能                                                 |
| -------------- | ---------------------------------------------------- |
| Content-Type   | 响应体的内容类型                                     |
| Content-Length | 响应体的内容长度                                     |
| Set-Cookie     | 服务器返回新的Cookie信息给浏览器                     |
| location       | 在**重定向**的情况下，告诉浏览器访问下一个资源的地址 |

**响应体**是服务器返回的数据主体，可能是各种数据类型。（比如html文本）



### 错误列表

404：资源丢失（比如URL填写错误，访问WEB-INF目录下的资源，Web引用启动时控制台已抛出异常，访问任何资源都是404

405：请求方法不支持，比如设置表单method=post，则必须对应doPost方法。



---



### 前端知识

**前端目前主要使用以下三种语言，被称为前端三剑客：**

- HTML：超文本标记语言，决定页面上显示什么内容
- CSS：页面上的内容显示的风格（决定页面的美观程度）
- JavaScript：页面特效



##### HTML

**背景：**浏览器端发送请求（index.html）给服务器端，服务器端返回响应（`<html><head></head><html>`）给浏览器。

**介绍：**HTML被称为超文本标记语言。html是解释型语言，这类语言直接以源代码的形式出现，运行的时候再实时解析为机器码并执行。这表示每句代码只有在运行时，系统才知道这句代码是否有错。

**语法**

* HTML有两种标记：（1）单标记：独自使用就可以表达；（2）双标记：成对使用，又开始和结束。
* HTML的注释：单行和多行均用`<!--xxx-->`；
* 当属性名等于属性值时，等号和等号后面的内容可以省略；
* 嵌套使用，不能交叉使用；
* 不区分大小写，但标记名和属性建议都用小写字母；
* 注重缩进，有层次。偏于检查和改编。（用空格，一次缩进两个空格）
* 网站首页文件名一般是index.html或default.html

**基本结构**

```html
<html>
	<head>:保留信息不会显示在正文中
		<title>打开的网页名字</title>
		<meta character='UTF-8'>
        <style>
        	css..
        </style>
	</head>
	<body>:正文<br/>内容
		<h1>标题</h1>
		<p>段落1<p>
		<p>段落2<p>
	</body>
</html>	
```

* html页面由一对标签组成：`<html></html>`，分别称为开始标签和结束标签。

* `<head> `：为html文件的保留信息，不会显示在网页中，其中的title表示网页的标题，meta character表示HTML使用的编码方式；CSS用于定义HTML元素样式。

* `<br/>`：是一个单标签，表示换行。

* `p`：表示段落标签。

* `<img src="" alt="" width="" height="">`：用于图片的插入，**src 属性和 alt 属性是`<img>` 标签必要属性。**src表示图片的路径，width和height用于设置图片的大小，alt用来规定图片的替代文本，当图片无法显示时将显示该属性。

* `<h1>..<h6>`：标题标签。

* `<u> <b> <i> <sup> <sub>`：分别表示下划线、粗体、斜体、上标、下标，要用双标记方式使用，需要多少种修饰就要多少对标签

* `&lt &gt &ge &copy...`：为html中的实体，分别表示小于、大于、大于等于、版权…

* `<a href="" target="">`：表示超链接，href表示超链接引导的地址，标签内为链接的名字。target标签用于网页的打开方式。`_self`表示在当前窗口打开默认值，`_blank`表示在新窗口打开，`_parent`表示在父窗口打开，`_top`在顶层窗口打开

*  `<div>`：用于html的分块分层



**列表标签**

HTML存在三种列表：

- 有序列表，使用 <ol> + <li> 标签。<ol>创建了一个有序列表，会为列表进行编号。<li>表示列表的每一项。

- 无序列表，使用 <ul> + <li> 标签。<ol>创建了一个无序列表，会为列表进行编号。<li>表示列表的每一项。

- 定义列表，使用 <dl> + <dt> + <dd> 标签。<dl>表示创建列表，<dt>定义了一个概念（术语），<dd>用于对概念进行解释。

  ```html
  <dl>
      <dt>标题1<dt>
      <dd>描述文本2<dd>
      <dt>标题2<dt>
      <dd>描述文本2<dd>
      <dt>标题3<dt>
      <dd>描述文本3<dd>
  </dl>
  ```

e.g.

```html
<!--body内-->
<p style="margin:0px auto;">煮米饭的步骤：</p>
    <ol style="margin:0px auto; padding-left:20px; list-style:upper-latin;">
        <li>将水煮沸</li>
        <li>加入一勺米</li>
        <li>搅拌均匀</li>
        <li>继续煮10分钟</li>
    </ol>
    <p style="margin-bottom:0px;">早餐的种类：</p>
    <ul style="margin:0px auto; padding-left:20px; list-style:square;">
        <li>鸡蛋</li>
        <li>牛奶</li>
        <li>面包</li>
        <li>生菜</li>
    </ul>
    <dl style="margin-bottom:0px;">
        <dt><b>HTML</b></dt>
        <dd style="margin:auto auto 10px 0px;">HTML 是一种专门用来开发网页的标记语言。</dd>
        <dt><b>CSS</b></dt>
        <dd style="margin:auto auto 10px 0px;">CSS 层叠样式表可以控制 HTML 文档的显示样式，用来美化网页。</dd>
        <dt><b>JavaScript</b></dt>
        <dd style="margin:auto auto 10px 0px;">JavaScript 简称 JS，是一种用来开发网站（包括前端和后台）的脚本编程语言。</dd>
    </dl>
```



**表格**

```html
<!--body标签内-->
<table>
    <tr>
    	<td>姓名</td>
        <td>门派</td>
    </tr>
    <tr>
    	<td>虚竹</td>
        <td>灵鹫宫</td>
    </tr>
    <tr>
    	<td>乔峰</td>
        <td>丐帮</td>
    </tr>
</table>
```

table表示创建表格，tr表示行，td表示列，th表示表头的列。

table标签有如下属性（已淘汰）：border表示表格边框的粗细，width表示表格的宽度，cellspacing表示单元格间隔，cellpadding表示单元格填充

tr标签中属性：align，可以选择left right center表示行内单元格的位置。

td标签中属性：rowspan和colspan，表示列、行合并（值表示合并的长度）



**表单**

当您想要通过网页来收集一些用户的信息（例如用户名、电话、邮箱地址等）时，就需要用到 HTML 表单。表单可以接收用户输入的信息，然后将其发送到后端应用程序，例如 PHP、Java、Python 等，后端应用程序将根据定义好的业务逻辑对表单传递来的数据进行处理。

表单包含了如**输入框、复选框、单选按钮、提交按钮**等不同的表单控件，用户通过修改表单中的元素（输入文本，选择选项等）完成表单，通过表单中的提交按钮将表单数据提交给后端程序。

```html
<!--body标签内-->
<form action="demo1.html" method="post">
    昵称：<input type="text" name="nickname"/><br/>
    密码：<input type="password" name="pwd"/><br/>
    性别：<input type="radio" name="sex" value="male" checked="checked"/>男<input type="radio" name="sex" value="female"/>女<br/>
    爱好：<input type="checkbox" name="hobby" value="basketball"/>篮球<input type="checkbox" name="hobby" value="football"/>足球<br/>
    星座：<select name="star">
        <option value="1" select>白羊座</option>
        <option value="2">金牛座</option>
        <option value="3">天蝎座</option>
        <option value="4">天秤座</option>
        <option value="5">双子座</option>
        </select><br/>
    </select><br/>
    备注：<textarea name="remark" rows="5" cols="50"></textarea><br/>
    <input type="submit" value=" 注 册 "/>
    <input type="reset" value=" 重 置 "/>
    <input type="button" value=" 普通按钮 "/>
</form>
```

* `<input type="text" name=""/>` 表示文本框，其中name属性必须指定，否则文本框数据无法发送给服务器。
* `<input type=“password” name=""/>` 表示密码框
* `<input type="radio" name="" value="" checked/>` 表示单选框，name属性需要保持一致，起到互斥的效果；value表示选择后向服务器返回的值；`checked`表示默认选中该选项
* `<input type="checkbox" name="" value="“ checked/>` 表示多选框，name属性需要保持一致，这样服务器获取的是一个数组；其他同上单选框
* `<select name="star"><option value="" select>xxx</option></select>` 表示下拉列表，option标签内为下拉的选项，value表示返回值，`select`表示默认选中该选项
* `<textarea name="remark" rows="5" cols="50"></textarea>` 表示多行文本框，其value值是开始结束标签中的内容
* `<input type="submit"><input type="reset"><input type="button">`分别表示注册、重置、普通按钮。




##### CSS

CSS被称为**层叠样式表**，是一种描述 HTML 文档样式的语言，也就是在描述应该如何显示 HTML 元素。



**语法：**

使用：`<styles> ..css.. </style>>`

外部引入：`<link rel="stylesheet" href="./styles.css" />`

在html文件中的head标签中可以定义style标签，被style标签包围的范围是CSS环境，用于写CSS代码。

CSS规则集由**样式表**和**声明块**组成。样式表指向您需要设置样式的 HTML 元素，声明块包含一条或多条用分号分隔的声明。每条声明都包含一个 CSS 属性名称和一个值，以冒号分隔。多条 CSS 声明用分号分隔，声明块用花括号括起来。

![image-20220613155006411](assets\image-20220613155006411.png)



**样式表**

**按实现方式分类：**

大致可以分为元素样式表、id样式表、类样式表、

```css
/*元素样式表：根据元素名称来选择 HTML 元素*/
p,h1,h2 {
    text-align: center;
    color: red;
}
div p{/*选择div块中的p元素*/
    text-align: center;
    color: red;
}
/*id样式表：根据HTML元素的id属性来选择特定元素（不能以数字开头）*/
#para1 {
    text-align: center;
    color: red;
}
#para1 p{/*选择id为para1的p元素*/
    font-style: italic;
	text-align: right;
	margin-top: 0.5em;
}
/*类样式表：选择有特定class属性的HTML元素*/
.center {/*选择class="center"的元素*/
  text-align: center;
  color: red;
}
p.center {/*选择class="center"的<p>元素*/
  text-align: center;
  color: red;
}
/*通用样式表：选择页面上的所有 HTML 元素*/
* {
  text-align: center;
  color: blue;
}
```



**按位置分类：**

**内部样式表：**包含在 <style> 标签内，一个 <style> 标签就表示一个内部样式表。

**外部样式表：**如果 CSS 样式被放置在网页文档外部的文件中，则称为外部样式表，一个 CSS 样式表文档就表示一个外部样式表。（常用）

**嵌入样式表：**将style设置于标签的内部，以属性的形式出现。



**声明**

字体样式：`color:red;font-size=20px;`

宽度高度：`width=19px;height:20px;`	也可以设百分比

背景颜色：`background-color:#0F2D4C;`

边框：`border:1px solid red;`	设置边框的像素值、线样式、边框颜色

div位置：`margin-left:auto;margin-right=auto;`

文本位置：`text-align:center;`

表格：`border: 1px solid black;border-collapse:collapse;`  折叠边框



##### JavaScript

JavaScript是客户端的脚本语言，需要运行浏览器来解析执行 JavaScript 代码。JavaScript是一种属于网络的高级脚本语言,已经被广泛用于Web应用开发。
常用来为网页添加各式各样的动态功能,为用户提供更流畅美观的浏览效果。
通常JavaScript脚本是通过嵌入在HTML中来实现自身的功能的。 

**特点：**

* 交互性（它可以做的就是信息的动态交互）

* 安全性（不允许直接访问本地硬盘）

* 跨平台性（只要是可以解释 JS 的浏览器都可以执行，和平台无关）

**主要功能：**

- 嵌入动态文本于HTML页面
- 对浏览器事件做出响应
- 读写HTML元素
- 在数据被提交到服务器之前验证数据
- 检测访客的浏览器信息。控制cookies，包括创建和修改等
- 基于Node.js技术进行服务器端编程。 

**知识点：**

* JS 是弱类型，Java 是强类型。
  弱类型就是类型可变（由数据定义类型决定）；强类型就是定义变量时，类型已确定无法改变。
* 数值类型包括：数值型、



**语法：**

使用：`<script type="text/javascript"> ...(js code)... </script>`

外部引入：`<script type="text/javascript" src="js/demo.js"></script>`



# Servlet

### 概念

Java Servlet 是运行在 Web 服务器或应用服务器上的程序，它是作为来自 Web 浏览器或其他 HTTP 客户端的请求和 HTTP 服务器上的数据库或应用程序之间的中间层。

使用 Servlet，您可以收集来自网页表单的用户输入，呈现来自数据库或者其他源的记录，还可以动态创建网页。



**架构**

![1655219228314](/1655219228314.png)



**任务**

- 读取客户端（浏览器）发送的显式的数据。这包括网页上的 HTML 表单，或者也可以是来自 applet 或自定义的 HTTP 客户端程序的表单。
- 读取客户端（浏览器）发送的隐式的 HTTP 请求数据。这包括 cookies、媒体类型和浏览器能理解的压缩格式等等。
- 处理数据并生成结果。这个过程可能需要访问数据库，执行 RMI 或 CORBA 调用，调用 Web 服务，或者直接计算得出对应的响应。
- 发送显式的数据（即文档）到客户端（浏览器）。该文档的格式可以是多种多样的，包括文本文件（HTML 或 XML）、二进制文件（GIF 图像）、Excel 等。
- 发送隐式的 HTTP 响应到客户端（浏览器）。这包括告诉浏览器或其他客户端被返回的文档类型（例如 HTML），设置 cookies 和缓存参数，以及其他类似的任务。



### 原理

##### **继承关系**

Servlet是一个接口，全称为javax.servlet.Servlet；

Servlet继承于javax.servlet.GenericServlet抽象类，该抽象类又继承于javax.servlet.http.HttpServlet

HttpServlet => GenericServlet => Servlet



##### **相关方法**

**service方法**

```java
public void service(ServletRequest request, 
                    ServletResponse response) 
      throws ServletException, IOException{
}
```

service() 方法是执行实际任务的主要方法，当收到请求时，Servlet 容器（即 Web 服务器）会调用 service() 方法来处理来自客户端（浏览器）的请求，并把格式化的响应写回给客户端。

每次服务器接收到一个 Servlet 请求时（默认为GET），服务器会产生一个新的线程并调用服务。service() 方法检查 HTTP 请求类型（GET、POST、PUT、DELETE 等），并在适当的时候调用 doGet、doPost、doPut，doDelete 等方法。

底层逻辑：

* 首先获取请求的类型：`String method = req.getMethod();`

* 根据请求的类型调用不同的do方法，如果不存在相应类型，报501错误。

  ```java
  if (method.equals("GET")) {
      lastModified = this.getLastModified(req);
      this.doGet(req, resp);
  } else if (method.equals("HEAD")) {
      lastModified = this.getLastModified(req);
      this.maybeSetLastModified(resp, lastModified);
      this.doHead(req, resp);
  } else if (method.equals("POST")) {
      this.doPost(req, resp);
  } else if (method.equals("PUT")) {
      this.doPut(req, resp);
  } else if (method.equals("DELETE")) {
      this.doDelete(req, resp);
  } else if (method.equals("OPTIONS")) {
      this.doOptions(req, resp);
  } else if (method.equals("TRACE")) {
      this.doTrace(req, resp);
  } else {
      String errMsg = lStrings.getString("http.method_not_implemented");
      Object[] errArgs = new Object[]{method};
      errMsg = MessageFormat.format(errMsg, errArgs);
      resp.sendError(501, errMsg);
  }
  ```

* 如果没有重写相应的do方法，则报405错误：

  ```java
  if (protocol.endsWith("1.1")) {
      resp.sendError(405, msg);
  } else {
      resp.sendError(400, msg);
  }
  ```



**init方法**

```java
public void init() throws ServletException {
  // 初始化代码...
}
```

nit 方法被设计成只调用一次。它在第一次创建 Servlet 时被调用，在后续每次用户请求时不再调用。



**destroy方法**

```java
public void destroy() {
    // 终止化代码...
}
```

destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。

在调用 destroy() 方法之后，servlet 对象被标记为垃圾回收。



##### 生命周期

Servlet的生命周期对应于Servlet对象从创建到销毁的过程。分别对应上述的三个方法。Servlet的生命周期如下：

- Servlet 初始化后调用 **init ()** 方法。
- Servlet 调用 **service()** 方法来处理客户端的请求（一般有多个）。
- Servlet 销毁前调用 **destroy()** 方法。

<img src="/image-20220615155146747.png" alt="image-20220615155146747" style="zoom:50%;" />

**注意点：**

* Servlet对象只会创建一个，所有请求都是基于这个对象进行响应；
* 第一次请求时tomcat才会实例化和初始化，然后再进行服务。这样可以提高系统的启动速度，但会影响第一次请求的响应速度。（如果需要提高响应速度，应该设置Servlet的响应请求时机：通过`<servlet>`标签中的`<load-on-startup>`设置servlet启动的先后顺序）
* Servlet在容器中是单例的，线程不安全的；因此尽量不要在servlet中定义成员变量，也不要去修改成员变量的值，不要做逻辑判断。









### 部署

##### 软件

* **Java开发工具包**

  Servlet是由Java编写的，因此需要使用 Java 编译器 javac 编译 Servlet。

  安装JDK后，设置 PATH 和 JAVA_HOME 环境变量指向包含 java 和 javac 的目录：

  ```
  set PATH=C:\jdk1.5.0_20\bin;%PATH%
  set JAVA_HOME=C:\jdk1.5.0_20
  ```

* **Web应用服务器Tomcat**

  Apache Tomcat 是一款 Java Servlet 和 JavaServer Pages 技术的开源软件实现，可以作为测试 Servlet 的独立服务器，而且可以集成到 Apache Web 应用服务器。



##### 项目管理

* **创建Web工程**（Web有小蓝点表示创建成功）
  
  * 使用Maven模板创建：选择maven-archetype-webapp模板
* 手动创建：创建后右键工程，选择Add frameworks Support，勾选Web Application和Maven；然后进入Project Structure-Facets，添加Web框架，配置部署描述符地址（web.xml）和Web资源文件的地址。
  
* **配置启动属性**
  
  进入启动配置Run/Debug Configurations，添加Tomcat Server-Local（如果第一次设定需要设置模板，在Application server中选择使用的服务器Tomcat路径）
  
  * 添加部署包：Project Structure-Artifacts添加Web Application Exploded-From Modules表示将本项目以文件夹形式发布，Web Application Archive则表示将本项目打包为war包再发布。Output Directory表示Artifact包生成的位置。
  * 在Deployment中添加Artifact，如果不显示Artifact需要在，此时再添加Artifact；application context改为/便于访问。
  * 在Server中修改URL为`http://localhost:8080/网页名; `表示Tomcat启动时打开的网址。如果不指定网页，则会默认访问index.html/index.jsp/index.htm（这是web.xml文件中设置的`<welcome-file-list>`标签的作用）
  
* **添加需要的jar包**

  将jar包添加到web/WEB-INFO/lib下，如果使用maven-archetype-webapp模板，则会自动添加。

* **在web.xml中设置servlet配置**

  `<servlet>`标签中表示servlet的注册，表示为servlet名与路径建立关系。`<servlet-mapping>`表示设定servlet的映射，映射中的name要与servlet标签中的name一致。`<url-pattern>`标签表示指定匹配的请求（必须加/），该值与form表单中的action对应。【注意：一个servlet标签可以对应多个servlet-mapping，服务器可以根据收到的不同请求运行不同的业务逻辑】

  ```xml
  <servlet>
      <servlet-name>AddServlet</servlet-name>
      <servlet-class>com.twhupup.servlet.AddServlet</servlet-class>
  </servlet>
  <servlet-mapping>
      <servlet-name>AddServlet</servlet-name>
      <url-pattern>/add</url-pattern>
  </servlet-mapping>
  ```



### 实操

##### 处理表单数据

我们有事需要从浏览器到 Web 服务器传递信息，最终到后台程序。浏览器使用两种方法可将这些信息传递到 Web 服务器，分别为 GET 方法和 POST 方法。

Servlet 处理表单数据，这些数据会根据不同的情况使用不同的方法自动解析：

- **getParameter()：**您可以调用 request.getParameter() 方法来获取表单参数的值。
- **getParameterValues()：**如果参数出现一次以上，则调用该方法，并返回多个值，例如复选框。
- **getParameterNames()：**如果您想要得到当前请求中的所有参数的完整列表，则调用该方法。

举例：

```java
@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        req.setCharacterEncoding("UTF-8"); 
        String name = req.getParameter("fname");
        int price = Integer.parseInt(req.getParameter("price"));
        int number = Integer.parseInt(req.getParameter("fcount"));
        String des = req.getParameter("remark");
        int flag = fruitService.addFruit(new Fruit(name, price, number,des));
        System.out.println(flag==1?"添加成功！":"添加失败！");;
    }
}
```

**注意点：**

* 处理中文时，tomcat8之后只需要对post方式设置编码，get方式不需要设置；设置声明必须在所有参数获取之前



##### 会话跟踪

http是无状态的，这意味着每次客户端检索网页时，客户端打开一个单独的连接到 Web 服务器，服务器不保留之前客户端请求的任何记录，因此服务器无法判断两次请求是同一个客户端还是不同客户端发送的。我们通过会话跟踪技术解决http的无状态问题。

客户端第一次发送请求给服务器，服务器获取session；如果无法获取则创建新的session响应给客户端。

下一次客户端给服务器发送请求，会将sessionID一起发给服务器，服务器确定该次请求和上一次为同一个客户端。

Servlet 提供了 **HttpSession 接口**，该接口提供了一种跨多个页面请求或访问网站时识别用户以及存储有关用户信息的方式。

Servlet 容器使用这个接口来创建一个 HTTP 客户端和 HTTP 服务器之间的 session 会话。会话持续一个指定的时间段，跨多个连接或页面请求。

**常用API：**

`request.getSession([true])`	获取当前会话，如果没有则创新新会话

`request.getSession(false)` 	获取当前会话，如果不存在则返回null

`session.getId()`	获取session的id号

`session.isNew()`	判断当前session是否为新

`session.invalidate()`	使该session会话失效，并解除绑定到它上的任意对象

`setMaxInactiveInterval(int interval)`指定客户端的请求时间

保存作用域（注意保存的对象仅绑定当前的sessionid）：

`setAttribute(String name, Object value)` 使用指定的名称key绑定一个对象value到该 session 会话

`getAttribute(String name)`	返回该session会话中具有指定名称key的对象，如果没有则返回null

`removeAttribute(String name)`	移除指定名称k的对象



##### 服务器内部转发和客户端重定向



















