# SpringBoot

### 概念

**简介**

Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。

**微服务**

微服务：架构风格（服务微化）

一个应用应该是一组小型服务，可以通过HTTP的方式进行互通

单体应用：ALL IN ONE

微服务：每个功能元素最终都是一个可以独立替换和升级的软件单元



### 注解

#### @RequestBody

@RequestBody 注解是将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象。

**作用：**

* 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上； 
* 再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上。

**使用时机：**

A) GET、POST方式提时， 根据request header Content-Type的值来判断:

    application/x-www-form-urlencoded， 可选（即非必须，因为这种情况的数据@RequestParam, @ModelAttribute
也可以处理，当然@RequestBody也能处理）； 
    multipart/form-data, 不能处理（即使用@RequestBody不能处理这种格式的数据）； 
    其他格式， 必须（其他格式包括application/json, application/xml等。这些格式的数据，必须使用@RequestBody来处理）；
B) PUT方式提交时， 根据request header Content-Type的值来判断:

application/x-www-form-urlencoded， 必须；multipart/form-data, 不能处理；其他格式， 必须；
说明：request的body部分的数据编码格式由header部分的Content-Type指定；
