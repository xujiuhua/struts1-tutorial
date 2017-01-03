<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="commons/taglibs.jsp"%>
<html>
<head>
    <title>${ctx}</title>
</head>
<body>
    <html-el:form action="${ctx}/lazyLogin.do" method="post">
        <!--这里的uerName和passWord要和bean中的一模一样-->
        username:
        <input type="text" name="userName">用户名</input></br>
        password:
        <input type="text" name="passWord">密码</input></br>
        <input type="submit" value="提交">
    </html-el:form>
</body>
</html>
