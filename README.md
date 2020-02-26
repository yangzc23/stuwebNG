### 项目介绍
所有功能使用Ajax技术，浏览器发送Ajax请求给后台，后台返回json格式的数据，JS回调函数对返回的JSON数据进行处理。

特点：**前端代码和后台代码彻底分离**

使用到的框架主要包括：**JDK + servlet + sqljdbc4 + jQuery + Bootstrap**

数据库：**SQL Server**

服务器软件：**tomcat**

开发工具：**eclipse + maven**

功能点如下：

**1）添加学生（已完成）**

**2）学生列表（已完成）**

3）列表分页显示（未完成）

4）学生详情（未完成）

**5）修改学生（已完成）**

**6）删除学生（已完成）**

7）搜索学生（未完成）

8）后台出现异常界面能够给出简单清晰的错误提示（未完成）

9）表单校验的JS功能（未完成）

![image.png](https://upload-images.jianshu.io/upload_images/10027900-736d126c6bfca41c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 思路
1）HttpServlet的作用：对http请求进行处理（service方法）

2）HttpServletRequest表示请求（获取请求里面参数的内容）

3）HttpServletResponse表示响应（发送响应数据给浏览器）

![image.png](https://upload-images.jianshu.io/upload_images/10027900-485cbc2085f643e5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/10027900-eef18708d0bc2d7e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/10027900-4e61a4569973927d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/10027900-699c2905e14b4882.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### 创建学生表
```
CREATE TABLE STUDENT(
SNO NUMERIC(4) PRIMARY KEY IDENTITY(1001,1),	--学号
SNAME VARCHAR(20),	--姓名
GENDER CHAR(2),	--性别
BIRTH DATE,	--生日
PHOTO_URL VARCHAR(50)	--照片路径
);
```
### 遇到的问题
**描述：**

访问网站首页报404错误

![image.png](https://upload-images.jianshu.io/upload_images/10027900-528d5619ae7fe8a3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**分析：**

web.xml中设置metadata-complete="true"，会在启动时不扫描注解（annotation）。如果不扫描注解的话，用注解进行的配置就无法生效，例如：@WebServlet

**解决办法：**

设置metadata-complete="false"

**微信扫一扫关注公众号**

![image.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy8xMDAyNzkwMC0yMzM2ODlhNjQ1YTM2YTEyLnBuZw?x-oss-process=image/format,png#pic_center)

**点击链接加入群聊**

[https://jq.qq.com/?_wv=1027&k=5eVEhfN](https://jq.qq.com/?_wv=1027&k=5eVEhfN)

软件测试学习交流QQ群号：**511619105**

**软件测试学习资料**

[《自动化测试教程》](https://item.taobao.com/item.htm?id=601801208861)
