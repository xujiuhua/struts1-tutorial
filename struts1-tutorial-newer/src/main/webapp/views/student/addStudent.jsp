<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglibs.jsp"%>
<html>
<head>
    <title>${ctx}</title>
</head>
<body>
    <html-el:form action="${ctx}/dynAddStudent.do" method="post">
        <!--这里的uerName和passWord要和bean中的一模一样-->
        sname:
        <input type="text" name="sname">用户名</input></br>
        birthday:
        <input type="text" name="birthday">生日</input></br>
        <input type="submit" value="提交">
    </html-el:form>
</body>
</html>
