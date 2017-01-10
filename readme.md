http://blog.csdn.net/jiangwei0910410003/article/details/26447539

#### 1. 重定向和转发
转发：JSP容器将使用一个内部的方法来调用目标页面，新的页面继续处理同一个请求，而浏览器将不会知道这个过程

重定向：重定向方式的含义是第一个页面通知浏览器发送一个新的页面请求。因为，当你使用重定向时，浏览器中所显示的URL会变成新页面的URL, 而当使用转发时，该URL会保持不变

重定向的速度比转发慢，因为浏览器还得发出一个新的请求。同时，由于重定向方式产生了一个新的请求，所以经过一次重 定向后，request内的对象将无法使用。

**方式对比**

ActionForward的参数除了在struts-config.xml和页面中设置外，还可以通过在Action类中添加参数，或重新在Action中创建一个ActionForward。

```java
public class RedirectAction extends DispatchAction{

    public ActionForward redirectFalse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("city", "hefei");
        return mapping.findForward("redirectFalse");
    }

    public ActionForward redirectTrue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("city", "hefei");
        return mapping.findForward("redirectTrue");
    }
}
```

```xml
<action path="/redirect" type="com.ebiz.webapp.web.action.RedirectAction" name="lazyForm" validate="false"
        parameter="method" scope="request">
    <forward name="redirectFalse" path="/views/forward/redirect-false.jsp"></forward>
    <forward name="redirectTrue" path="/views/forward/redirect-true.jsp" redirect="true"></forward>
</action>
```

**redirect false**

request url: http://localhost:8083/redirect.do?method=redirectFalse

output: redirect false. hefei

**redirect true**

request url: http://localhost:8083/redirect.do?method=redirectTrue

redirect url: http://localhost:8083/views/forward/redirect-true.jsp

output: redirect true.

>   1.url差异
>
>   2.request范围内的数据共享差异

#### 2. 动态表单DynaActionForm

它让程序员不需要书写formbean，只需要在配置文件中配置即可

#### 3. DispatchAction
派发Action的使用：实现多个方法会有多个Action这样太复杂了，可以使用DispatchAction

#### 4. MappingDispatchAction
使用DispatchAction转发，每次使用的是同一个Action，这样很容易错误，为了解决这个问题我们使用MappingDispatchAction

#### 5. 占位符巧用

#### 6. unspecified
DispatchAction中unspecified方法，Struts找不到在parameter配置的参数对应的值时，Struts会默认执行重写的该方法。
```xml
<action path="/student" type="com.ebiz.webapp.web.action.StudentAction"
        name="dynStudentForm" parameter="method">
    <forward name="addSuccess" path="/student/addSuccess.jsp"></forward>
    <forward name="addFail" path="/student/addFail.jsp"></forward>
</action>
```
```java
public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return this.addStudent(mapping, form, request, response);
}

public ActionForward addStudent(ActionMapping arg0, ActionForm form,
                                HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    PrintWriter out=arg3.getWriter();
    out.println("addStudent");
    return null;
}
```
http://localhost:8080/student.do?method=addStudent 直接进入方法`addStudent`
http://localhost:8080/student.do 会进入方法`unspecified`

#### 7. LazyValidatorForm (DynaBean)
不用再配置属性.比`DynaActionForm`再简化一步
http://www.xuebuyuan.com/2223011.html
```java
public class LazyLoginAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean loginForm = (DynaBean) form;
        String userName = loginForm.get("userName").toString();

        if ("admin".equals(loginForm.get("userName"))) {
            return mapping.findForward("loginSuccess");
        } else {
            return mapping.findForward("loginFail");
        }
    }
}
```
```xml
......
<form-bean name="lazyForm" type="org.apache.struts.validator.LazyValidatorForm"/>
......

<action path="/lazyLogin" type="com.ebiz.webapp.web.action.LazyLoginAction" name="lazyForm" validate="false">
    <forward name="loginSuccess" path="/loginSuccess.jsp"></forward>
    <forward name="loginFail" path="/loginFail.jsp"></forward>
</action>
```
> 注意配置`validate="false"`,如果未配置报错:
> java.lang.IllegalArgumentException: Resources cannot be null


#### 8. 指定首页
##### 第一种方式：
index.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>
<c:url var="index" value="/index.do" />
<c:redirect url="${index}" />
```
```xml
<action path="/index" type="com.ebiz.webapp.web.action.IndexAction" name="lazyForm" validate="false" parameter="method">
    <forward name="success" path="/login.jsp"></forward>
</action>
```
```java
public class IndexAction extends DispatchAction{

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.index(mapping, form, request, response);
    }

    public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }
}
```
url:http://localhost:8083/index.do

##### 第二种方式
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>
<logic-el:forward name="index"/>
```
```xml
<global-forwards>
    <forward name="index" path="/index.do" redirect="true"></forward>
</global-forwards>

<action path="/index" type="com.ebiz.webapp.web.action.IndexAction" name="lazyForm" validate="false" parameter="method">
    <forward name="success" path="/login.jsp"></forward>
</action>
```
其他一样

#### 9. attribute

1）应用前提，attribute只有在设置了name后才有意义。
2）attribute可以实现对象的重用，即如果设置了attribute属性，在创建actionform是，会先去查找相应的scope中是否有此对象，如果有，则重用，否则创建新的对象。
3）当你将创建的acitonForm保存到相应的scope中时，你想用一个更有意义的名字来访问它时，它就有意义了。

#### 10.配置文件

##### 1.  配置文件只能放在WEB-INF/ 目录下，如WEB-INF/config/xx.xml 或者 WEB-INF/xx.xml

##### 2. 约定

##### `struts-config-other.xml`中配置的地址请求必须增加other/,如http://localhost:8083/other/student.do?method=addStudent

```xml
<servlet>
    <servlet-name>action</servlet-name>
    <servlet-class>org.apache.struts.action.ActionServlet</servlet-class>
    <init-param>
        <param-name>config</param-name>
        <param-value>/WEB-INF/struts-config.xml</param-value>
    </init-param>
    <init-param>
        <param-name>config/other</param-name>
        <param-value>/WEB-INF/struts-config-other.xml</param-value>
    </init-param>
    <load-on-startup>0</load-on-startup>
</servlet>
```

##### 3. 路径约定，`path="/success.jsp"`实质是`/webapp/other/success.jsp`，必须创建一个other文件夹在webapp下

```xml
<action path="/student" type="com.ebiz.webapp.web.action.OtherAction" name="lazyForm" validate="false"
        parameter="method">
    <forward name="success" path="/success.jsp"></forward>
</action>
```

