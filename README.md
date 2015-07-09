tiny-tomcat
=======

>A tiny http server container refer to tomcat.

## 关于

`tiny-tomcat`是为了学习j2ee服务器规范而建立的学习项目。我将努力尝试从使用功能的角度出发，同时参考J2ee规范、Tomcat源码、Http规范以及其他网上资源，从零开始一步一步增加功能。

## 功能

1. 获取静态资源。
2. 支持简单的servlet。



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

# 第二部分：一个简单的servlet服务器

功能：支持简单的servlet

    git checkout step_2_simple_servlet_web_server

一个servlet容器的作用是管理servlet并控制其生命周期。一般的，servlet的生命周期可以分为三步：
 - 加载servlet，如果是第一次加载，则调用其init方法。
 - 针对每一次请求，构造request、response，调用service方法。
 - 卸载servlet，调用destroy。

一个动态页面与一个静态页面的差异在于：同一个url，可以通过不同的参数、session、cookie等执行不同的逻辑，这里会包括操作数据、展现不同的页面等等。
这个功能实现大致是如果uri中带有/dynamic/XXX，就会触发动态页面的逻辑（根据XXX，mapping到对应的servlet）。

