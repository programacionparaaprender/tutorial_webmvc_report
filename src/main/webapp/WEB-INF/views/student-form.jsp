<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${empty student.id ? 'Crear' : 'Editar'} Estudiante</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            line-height: 1.6;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 10px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
        .button.cancel {
            background-color: #f44336;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        .error {
            color: #f44336;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${empty student.id ? 'Crear Nuevo Estudiante' : 'Editar Estudiante'}</h1>
        
        <form action="${pageContext.request.contextPath}/students/save" method="post">
            <input type="hidden" name="id" value="${student.id}">
            
            <div class="form-group">
                <label for="firstName">Nombre:</label>
                <input type="text" id="firstName" name="firstName" value="${student.firstName}" required>
                <c:if test="${not empty firstNameError}">
                    <span class="error">${firstNameError}</span>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="lastName">Apellido:</label>
                <input type="text" id="lastName" name="lastName" value="${student.lastName}" required>
                <c:if test="${not empty lastNameError}">
                    <span class="error">${lastNameError}</span>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${student.email}" required>
                <c:if test="${not empty emailError}">
                    <span class="error">${emailError}</span>
                </c:if>
            </div>
            
            <div class="button-container">
                <button type="submit" class="button">Guardar</button>
                <a href="${pageContext.request.contextPath}/students/" class="button cancel">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>