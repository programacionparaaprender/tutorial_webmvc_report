<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring MVC 3.0.5 - Servlet 2.4</title>
</head>
<body>
    <h1>${mensaje}</h1>
    <p>Versión de Spring: ${version}</p>
    <p>Versión de Servlet: ${servletVersion}</p>
    
    <p>Context Path: ${pageContext.request.contextPath}</p>
</body>
</html>