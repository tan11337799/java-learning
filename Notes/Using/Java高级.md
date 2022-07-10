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



**引入：**

使用：`<script type="text/javascript"> ...(js code)... </script>`

外部引入：`<script type="text/javascript" src="js/demo.js"></script>`



**常用函数：** 

(1)alert函数：显示一个警告对话框，包括一个OK按钮。 

(2)confirm函数：显示一个确认对话框，包括OK、Cancel按钮。 

(3)escape函数：将字符转换成Unicode码。 

(4)eval函数：计算表达式的结果。 

(5)isNaN函数：测试是(true)否(false)不是一个数字。 

(6)parseFloat函数：将字符串转换成符点数字形式。 

(7)parseInt函数：将符串转换成整数数字形式(可指定几进制)。 

(8)prompt函数：显示一个输入对话框，提示等待用户输入。

**数组函数：**

（1）join函数：转换并连接数组中的所有元素为一个字符串。

（2）length函数：返回数组的长度。

（3）reverse函数：将数组元素顺序颠倒。

（4）sort函数：将数组元素重新排序。

**字符串函数：**

javascript字符串函数完成对字符串的字体大小、颜色、长度和查找等操作：  

(1)anchor函数：产生一个链接点(anchor)以作超级链接用。anchor函数设定的链接点的名称，另一个函数link设定的URL地址。  

(2)big函数：将字体加到一号，与...标签结果相同。  

(3)blink函数：使字符串闪烁，与...标签结果相同。  

(4)bold函数：使字体加粗，与...标签结果相同。  

(5)charAt函数：返回字符串中指定的某个字符。  

(6)fixed函数：将字体设定为固定宽度字体，与...标签结果相同。  

(7)fontcolor函数：设定字体颜色，与标签结果相同。  

(8)fontsize函数：设定字体大小，与标签结果相同。  

(9)indexOf函数：返回字符串中第一个查找到的下标index，从左边开始查找。  

(10)italics函数：使字体成为斜体字，与...标签结果相同。  

(11)lastIndexOf函数：返回字符串中第一个查找到的下标index，从右边开始查找。  

(12)length函数：返回字符串的长度。(不用带括号)  

(13)link函数：产生一个超级链接，相当于设定的URL地址。  

(14)small函数：将字体减小一号，与...标签结果相同。  

(15)strike函数：在文本的中间加一条横线，与...标签结果相同。  

(16)sub函数：显示字符串为下标字(subscript)。  

(17)substring函数：返回字符串中指定的几个字符。  

(18)sup函数：显示字符串为上标字(superscript)。  

(19)toLowerCase函数：将字符串转换为小写。  

(20)toUpperCase函数：将字符串转换为大写。 





---



### Servlet

##### 概念

Java Servlet 是运行在 Web 服务器或应用服务器上的程序，它是作为来自 Web **浏览器**或其他 HTTP 客户端的请求和 HTTP 服务器上的数据库或**应用程序**之间的**中间层**。

使用 Servlet，您可以收集来自网页表单的用户输入，呈现来自数据库或者其他源的记录，还可以动态创建网页。



##### Tomcat

**介绍**

Tomcat是一个**Web应用服务器**，是Apache开源软件组织下的一个软件项目。Tomcat作为Web服务器，可以通过Http协议与客户端（浏览器）进行数据交互。同时也可以来发布Web应用。tomcat与Web应用是由不同的开发商开发的，那么这两种不同的软件系统是如何进行交互的呢。答案就是通过Servlet（最主要的接口）。Servlet是由Oracle公司指定的一个接口，用来规范Web服务器与Web应用之间的交互。Oracle制定了一系列交互相关接口以及对交互时的一些细节做了规定。这些接口以及规定统称为Servlet规范。Servlet规范把能够发布和运行Java Web应用的Web服务器称为Servlet容器（如Tomcat）。

**servlet在应用开发中的作用**

Web应用通过实现Servlet接口，在Servlet的实现类中添加业务逻辑代码。这些业务代码可以被Web服务器（如tomcat）动态加载并执行，从而完成客户的请求。Tomcat负责接收和解析客户的请求，并把客户的请求发送给相应的Servlet，Servlet执行完业务逻辑后，返回值再交由Tomcat返回给客户端。可以认为Servlet是用来扩展Web服务器功能的。



**架构**

![1655219228314](/1655219228314.png)



**任务**

- 读取客户端（浏览器）发送的显式的数据。这包括网页上的 HTML 表单，或者也可以是来自 applet 或自定义的 HTTP 客户端程序的表单。
- 读取客户端（浏览器）发送的隐式的 HTTP 请求数据。这包括 cookies、媒体类型和浏览器能理解的压缩格式等等。
- 处理数据并生成结果。这个过程可能需要访问数据库，执行 RMI 或 CORBA 调用，调用 Web 服务，或者直接计算得出对应的响应。
- 发送显式的数据（即文档）到客户端（浏览器）。该文档的格式可以是多种多样的，包括文本文件（HTML 或 XML）、二进制文件（GIF 图像）、Excel 等。
- 发送隐式的 HTTP 响应到客户端（浏览器）。这包括告诉浏览器或其他客户端被返回的文档类型（例如 HTML），设置 cookies 和缓存参数，以及其他类似的任务。





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

* Tomcat负责维护Servlet实例的生命周期
* Servlet对象只会创建一个，所有请求都是基于这个对象进行响应；
* 第一次请求时tomcat才会实例化和初始化，然后再进行服务。这样可以提高系统的启动速度，但会影响第一次请求的响应速度。（如果需要提高响应速度，应该设置Servlet的响应请求时机：通过`<servlet>`标签中的`<load-on-startup>`设置servlet启动的先后顺序）
* Servlet在容器中是单例的，线程不安全的；因此尽量不要在servlet中定义成员变量，也不要去修改成员变量的值，不要做逻辑判断。









##### 部署

**软件**

* **Java开发工具包**

  Servlet是由Java编写的，因此需要使用 Java 编译器 javac 编译 Servlet。

  安装JDK后，设置 PATH 和 JAVA_HOME 环境变量指向包含 java 和 javac 的目录：

  ```
  set PATH=C:\jdk1.5.0_20\bin;%PATH%
  set JAVA_HOME=C:\jdk1.5.0_20
  ```

* **Web应用服务器Tomcat**

  Apache Tomcat 是一款 Java Servlet 和 JavaServer Pages 技术的开源软件实现，可以作为测试 Servlet 的独立服务器，而且可以集成到 Apache Web 应用服务器。



**项目管理**

* **创建Web工程**（Web有小蓝点表示创建成功）

  > 使用Maven模板创建：选择maven-archetype-webapp模板
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



##### 匹配过程

当一个请求发送到servlet容器的时候，容器先会将请求的url去掉当前应用上下文的路径作为servlet的映射url，比如访问的是 `http://localhost/admin/login.jsp`，应用上下文是test，容器会将`http://localhost/admin`去掉， 剩下的`/login.jsp`部分拿来做servlet的映射匹配。

**url-pattern书写规则：**

只有以下几种情况：

* 以`/` 开头
* 以`*` 开头
* 以`/` 开始，以`*` 结尾

如该情况无法识别：`/*.do`  *只能用于结尾，或者开头，不能放在中间，正确写法（\*.do）



**具体匹配顺序和规则是：**

* step1：精确路径匹配。例子：比如servletA 的url-pattern为 `/test`，servletB的url-pattern为 `/*` ，这个时候，如果我访问的url为`http://localhost/test`，这个时候容器就会先进行精确路径匹配，发现/test正好被servletA精确匹配，那么就去调用servletA，也不会去理会其他的 servlet了。

* step2 : 最长路径匹配。例子：servletA的url-pattern为`/test/`，而servletB的url-pattern为`/test/a/`，此时访问`http://localhost/test/a`时，容器会选择路径最长的servlet来匹配，也就是这里的servletB。
* step3 : 扩展匹配，如果url最后一段包含扩展(.xxx)，容器将会根据扩展选择合适的servlet。
  .action匹配全部action结尾的请求
  .css 匹配全部css结尾的请求
  容器会首先查找完全匹配，如果找不到，再查找目录匹配，如果也找不到，就查找扩展名匹配。

* step4:如果前面三条规则都没有找到一个servlet，容器会根据url选择对应的请求资源。如果应用定义了一个default servlet，则容器会将请求丢给default servlet。



##### 注解

在 Servlet 中，web.xml 扮演的角色十分的重要，它可以将所有的 Servlet 的配置集中进行管理，但是若项目中 Servlet 数量较多时，为了减少冗余，注解（Annotation）就是一种更好的选择。

在Servlet中如果设置了`@WebServlet`注解,当请求该Servlet时,服务器就会⾃动读取当中的信息,如果注解`@WebServlet("/category")`则表⽰该Servlet默认的请求路径为…/category,这⾥省略了urlPatterns属性名。

**语法：**

* 如果在@WebServlet中需要设置多个属性,必须给属性值加上属性名称,中间⽤逗号隔开,否则会报错。
* 若没有设置@WebServlet的name属性，默认值是Servlet的全类名（包+类）。

**注意事项：**

- 通过实现 Serlvet 接口或继承 GenericServlet 创建的 Servlet 类无法使用 @WebServlet 注解。
- 使用 @WebServlet 注解配置的 Servlet 类，不要在 web.xml 文件中再次配置该 Servlet 相关属性。若同时使用 web.xml 与 @WebServlet 配置同一 Servlet 类，则 web.xml 中`<servlet-name>`的值与注解中 name 取值不能相同，否则容器会忽略注解中的配置。



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

客户端第一次发送请求给服务器，服务器获取客户端的session，如果无法获取则创建新的session响应给客户端。

下一次客户端给服务器发送请求，会将sessionID一起发给服务器，服务器确定该次请求和上一次为同一个客户端。

**实现：**

Servlet 提供了 **HttpSession 接口**，该接口提供了一种跨多个页面请求或访问网站时识别用户以及存储有关用户信息的方式。

Servlet 容器使用这个接口来创建一个 HTTP 客户端和 HTTP 服务器之间的 session 会话。会话持续一个指定的时间段，跨多个连接或页面请求。

**常用API：**

`request.getSession([true])`	获取当前会话，如果没有则创新新会话

`request.getSession(false)` 	获取当前会话，如果不存在则返回null

`session.getId()`	获取session的id号

`session.isNew()`	判断当前session是否为新

`session.invalidate()`	使该session会话失效，并解除绑定到它上的任意对象

`setMaxInactiveInterval(int interval)`指定客户端的请求时间



**保存作用域；**

> 保存作用域只在一次会话范围内有效（仅作用于当前sessionid）

`session.setAttribute(String name, Object value)` 使用指定的名称key绑定一个对象value到该 session 会话

`session.getAttribute(String name)`	返回该session会话中具有指定名称key的Object对象，如果没有则返回null

`session.removeAttribute(String name)`	移除指定名称k的对象





##### **服务器请求转发**

转发的功能是将用户对当前JSP页面或servlet的请求转发给另一个JSP页面或servlet，并且可以将用户对当前JSP页面或servlet的请求传递给转发到JSP页面或servlet。

请求转发属于服务器行为。转发时request对象会被保存，也就是说被转发到的另外一个servlet或其他资源中的request对象，跟请求转发的request是同一个对象，转发后浏览器地址栏内容不变（依旧是转发前的请求地址）。

**过程：**

本质上是一次请求，对应一个request对象和一个response对象。客户端不清楚服务器内部进行了多少次转发。

![1655727032488](/1655727032488.png)

**语法：**

服务器内部转发：`request.getRequestDispatcher(url).forward(request,response);` url表示需要转发到的servlet的url。



##### **客户端重定向**

servlet重定向指的是一种由http协议规定的机制，重定向属于客户端行为。服务器在收到客户端请求后，会通知客户端浏览器重新向另外一个 URL 发送请求，这称为请求重定向。用户在请求一个servlet时，该servlet在处理完数据后可以使用重定向方法将用户重新定向到另一个JSP页面或servlet。

**过程：**

它本质上是两次 HTTP 请求，对应两个 request 对象和两个 response 对象。

（1）用户在浏览器中输入 URL，请求访问服务器端的 Web 资源。
（2）服务器端的 Web 资源返回一个状态码为 302 的响应信息，该响应的含义为：通知浏览器再次发送请求，访问另一个 Web 资源（在响应信息中提供了另一个资源的 URL）。
（3）当浏览器接收到响应后，立即自动访问另一个指定的 Web 资源。
（4）另一 Web 资源将请求处理完成后，由容器把响应信息返回给浏览器进行展示。

![1655619813581](/1655619813581.png)

**语法：**

客户端重定向：`response.sendRedirect("...");`



**区别**

转发和重定向都能实现页面的跳转，但是两者也存在以下区别。

| 区别                                  | 转发               | 重定向             |
| ------------------------------------- | ------------------ | ------------------ |
| 浏览器地址栏 URL 是否发生改变         | 否                 | 是                 |
| 是否支持跨域跳转                      | 否                 | 是                 |
| 请求与响应的次数                      | 一次请求和一次响应 | 两次请求和两次响应 |
| 是否共享 request 对象和 response 对象 | 是                 | 否                 |
| 是否能通过 request 域对象传递数据     | 是                 | 否                 |
| 速度                                  | 相对要快           | 相对要慢           |
| 行为类型                              | 服务器行为         | 客户端行为         |



### Thymeleaf

##### 基础

thymeleaf是springboot官方推荐的视图模板技术。其特点包括：

- 动静结合：Thymeleaf 在有网络和无网络的环境下皆可运行，即它可以让美工在浏览器查看页面的静态效果，也可以让程序员在服务器查看带数据的动态页面效果。这是由于它支持 html 原型，然后在 html 标签里增加额外的属性来达到模板+数据的展示方式。浏览器解释 html 时会忽略未定义的标签属性，所以 thymeleaf 的模板可以静态地运行；当有数据返回到页面时，Thymeleaf 标签会动态地替换掉静态内容，使页面动态显示。
- 开箱即用：它提供标准和spring标准两种方言，可以直接套用模板实现JSTL、 OGNL表达式效果，避免每天套模板、该jstl、改标签的困扰。同时开发人员也可以扩展和创建自定义的方言。
- 多方言支持：Thymeleaf 提供spring标准方言和一个与 SpringMVC 完美集成的可选模块，可以快速的实现表单绑定、属性编辑器、国际化等功能。
- 与SpringBoot完美整合，SpringBoot提供了Thymeleaf的默认配置，并且为Thymeleaf设置了视图解析器，我们可以像以前操作jsp一样来操作Thymeleaf。代码几乎没有任何区别，就是在模板语法上有区别。

##### 使用步骤

* 引入thymeleaf的jar包；

* 新建Servlet类ViewBaseServlet，作为视图解析器

  ```java
  public class ViewBaseServlet extends HttpServlet {
  
      private TemplateEngine templateEngine;
  
      @Override
      public void init() throws ServletException {
          // 1.获取ServletContext对象
          ServletContext servletContext = this.getServletContext();
          // 2.创建Thymeleaf解析器对象
          ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
          // 3.给解析器对象设置参数
          templateResolver.setTemplateMode(TemplateMode.HTML);// 设置模板模式，HTML是默认模式
          templateResolver.setPrefix(servletContext.getInitParameter("view-prefix"));// 设置前缀
          templateResolver.setSuffix(servletContext.getInitParameter("view-suffix"));// 设置后缀
          templateResolver.setCacheTTLMs(60000L);// 设置缓存过期时间（毫秒）
          templateResolver.setCacheable(true);// 设置是否缓存
          templateResolver.setCharacterEncoding("utf-8");        // 设置服务器端编码方式
          // 4.创建模板引擎对象
          templateEngine = new TemplateEngine();
          // 5.给模板引擎对象设置模板解析器
          templateEngine.setTemplateResolver(templateResolver);
      }
  
      protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
          // 1.设置响应体内容类型和字符集
          resp.setContentType("text/html;charset=UTF-8");
          // 2.创建WebContext对象
          WebContext webContext = new WebContext(req, resp, getServletContext());
          // 3.处理模板数据
          templateEngine.process(templateName, webContext, resp.getWriter());
      }
  }
  ```

* 在web.xml中进行配置servletContext参数

  > 为什么要放在WEB-INF目录下？
  >
  > 原因：WEB-INF目录不允许浏览器直接访问，所以我们的视图模板文件放在这个目录下，是一种保护。以免外界可以随意访问视图模板文件。
  >
  > 访问WEB-INF目录下的页面，都必须通过Servlet转发过来，简单说就是：不经过Servlet访问不了。
  >
  > 这样就方便我们在Servlet中检查当前用户是否有权限访问。

  > 放在WEB-INF目录下之后，重定向进不去怎么办？
  >
  > 重定向到Servlet，再通过Servlet转发到WEB-INF下。

  ``` xml
  <!-- 配置上下文参数,param-value中设置的前缀、后缀的值不是必须叫这个名字，可以根据实际情况和需求进行修改。 -->
  <context-param>
      <param-name>view-prefix</param-name><!--配置前缀-->
      <param-value>/</param-value>
  </context-param>
  <context-param>
      <param-name>view-suffix</param-name><!--配置后缀-->
      <param-value>.html</param-value>
  </context-param>
  ```

* 使需要使用的servlet继承视图解析器ViewBaseServlet

  使用视图解析器中processTemplate()渲染视图，

  `super.processTemplate("index",request,response);`（逻辑视图`index`=>物理视图`/index.html`）

  > 第一个参数templateName声明需要前往的视图名称，第二、三个参数传入请求体和响应体



##### th语法

Thymeleaf的主要作用是把model中的数据渲染到html中，因此其语法主要是如何解析model中的数据。

**说明：**

* 使用thymeleaf表达式首先需要引入th名称空间`<html lang="en" xmlns:th="www.thymeleaf.org">`

* 如果我们不经过SpringMVC，而是直接用浏览器打开编写的页面：在静态环境下,th指令不会被识别，但是也不会报错，而是显示标签的缺省默认值；

**具体使用**

**（1）表达式**

**${}**是thymeleaf的变量表达式，用于访问的是**容器上下文的变量**，比如域变量：request域，session域

**#{}**是thymeleaf中的消息表达式、或者是资源表达式
一般和 th:text一起使用多一点，#{}取出来的值取代了标签中的值，#{key}对应的value。如果标签中间已经有值，#{}取出来的值会覆盖标签中的值。如：

```html
<div th:text="#{user}"> </div>
```

**@{}**是thymeleaf中的超链接表达式，如：

```html
<a th:href="@{/user/login}"></a>
<a th:src="@{/user/login}"/>
```

***{}**是选择表达式，是选定的对象，不是整个环境的映射，如：

```html
<p>Name: <span th: text=" *{firstName}" >Sebastian</span>. </p>
<p>Surname: <span th: text=" *{lastName}" >Pepper</span>. </p>
```

**（2）字面量**

我们需要在指令中填写基本类型如：字符串、数值、布尔等，并不希望被Thymeleaf解析为变量，这个时候称为字面值。字符串用单引号引用，其他正常。

**（3）拼接**

字符串字面值需要用单引号，拼接起来非常麻烦，Thymeleaf对此进行了简化，使用一对|即可：

```html
<!--原先-->
<span th:text="'欢迎您:' + ${user.name} + '!'"></span> 
<!--简化-->
<span th:text="|欢迎您:${user.name}|"></span>
```

**（4）循环**

我们使用th:each指令来完成循环。假如有用户的集合：users在Context中，我们需要去除每个用户的数据:

```html
<tr th:each="user : ${users}">    
    <td th:text="${user.name}">Onions</td>    
    <td th:text="${user.age}">2.41</td>
</tr>
```

**（5）逻辑判断**

Thymeleaf中使用th:if 或者 th:unless ，两者的意思恰好相反。

```go
<span th:if="${user.age} > 24">老油条</span>
```

如果表达式的值为true，则标签会渲染到页面，否则不进行渲染。



**举例：**

```html
<tr th:if="${#lists.isEmpty(session.fruitList)}">
    <td colspan="5">对不起，库存为空!</td>
</tr>
<tr th:unless="${#lists.isEmpty(session.fruitList)}" th:each="fruit:${session.fruitList}">
    <td><a th:text="${fruit.fid}" th:href="@{edit.do}"></a></td>
    <td th:text="${fruit.fname}"></td>
    <td th:text="${fruit.price}"></td>
    <td th:text="${fruit.fcount}"></td>
</tr>
```

> th:if和th:unless对应判断的两种情况。
>
> th:each是thymeleaf的迭代器，a:${list}）a表示list中的每个元素
>
> th:text表示文本
>
> th:href表示超链接

  

##### 保存作用域

保存作用域有四种方式：page、request、session、application

* page是页面级别保存，已弃用
* request为一次请求响应范围有效
* session为一次会话范围有效（也就是要同一个客户端【浏览器】进行访问）
* application为一次应用程序有效



**request保存作用域：**

```java
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向request保存作用域保存数据
        request.setAttribute("uname","lili");
        //2.客户端重定向
        // response.sendRedirect("demo02");//demo02打印为null
        //3.服务器端转发
        request.getRequestDispatcher("demo02").forward(request,response);//demo02能打印uname的信息
    }
}
//另一个class
@WebServlet("/demo02")
public class Demo02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取request保存作用域保存的数据，key为uname
        Object unameObj = request.getAttribute("uname");
        System.out.println("unameObj = " + unameObj);
    }
}
```

> 重定向时，页面跳转，由于不是同一个请求（超出了作用域范围），因此demo02无法get到数据；
>
> 转发时，页面不变，由于内部使用同一个请求，因此demo02可以获取数据；
>
> 重新打开新的demo02页面或打开新的浏览器时，则无法获取数据。



**session保存作用域：**

```java
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向session保存作用域保存数据
        System.out.println(request.getSession().getId());
        request.getSession().setAttribute("uname","lili");
        //2.客户端重定向
        response.sendRedirect("demo04");
        //3.服务器端转发
        // request.getRequestDispatcher("demo04").forward(request,response);
    }
}
//另一个类
@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取session保存作用域保存的数据，key为uname
        System.out.println(request.getSession().getId());
        Object unameObj = request.getSession().getAttribute("uname");
        System.out.println("unameObj = " + unameObj);
    }
}
```

>重定向或转发时，单个或多个请求都在同一session内，因此可以获取数据
>
>同一个浏览器重新打开demo04页面，也是同一个session，可以获取数据
>
>更换浏览器无法获取数据，这是因为不同的浏览器使用不同的session



**application保存作用域：**

```java
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向application保存作用域保存数据
        //ServletContext : Servlet上下文
        ServletContext application = request.getServletContext();
        application.setAttribute("uname","lili");
        //2.客户端重定向
        response.sendRedirect("demo06");//demo06能获取uname信息
        //3.服务器端转发
        //request.getRequestDispatcher("demo04").forward(request,response);
    }
}
//另一个class
@WebServlet("/demo06")
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取application保存作用域保存的数据，key为uname
        ServletContext application = request.getServletContext() ;
        Object unameObj = application.getAttribute("uname");
        System.out.println("unameObj = " + unameObj);
    }
}
```

> 重定向或转发时，由于仍然在同一应用内，因此可以获取get的数据；
>
> 同一个浏览器重新打开demo06页面或更换浏览器，都在同一应用内，可以获取数据；





# 多线程

### 线程简介

进程（Process）：进程是程序执行的一次过程，是一个**动态**的概念。是系统资源分配的基本单位，

线程（Thread）：一个进程中可以包含若干个进程。线程是CPU运算调度的基本单位。

多线程：很多多线程是模拟出来的，真正的多线程是指有多个cpu（多核，如服务器）。模拟出来的多线程是在一个cpu的情况下，在同一时间点，cpu只能执行一个代码，只是切换的很快有同时执行的错觉。



**多线程和普通方法的区别：**

普通方法：只有主线程一条执行路径；

多线程：多条执行路径，主线程和子线程**并行交替**执行；



**核心概念：**

- 线程是独立的执行路径；
- 在程序运行时，即使没有自己创造线程，后台也会有多个线程，如主线程，gc线程；
- main()称为主线程，为系统的入口，用于执行整个程序；
- 在进程中，如果开辟了很多个线程，线程的运行由调度器安排调度，调度器是与操作系统紧密相关的，不能人为干预；
- 对同一份资源操作时，会存在资源抢夺问题，需要加入并发控制；
- 线程会带来额外的开销，如cpu调度时间，并发控制开销；
- **每个线程只能在自己的工作内存交互，内存控制不当会导致数据不一致**



##### **多线程三要素**

**原子性：**

指的是一个或者多个操作，要么全部执行并且在执行的过程中不被其他操作打断，要么就全部不执行。原子性是数据一致性的保障。

解决：synchronized关键字

**可见性：**

指多个线程操作一个共享变量时，其中一个线程对变量进行修改后，其他线程可以立即看到修改的结果。(线程间的通信实现)

解决：synchronized关键字、volatile关键字

**有序性：**

在执行程序时，为了提供性能，处理器和编译器常常会对指令进行重排序，但是不能随意重排序。

需要满足以下两个条件：（1）在单线程环境下不能改变程序运行的结果；（2）存在数据依赖关系的不允许重排序



### 线程创建

##### **lambda表达式**

Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。Lambda 允许把函数作为一个方法的参数。

**具体用法：**对于函数式接口，可以通过lambda表达式创建该接口的对象。（**函数式接口：**任何接口只包含**一个**抽象方法）

e.g.:`new Thread(()->System.out.println("学习")).start();`

**语法：**

（1）(params)->expression

（2）(params)->{statements;}



**说明：**

- lambda表达式用接口类型的对象进行接收，表示对接口中的唯一方法进行实现（如果需要参数需要添加参数）
- 可选类型声明：不需要声明参数类型（要去除就都去除），编译器可以统一识别参数值。
- 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
- 可选的大括号：如果主体只包含了一个语句，就不需要使用大括号。
- 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定表达式返回了一个数值。



**优势：**

- 避免匿名内部类定义过多
- 简化代码，只留下核心逻辑



##### **三种方式**

（1）继承Thread类【声明为Thread的子类，并重写run类】（重点）

（2）实现Runnable接口【重写run类的T】（重点）

（3）实现Callable接口（了解）



**方式一、继承Thread类**

**过程：**

- 自定义线程继承Thread类；
- 重写run方法，编写线程执行体；
- 创建线程对象，调用start()方法启动线程。（注意：调用start方法会自动调用重写的run方法）



demo:

```
public class TestThread1 extends Thread{
    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("Thread线程"+i);
        }
    }

    public static void main(String[] args) {
        //主线程
        //线程开启不一定立即执行，由CPU调度执行；
        //多线程和主线程并行交替执行
        TestThread1 testThread1 = new TestThread1();//创建线程对象
        testThread1.start();//调用start方法开启线程

        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程"+i);
        }

    }
}
```



**方式二、实现Runnable接口**

**过程：**

- 自定义线程实现Runnable接口；
- 重写run方法，编写线程执行体；
- 创建线程对象，创建Thread对象并作为参数传递，调用start()方法启动线程，可以传入同一个线程。（注意：调用start方法会自动调用重写的run方法）



demo:

```
public class TestThread4 implements Runnable{
    private int ticketNum = 10;

    public void run() {
        while(true){
            if(ticketNum<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"张票");
        }
    }

    public static void main(String[] args) {
        TestThread4 tt = new TestThread4();
        new Thread(tt,"a").start();//Thread第二个参数可以为线程取名
        new Thread(tt,"b").start();
    }
}
```

**说明：**

- Thread第二个参数可以为线程取名
- `Thread.currentThread()`取得当前的线程，`Thread.currentThread().getName()`取得当前的线程名



**方式三、实现Callable接口**

**过程：**

- 实现Callable接口，需要返回值类型
- 需要重写call方法并抛出异常
- 创建目标对象：`TestCallable t1 = new TestCallable();`
- 创建执行服务：`ExecutorService ser = Executors.newFixedThreadPool(1);`
- 提交执行：`Future<Boolean> result1 = ser.submit(t1);`
- 获取结果：`boolean r1 = result1.get();`
- 关闭服务：`ser.shutdown();`



demo:

```
public class TestCallable implements Callable<Boolean> {

    //加载图片线程的执行体
    @Override
    public Boolean call() {
        System.out.println("下载完成");
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable();
        TestCallable t2 = new TestCallable();
        TestCallable t3 = new TestCallable();
        
        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);//创建线程池
        //提交执行
        Future<Boolean> result1 = ser.submit(t1);
        Future<Boolean> result2 = ser.submit(t2);
        Future<Boolean> result3 = ser.submit(t3);
        //获取结果(返回值）
        boolean r1 = result1.get();
        boolean r2 = result2.get();
        boolean r3 = result3.get();

        //关闭服务
        ser.shutdown();
    }
}
```



**三种方式比较**

**（1）继承Thread类**

- 子类继承Thread类就可以具备多线程能力
- 启动线程：`子类对象.start()`
- 不建议使用，因为OOP是单继承，同一个对象不能被多个线程使用



**（2）实现Runnable接口**

- 子类实现Runnable接口并重写run方法可以具备多线程能力
- 启动线程：`Thread对象(实现类对象).start()`
- 建议使用，灵活方便，同一个对象可以被多个线程使用（多代理）



**（3）实现Callable接口**

- 子类实现Runnable接口并重写call方法可以具备多线程能力
- 启动线程：创建执行服务+提交执行+获取结果
- Runnable 没有返回值，Callable 有返回值，Callable 可以看作是 Runnable 的补充



### 守护线程

线程分为用户线程和守护线程。

虚拟机必须确保用户线程执行完毕，不用等待守护线程执行完毕。

守护线程包括比如后台记录操作日志、监控内存、垃圾回收机制。

语法：`thread.setDaemon(true);`	设置某个线程为守护线程（默认为false，表示用户线程）



### 代理

##### **静态代理**

真实对象和代理对象实现同一个接口，代理对象要代理真实角色（设置真实角色的属性并传入）。

**优势：**之所以实现相同接口，是为了尽可能保证代理对象的内部结构和目标对象一致，这样我们对代理对象的操作最终都可以转移到目标对象身上，代理对象只需专注于增强代码的编写。

demo:

```
public class TestSP {
    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.HappyMarry();
    }
}

interface Marry{
    void HappyMarry();
}

//真实角色结婚
class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("我要结婚了，很开心！");
    }
}

//代理角色，帮助结婚
class WeddingCompany implements Marry{
    private Marry target;


    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void after() {
        System.out.println("结婚之后收尾款");
    }

    public void before(){
        System.out.println("结婚前布置现场");
    }
}
```



##### **动态代理**

Jdk提供了**invocationHandler接口**和**Proxy类**。

动态代理和静态代理的区别在于静态代理我们需要手动的去实现目标对象的代理类，而动态代理可以在运行期间动态的生成代理类。

**实现流程：**

- 实现 InvocationHandler 接口，重写 invoke 方法，在invoke方法中完成代理的功能
- 创建 JDK 动态代理类，创建 JDK 动态代理类实例同样也是使用反射包中的 java.lang.reflect.Proxy 类进行创建。通过调用`Proxy.newProxyInstance`静态方法进行创建。
- 实现动态代理：通过向下转型，调用实现类的getProxy方法。



### 线程状态

java中用`Thread.state`表示线程当前的状态。

线程状态包括：创建状态(New)、就绪状态(Ready)、运行状态(Runnable)、阻塞状态(Blocked)、死亡状态(Terminated)



**过程：**

- 当线程对象被创建时，线程进入**创建状态**(New)
- 当调用start方法，线程立即进入**就绪状态**，等待调度执行(Ready)
- 当调用sleep、wait或同步锁时，线程进入**阻塞状态**(Blocked)；阻塞解除后，重新进入就绪状态等待cpu调度执行；
- cpu对线程进行调度执行，线程进入**运行状态**(Runnable)；
- 线程中断或结束，线程进入**死亡状态**(Terminated)，处于终止态的进程不再被调度执行



demo:

```
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{//创建进程
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);//阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("//////");
        });
        Thread.State state = thread.getState();//(New)
        System.out.println(state);
        thread.start();//启动线程,进入预备状态
        state = thread.getState();//(Runnable)
        System.out.println(state);
        while(state != Thread.State.TERMINATED){//当阻塞状态结束，终止进程(TERMINATED)
            Thread.sleep(100);
            state = thread.getState();//(TIMED_WAITING)
            System.out.println(state);
        }
    }
}
```





### 线程方法

**停止线程**

废弃：stop()、destroy()

一般使用一个标志位作为终止变量：

```
public class TestStop implements Runnable{
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("run Thread"+i++);
        }
    }

    public void stop(){
        flag = false;
    }

    public static void main(String[] args) throws InterruptedException {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        Thread.sleep(10);//防止分线程未执行主线程就将flag置false
        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+i);
            if(i==900){
                testStop.stop();
                System.out.println("该线程停止");
            }
        }
    }
}
```



**线程休眠**

**语法：**`Thread.sleep(time)`

**说明：**

- sleep(time)指定当前线程组成的毫秒数；
- sleep使用时需要抛出相关异常（InterruptedException)
- sleep时间到达后线程进入就绪状态；
- sleep可以模拟网络延时，倒计时等=>放大问题的发生性；
- **每一个对象都有一把锁，sleep不会释放锁**



**线程礼让**

**语法：**`Thread.yield()`

**作用：**

（1）礼让线程，让当前执行的线程暂停，但不阻塞；

（2）此时线程会从运行状态转为就绪状态；

（3）**让cpu重新调度，礼让不一定成功，看CPU分配情况。**



demo:

```
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield,"a").start();
        new Thread(myYield,"b").start();
    }
}

class MyYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"线程结束执行");
    }
}
```



**线程合并**

**语法：**`thread.join()`	注意，此处的thread是进程对象

**作用：**用于合并线程，当此线程执行完成后，再执行其他线程，其他线程阻塞(插队)

demo:

```
public class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("vip-"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        //主线程
        for (int i = 0; i < 500; i++) {
            if(i==200){
                thread.join();
            }
            System.out.println("main-"+i);
        }
    }
}
```



**设置优先级**

**语法：**

`Thread.currentThread().getPriority()`	显示当前进程的优先级

`thread.setPriority(PRIORITY)`	设置优先级







### 线程安全

方式：线程同步

场景：并发（同一个对象被多个线程同时操作）

线程同步是一种等待机制，多个需要同时访问此对象的线程进入**这个对象的等待池**形成队列，等先前的线程使用完毕下一个线程再使用。

**总结：**

- 方法一：使用安全类。
- 方法二：使用自动锁 synchronized。
- 方法三：使用手动锁 Lock。



**队列和锁**

每个对象都拥有一把锁。

由于同一进程的多个线程共享同一块存储空间，在带来方便的同时也会带来访问冲突问题。为了保证数据在方法中被访问时的正确性，在访问时需要加入锁机制 (synchronized)，当一个线程获得对象的排它锁，独占资源，其他线程必须等待，使用后释放锁即可。

**主要问题：**

- 一个线程持有锁会导致其他所有需要此锁的线程挂起，降低效率；
- 在多线程竞争下，加锁、释放锁会导致较多的上下文切换和调度延时，引起性能问题；
- 如果一个优先级高的线程等待一个优先级低的线程释放锁，会导致优先级倒置，引起性能问题；



##### synchronized同步

**同步方法**

**语法：**`public synchronized void method(args){}`

**说明：**synchronized方法控制对**”对象“**的访问，每个对象对应一把锁，每个synchronized方法都必须获得调用该方法的对象的锁才能执行，否则线程阻塞，方法一旦执行就独占该锁，直到该方法返回才释放锁，后面被阻塞的线程才能获得该锁继续执行。



**同步块**

**语法：**`synchronized(obj){}`

obj称为同步监视器。

**说明：** 

- Obj可以是任何对象，但是推荐使用**共享资源**作为同步监视器
- 同步方法中无需指定同步监视器，因为同步方法的同步监视器就是this，或者是class.

**同步监视器的执行过程：**

- 第一个线程访问，锁定同步监视器，执行代码；
- 第二个线程访问，发现同步监视器被锁定，无法访问；
- 第一个线程访问完毕，解锁同步监视器；
- 第二个线程访问，锁定，重复；



##### **Lock锁**

lock锁通过显示定义同步锁对象实现同步。

所属包：`java.util.concurrent.locks.Lock`

Lock接口是控制多个线程对共享资源访问的工具。锁提供了对共享资源的独占空间，每次只能有一个对象对Lock对象加锁，线程开始访问共享资源前应该先获得Lock对象。

**ReentrantLock类**实现了Lock接口，拥有和synchronized相同的并发性和内存语义。在实现线程安全控制中，比较常用的是ReentranLock，可以显式的加锁、释放锁。



synchronized和Lock对比：

- Lock是显式锁（需要手动开关），synchronized是隐式锁，退出作用域会自动释放；
- Lock只有代码块锁，synchronized有代码块锁和方法锁两种
- 使用Lock锁，JVM花费较少的时间调度线程，性能更好，有更好的扩展性



##### **死锁**

多个线程各自占有一些共享资源，并且互相等待其他线程占有的资源才能运行，而导致两个或以上的线程都在等待对方释放资源，都停止执行的情形。

同一个同步块同时拥有**两个以上对象的锁**，就可能发生死锁问题。



产生的四个必要条件：

- 互斥条件：进程对所分配到的资源不允许其他进程进行访问，若其他进程访问该资源，只能等待，直至占有该资源的进程使用完成后释放该资源
- 请求和保持条件：进程获得一定的资源之后，又对其他资源发出请求，但是该资源可能被其他进程占有，此时请求阻塞，但又对自己获得的资源保持不放
- 不可剥夺条件：是指进程已获得的资源，在未完成使用之前，不可被剥夺，只能在使用完后自己释放
- 环路等待条件：是指进程发生死锁后，若干进程之间形成一种头尾相接的循环等待资源关系



**怎么预防死锁**

预防死锁的方式就是打破四个必要条件中的任意一个即可。

1）打破互斥条件：在系统里取消互斥。若资源不被一个进程独占使用，那么死锁是肯定不会发生的。但一般来说在所列的四个条件中，“互斥”条件是无法破坏的。因此，在死锁预防里主要是破坏其他几个必要条件，而不去涉及破坏“互斥”条件。

2）打破请求和保持条件：采用资源预先分配策略，即进程运行前申请全部资源，满足则运行，不然就等待。 每个进程提出新的资源申请前，必须先释放它先前所占有的资源。

3）打破不可剥夺条件：当进程占有某些资源后又进一步申请其他资源而无法满足，则该进程必须释放它原来占有的资源。

4）打破环路等待条件：实现资源有序分配策略，将系统的所有资源统一编号，所有进程只能采用按序号递增的形式申请资源。



### ThreadLocal

ThreadLocal全称线程局部变量，主要解决多线程中数据因并发产生不一致问题。ThreadLocal为每一个线程都提供了变量threadLocals的副本，使得每个线程都能够独立地访问这个变量，隔离了多个线程对数据的数据共享。耗费了内存，但大大减少了线程同步所带来性能消耗，也减少了线程并发控制的复杂度。



**底层原理**

每个线程都要自己的一个map，map是一个数组的数据结构存储数据，每个元素是一个Entry，entry的key是threadlocal的引用，也就是当前变量的副本，value就是set的值。

1、Thread类中有变量threadLocals，类型为ThreadLocal.ThreadLocalMap，保存着每个线程的私有数据。

2、ThreadLocalMap是ThreadLocal的内部类，每个数据用Entry保存，其中的Entry继承于WeakReference，用一个键值对存储，键为ThreadLocal的引用。如果是强引用，即把ThreadLocal设置为null，GC也不会回收，因为ThreadLocalMap对它有强引用。

3、ThreadLocal中的**set方法**的实现逻辑，先获取当前线程，取出当前线程的ThreadLocalMap，如果不存在就会创建一个ThreadLocalMap，如果存在就会把当前的threadlocal的引用作为键，传入的参数作为值存入map中。

4、ThreadLocal中**get方法**的实现逻辑，获取当前线程，取出当前线程的ThreadLocalMap，用当前的threadlocak作为key在ThreadLocalMap查找，如果存在不为空的Entry，就返回Entry中的value，否则就会执行初始化并返回默认的值。

5、ThreadLocal中**remove方法**的实现逻辑，还是先获取当前线程的ThreadLocalMap变量，如果存在就调用ThreadLocalMap的remove方法。ThreadLocalMap的存储就是数组的实行，因此需要确定元素的位置，找到Entry，把entry的键值对都设为null，最后也Entry也设置为null。



### 线程通信

**生产者消费者模式**

**作用：**

（1）通过平衡生产者的生产能力和消费者的消费能力来提升整个系统的运行效率，这是生产者消费者模型最重要的作用

（2）解耦，这是生产者消费者模型附带的作用，解耦意味着生产者和消费者之间的联系少，联系越少越可以独自发展而不需要收到相互的制约

**通信方法：**

`wait()`		表示线程一直等待，会释放锁（`wait(timeout)`指定等待秒数）

`notify()`	唤醒处于等待的线程

`notifyAll()`	唤醒同一个对象上所有调用wait()方法的线程（优先级高的优先调度）



**并发协作模型（管理法）**

- 生产者：负责生产数据的模块
- 消费者：负责处理数据的模块
- 缓冲区：消费者不能直接使用生产者的数据，需要有缓冲区。生产者将生产好的数据放入缓冲区，消费者从缓冲区拿出数据。



**信号灯法**

- 设置一个标志位flag，如果为true，就让线程等待、如果为false，就让该线程去通知另一个线程、把两个线程衔接起来，就像咱们的信号灯红灯停，绿灯行，通过这样一个判断方式，只要来判断什么时候等待，什么时候将他唤醒。



### 线程池

**背景：**我们经常创建和销毁、使用量很大的资源，比如并发情况下的线程。

**思路：**提前创建好多个线程，放入线程池中，使用时直接获取，使用后放回池中。可以避免频繁创建销毁、实现重复利用。

**属性：**

- corePoolSize：线程池大小
- maximumPoolSize：最大线程数
- keepAliveTime：线程没有任务时最多保持多长时间后会终止



**创建线程池的优点：**

- 降低资源消耗。通过重复利用已创建的线程，降低线程创建和销毁造成的消耗。
- 提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。
- 增加线程的可管理型。线程是稀缺资源，使用线程池可以进行统一分配，调优和监控。



**方法：**

`void executor(Runnable command)`	执行任务，没有返回值

`Future submit(Callable<T> task)`	执行任务，有返回值

`void shutdown()`	关闭线程池



# 反射

反射是指在运行状态中，对于任意一个类都能够知道这个类所有的属性和方法；并且对于任意一个对象，都能够调用它的任意一个方法；这种动态获取信息以及动态调用对象方法的功能称为反射机制。

### 反射机制

**需求：**通过外部文件配置，在不修改源码的情况下控制程序。满足开闭原则（不修改源码扩展功能）

**作用：**反射机制允许程序在执行期间借助于Reflection API取得任何类的内部信息（比如成员变量、构造器、成员方法、泛型、注解等），并能操作对象的属性及方法。反射在设计模式和框架底层都被广泛使用。具体而言，包括；

- 在运行时判断任意一个对象所属的类
- 在运行时构造任意一个类的对象
- 在运行时得到任意一个类所具有的成员变量和方法
- 在运行时调用任意一个对象的成员变量和方法
- 生成动态代理



**Java程序的三个阶段**

- 代码阶段/编译阶段：Java程序被javac编译为字节码文件（保存着一些变量、方法等）
- Class类阶段（类加载阶段）：通过类加载器将字节码文件加载（反射机制），在**堆**中生成Class类对象，用对象的方式保存成员变量、方法等
- Runtime运行阶段：生成对象后，该对象可以记录自己属于哪个Class类对象（互相关联）。通过Class对象可以调用对应的方法、属性。



**常用类：**java.lang.Class、java.lang.reflect.Method/Field/Constructor/Proxy



### 案例

通过配置文件使用反射机制的简单案例

demo:

```java
//使用IO文件流读取配置文件（使用Properties类）
Properties properties = new Properties();
properties.load(new FileInputStream("src\\main\\resources\\re.properties"));
String classfullpath = properties.get("classfullpath").toString();
Class cls = Class.forName(classfullpath);
Object o = cls.newInstance();//通过Class对象得到加载类的对象实例
Method method = cls.getMethod(methodName);//通过Class对象得到指定方法
method.invoke(o);//利用反射机制调用方法
Field nameField = cls.getField("age");//通过Class对象得到指定属性
System.out.println(nameField.get(o));//利用反射机制调用属性
Constructor constructor = cls.getConstructor();//通过Class对象得到无参构造器，括号中指定构造器参数类型
Constructor constructor2 = cls.getConstructor(String.class,int.class);//通过Class对象得到有参构造器，括号中指定构造器参数类型
```





### Class类分析

##### 主要特点

- Class类也是一个类，继承于Object
- Class类由系统自动创建，对于每个类内存中只有一份Class对象，因为类加载只进行一次。
- 每个对象知道自己属于哪个Class对象：`object.getClass()`
- 通过Class对象可以获得一个类的完整结构
- Class类对象存放在堆中
- 类的字节码二进制数据存放在方法区，也被称为类的元数据（包括方法代码、变量名、方法名、访问权限）



##### 常用方法

**基本信息**

`forName(String name)`	返回指定类名name的Class对象

`newInstance()`	调用无参构造函数，返回该Class对象的一个实例

`getName()`	返回该Class对象所表示的实体名称（类、接口、数组类、基本数据类型）

`getPackage()`	返回该Class对象所属的包信息

`getSuperClass()`	返回该Class对象的父类的全路径

`getClassLoader()`	返回该类的类加载器

**结构信息**

`getInterfaces()`	获取当前Class对象的所有接口（数组）

`getAnnotations()`	获取当前Class对象的注解信息

`getConstructor(ClassObject...)`	返回Class对象所有本类以及父类的public构造器

`getDeclaredConstructor(ClassObject...)`	返回Class对象所有本类以及父类的所有构造器

`getConstructors()`	返回Class对象所有本类以及父类的public构造器（数组）

`getDeclaredConstructors()`	返回Class对象所有本类以及父类的所有构造器（数组）

`getField(String name)`	返回Class对象的名为name的Field对象

`getFields()`	返回Class对象所有本类以及父类的public属性（数组）

`getDeclaredFields()`	返回Class对象所有本类以及父类的所有属性（数组）

`getMethod(String name, Class<?>… parameterTypes)`	返回Class对象的名为name的Method对象

`getDeclaredMethod(String name, Class<?>… parameterTypes)`	返回Class对象的有参方法

`getMethods()`	返回Class对象所有本类以及父类的public方法（数组）

`getDeclaredMethods()`	返回Class对象所有本类以及父类的所有方法（数组）

**Field类方法**

`getModifiers()`	以int形式返回修饰符（默认为0，public为1，private为2，protected为4，static为16【通过加法得到结果】）

`getType()`	以Class形式返回类型

`getName()`	返回属性名

**Method类方法**

`getModifiers()`	以int形式返回修饰符（默认为0，public为1，private为2，protected为4，static为16【通过加法得到结果】）

`getReturnType()`	获得当前方法的返回值类型

**Constructor类方法**

`getModifiers()`	以int形式返回修饰符（默认为0，public为1，private为2，protected为4，static为16【通过加法得到结果】）

`getParameterTypes()`	返回参数列表的数组

`getName()`	返回构造器名



**获取Class类对象的方式**

编译阶段：`Class.forName(path)`	已知类的全类名（包名+类名），可以通过Class类的静态方法forName()获取（多用于配置文件的读取）

加载阶段：`类.class`	已知具体的类，通过类的class获取（多用于参数传递，比如通过反射得到对应构造器对象）

运行阶段：`对象.getClass()`	已知某个类的实例，可以调用该实例的getClass()方法获取Class对象（获取的是该对象的运行类型）

类加载器：

```java
ClassLoader classLoader = c3.getClass().getClassLoader();
Class<?> c4 = classLoader.loadClass(path);
```

特殊情况：

- 基本数据类型(int/char/float...)可以通过`.class`方式获取对应的Class对象
- 基本数据类型对应的包装类(Integer/Character/Float)，可以通过`.TYPE`获取对应的Class对象
- 实际上在java底层基本数据类型和其对应包装类持有同一个Class对象（hashcode相同）



**Class对象包含类型**

外部类（成员、静态、局部、匿名）、接口、数组、enum、annotation、基本数据类型、void



### 类加载

##### **动态加载**

反射机制是java实现动态语言的关键，也就是通过反射实现类动态加载，

静态加载：**编译时**加载相应的类，如果没有这个类则报错（必须存在该类编译才能通过）

动态加载：**运行时**加载需要的类，如果运行时不用该类则不报错，降低依赖性  （不存在该类编译也通过）



##### **类加载时机**

1、使用new创建对象时发生

2、当子类被加载，父类发生

3、调用类中的静态变量时发生（以上均为静态加载）

4、通过反射发生（动态加载）



##### **类加载过程**

**类加载的三个阶段：**加载、连接（验证、准备、解析）、初始化

- 加载：将类的class文件读入内存，并为之创建一个java.lang.Class对象（由JVM控制）
- 连接：将类的二进制数据合并到 JRE中（由JVM控制）
  - 验证：对文件的安全性进行验证，确保 Class 文件的字节流中包含的信息是否符合当前虚拟机的要求
  - 准备：对静态变量分配内存并进行默认初始化
  - 解析：虚拟机将常量池中的符号引用替换为直接引用
- 初始化：执行静态代码块、静态变量的显式赋值（程序员控制）



**加载后内存分布情况：**

方法区：存放类的字节码二进制数据

堆区：存放类的Class对象（存在和方法区二进制数据的引用关系）



**加载阶段**

JVM会将将不同的数据源（包括class文件、jar包、甚至网络）转化为二进制字节流加载到内存中，并生成一个代表该类的Class对象。

**连接-验证阶段**

JVM需要确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机本身的安全。

验证范围：文件格式验证（是否以魔术oxcafebabe开头）、元数据验证、字节码验证、符号引用验证

**连接-准备阶段**

JVM会在该阶段对静态变量分配内存并进行默认初始化，这些变量所使用的内存都将在方法区进行区分。

注意：实例属性不会分配内存；针对final static修饰的常量，由于一旦赋值就不会变化，因此jvm底层按照显式赋值。

**连接-解析阶段**

JVM将常量池中的符号引用替换为直接引用。

**初始化阶段**

操控者：程序员

初始化阶段真正执行类中定义的Java程序代码。此阶段是执行`<clinit>()`方法的过程。

注意：

- `<clinit>()`方法是由编译器按语句在源文件中出现的顺序，依次自动收集类中所有**静态变量**的赋值动作和**静态代码块**中的语句，并进行合并。
- 虚拟机会保证一个类的`<clinit>()`方法在多线程环境中被正确的加锁、同步，如果多个线程同时初始化一个类，那么只会有一个线程执行`<clinit>()`方法，其他线程都需要阻塞等待，直到活动线程执行`<clinit>()`方法完毕。



### 反射用法

##### 创建对象

通过反射创建对象有若干种方式：

- 调用类中public修饰的无参构造器
- 调用类中的指定构造器（public和private）

**相关方法：**

- 使用Class类中的相关方法：

  `newInstance()`	调用类中的无参构造器，获取对应类的对象

  `getConstructor(Class...clazz)`	根据参数列表，获取对应public构造器的对象

  `getDeclaredConstructor(Class...clazz)`	根据参数列表，获取对应所有构造器的对象

- 使用Constructor类中的相关方法：

  `setAccessible(true)`	爆破（通过这种方式可以访问私有的构造器方法）

  `newInstance(Object...obj)`	调用构造器

**步骤：**

```java
//先获取User类的Class对象
Class<?> userClass = Class.forName("com.twhupup.basic.User");
//1.通过public的无参构造器创建实例
Object o = userClass.newInstance();//newInstance()方法会直接调用无参构造器
//2.通过public的有参构造器创建实例
Constructor<?> constructor = userClass.getConstructor(int.class);//先得到对应的构造器对象
Object o1 = constructor.newInstance(20);//传入实参，创建实例
//3.通过非public的有参构造器创建实例
Constructor<?> constructor1 = userClass.getDeclaredConstructor(int.class, String.class);//先得到对应私有构造器
constructor1.setAccessible(true);//爆破，使用反射可以访问private构造器
Object o2 = constructor1.newInstance(30, "ming");//传入实参，创建实例
```



##### 访问类属性

**相关方法：**

根据属性名获取公有的Field对象：`Field f = clazz.getField(属性名)`

根据属性名获取Field对象：`Field f = clazz.getDeclaredField(属性名)`

设置属性：`f.set(o,value)`

暴力破解有权限属性：`f.setAccessible(true)`

注意点：如果属性被static修饰，在set()和get()中可以不指定对象

**步骤：**

```java
//获取Student的Class类
Class<?> stuClass = Class.forName("com.twhupup.Student");
//创建对象
Object o = stuClass.newInstance();
//使用反射操作公有属性
Field age = stuClass.getField("age");
age.set(o,88);//通过反射操作属性
System.out.println(age.get(o));//通过反射获取属性值
//使用反射操作私有属性
Field name = stuClass.getDeclaredField("name");
name.setAccessible(true);
name.set(o,"abc");//如果name是static属性，则set的一个传入参数可以为null
System.out.println(name.get(o));//如果name是static属性，则get的传入参数可以为null
```



##### 访问类方法

**相关方法：**

根据方法名和参数列表获取公有方法的Method对象：`Method m = clazz.getMethod(方法名, XX.class)`

根据方法名和参数列表获取Method对象：`Method m = clazz.getDeclaredMethod(方法名, XX.class)`

暴力破解有权限方法：`m.setAccessible(true)`

调用方法并得到返回值：`Object returnValue = m.invoke(o,实参列表)`

注意点：

- 如果方法被static修饰，在invoke方法中，可以传入null；
- 在方法中，如果方法有返回值，统一返回Object作为编译类型，但运行类型和方法定义保持一致

**步骤：**

```java
//获取Student的Class类
Class<?> bossClass = Class.forName("com.twhupup.Boss");
//创建对象
Object o = bossClass.newInstance();
//根据Class对象获取公有Method对象（需要在getMethod后添加实参的Class对象）
Method hi = bossClass.getMethod("hi",String.class);
//根据Class对象获取所有（私有）Method对象（需要在getDeclaredMethod后添加实参的Class对象）
Method say = bossClass.getDeclaredMethod("say",String.class);
hi.invoke(o,"twh");//使用反射调用方法
say.setAccessible(true);//爆破
say.invoke(o,"sjy");//使用反射调用方法
```













------



# 垃圾回收机制

java  语言中一个显著的特点就是引入了java回收机制。它使得java程序员在编写程序的时候不在考虑内存管理。由于有个垃圾回收机制，java中的对象不再有“作用域”的概念，只有对象的引用才有“作用域”。垃圾回收可以有效的防止内存泄露，有效的使用空闲的内存。



### 内存泄漏和内存溢出

**内存泄漏：**是指程序在申请内存后，无法释放已申请的内存空间，大量内存泄漏堆积后的后果就是内存溢出。

**内存溢出：**指程序申请内存时，没有足够的内存供申请者使用（或者说，给了你一块存储int类型数据的存储空间，但是你却存储long类型的数据，那么结果就是内存不够用）此时就会报错OOM，即所谓的内存溢出。 

**内存泄露量大到一定程度会导致内存溢出。但是内存溢出不一定是内存泄露引起的。**



**内存泄漏的分类**

- 常发性内存泄漏。发生内存泄漏的代码会被多次执行到，每次被执行的时候都会导致一块内存泄漏。
- 偶发性内存泄漏。发生内存泄漏的代码只有在某些特定环境或操作过程下才会发生。常发性和偶发性是相对的。对于特定的环境，偶发性的也许就变成了常发性的。所以测试环境和测试方法对检测内存泄漏至关重要。
- 一次性内存泄漏。发生内存泄漏的代码只会被执行一次，或者由于算法上的缺陷，导致总会有一块仅且一块内存发生泄漏。比如，在类的构造函数中分配内存，在析构函数中却没有释放该内存，所以内存泄漏只会发生一次。
- 隐式内存泄漏。程序在运行过程中不停的分配内存，但是直到结束的时候才释放内存。严格的说这里并没有发生内存泄漏，因为最终程序释放了所有申请的内存。但是对于一个服务器程序，需要运行几天，几周甚至几个月，不及时释放内存也可能导致最终耗尽系统的所有内存。所以，我们称这类内存泄漏为隐式内存泄漏



**内存溢出原因** 

- 内存中加载的数据量过于庞大，如一次从数据库取出过多数据； 
- 集合类中有对对象的引用，使用完后未清空，使得JVM不能回收； 
- 代码中存在死循环或循环产生过多重复的对象实体； 
- 使用的第三方软件中的BUG； 
- 启动参数内存值设定的过小



### 垃圾回收策略

使用**分代**的垃圾回收策略。

分代的原因：**不同的对象的生命周期是不一样的。不同生命周期的对象可以采取不同的回收算法，以便提高回收效率。**



**年轻代（Young Generation）**

- 所有新生成的对象首先都是放在年轻代的。年轻代的目标就是尽可能快速的收集掉那些生命周期短的对象。
- 新生代内存按照8:1:1的比例分为一个eden区和两个survivor(survivor0,survivor1)区。大部分对象在Eden区中生成。回收时先将eden区存活对象复制到一个survivor0区，然后清空eden区，当这个survivor0区也存放满了时，则将eden区和survivor0区存活对象复制到另一个survivor1区，然后清空eden和这个survivor0区，此时survivor0区是空的，然后将survivor0区和survivor1区交换，即保持survivor1区为空， 如此往复。
- 当survivor1区不足以存放 eden和survivor0的存活对象时，就将存活对象直接存放到老年代。若是老年代也满了就会触发一次Full GC，也就是新生代、老年代都进行回收
- 新生代发生的GC也叫做**Minor GC**，MinorGC发生频率比较高(不一定等Eden区满了才触发)



**年老代（Old Generation）**

- 在年轻代中经历了N次垃圾回收后仍然存活的对象，就会被放到年老代中。因此，可以认为年老代中存放的都是一些生命周期较长的对象。
- 内存比新生代也大很多(大概比例是1:2)，当老年代内存满时触发**Major GC**即**Full GC**，Full GC发生频率比较低，老年代对象存活时间比较长，存活率标记高。



**持久代（Permanent Generation）**

- 用于存放静态文件，如Java类、方法等。持久代对垃圾回收没有显著影响，但是有些应用可能动态生成或者调用一些class，例如Hibernate 等，在这种时候需要设置一个比较大的持久代空间来存放这些运行过程中新增的类。（在jdk新版本中，已经没有了永久代这个区域）



### 收集器

新生代收集器使用的收集器：Serial、PraNew、Parallel Scavenge

老年代收集器使用的收集器：Serial Old、Parallel Old、CMS



Serial收集器（复制算法)：新生代单线程收集器，标记和清理都是单线程，优点是简单高效。

Serial Old收集器(标记-整理算法)：老年代单线程收集器，Serial收集器的老年代版本。

ParNew收集器(停止-复制算法)　：新生代收集器，可以认为是Serial收集器的多线程版本，在多核CPU环境下有着比Serial更好的表现。

Parallel Scavenge收集器(停止-复制算法)：并行收集器，追求高吞吐量，高效利用CPU。吞吐量一般为99%， 吞吐量= 用户线程时间/(用户线程时间+GC线程时间)。适合后台应用等对交互相应要求不高的场景。

Parallel Old收集器(停止-复制算法)：Parallel Scavenge收集器的老年代版本，并行收集器，吞吐量优先。

CMS(Concurrent Mark Sweep)收集器（标记-清理算法）：高并发、低停顿，追求最短GC回收停顿时间，cpu占用比较高，响应时间快，停顿时间短，多核cpu 追求高响应时间的选择

### GC的执行机制

GC有两种类型：Scavenge GC和Full GC。

**Scavenge GC**

一般情况下，当新对象生成，并且在Eden申请空间失败时，就会触发Scavenge GC，对Eden区域进行GC，清除非存活对象，并且把尚且存活的对象移动到Survivor区。然后整理Survivor的两个区。这种方式的GC是对年轻代的Eden区进行，不会影响到年老代。因为大部分对象都是从Eden区开始的，同时Eden区不会分配的很大，所以Eden区的GC会频繁进行。因而，一般在这里需要使用速度快、效率高的算法，使Eden去能尽快空闲出来。

**Full GC**

对整个堆进行整理，包括Young、Tenured和Perm。Full GC因为需要对整个堆进行回收，所以比Scavenge GC要慢，因此应该尽可能减少Full GC的次数。在对JVM调优的过程中，很大一部分工作就是对于FullGC的调节。有如下原因可能导致Full GC：

1.年老代（Tenured）被写满

2.持久代（Perm）被写满

3.System.gc()被显示调用

4.上一次GC之后Heap的各域分配策略动态变化

















