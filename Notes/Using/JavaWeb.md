---
typora-copy-images-to: assets
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



---



# 前端知识

**前端目前主要使用以下三种语言，被称为前端三剑客：**

- HTML：超文本标记语言，决定页面上显示什么内容
- CSS：页面上的内容显示的风格（决定页面的美观程度）
- JavaScript：页面特效



### HTML

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




### CSS

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



### JavaScript

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





# Tomcat

Tomcat是一个Web服务器，同时也是一个Servet容器。

















