<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${titulo}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            line-height: 1.6;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        h1 {
            color: #333;
        }
        .tech-list {
            margin: 15px 0;
            padding-left: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${titulo}</h1>
        <p>${mensaje}</p>
        
        <p>Fecha actual: ${fecha}</p>
        <p>Total usuarios: ${totalUsuarios}</p>
        <h3>Tecnologías utilizadas:</h3>
        <ul class="tech-list">
            <c:forEach items="${tecnologias}" var="tech">
                <li>${tech}</li>
            </c:forEach>
        </ul>
        
        <p>Context Path: ${pageContext.request.contextPath}</p>
    </div>
</body>
</html>