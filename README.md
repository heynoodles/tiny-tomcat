tiny-tomcat
=======

>A tiny http server container refer to tomcat.

## 关于

`tiny-tomcat`是为了学习j2ee服务器规范而建立的学习项目。我将努力尝试从使用功能的角度出发，同时参考J2ee规范、Tomcat源码、Http规范以及其他网上资源，从零开始一步一步增加功能。

## 功能

1. 获取静态资源。



# 第一部分：一个简单的服务器

功能：获取静态资源

	git checkout step_1_simple_web_server

我是这么思考的，一个java服务器无外乎是按照http规范来读写socket。
这个功能的实现大致是解析http的uri部分，通过uri映射到服务器上的一个静态文件，然后就可以写socket返回给客户端。

一个简单的服务器有三部分组成：
 - TinyTomcat：服务器本尊，不断接受请求并响应
 - Request：封装socket的inputStream, 读取http头，并按照http规范解析
 - Response：封装socket的outputStream，根据request解析得到的Uri，找到静态文件

测试代码：

```java
   TinyTomcat tinyTomcat = new TinyTomcat();
   tinyTomcat.await();
```

测试url：
	http://localhost:8080/index.html

# TBC...
