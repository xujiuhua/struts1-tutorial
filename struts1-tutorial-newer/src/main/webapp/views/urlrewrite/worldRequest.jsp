<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../commons/taglibs.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Using the example above JSP's with the code<br>
    <a href="<%= response.encodeURL("/views/urlrewrite/world.jsp?country=usa&amp;city=nyc") %>">nyc</a>
    will output
    <a href="/world/usa/nyc">nyc</a>
    <br>
    Or JSTL<br>
    <a href="<c:url value="/views/urlrewrite/world.jsp?country=${country}&amp;city=${city}" />">nyc</a>
    will output
    <a href="/world/usa/nyc">nyc</a>
</body>
</html>
