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
        .tech-list, .user-list {
            margin: 15px 0;
            padding-left: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
        .button.edit {
            background-color: #2196F3; /* Azul */
        }
        .button.delete {
            background-color: #f44336; /* Rojo */
        }
        .button-container {
            margin-bottom: 20px;
        }
        .actions-cell {
            white-space: nowrap; /* Evita que los botones se apilen */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="button-container">
            <a href="${pageContext.request.contextPath}/students/new" class="button">Crear Estudiante</a>
        </div>
        
        <h3>Lista de Estudiantes:</h3>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.email}</td>
                        <td class="actions-cell">
                            <!-- Botón Editar -->
                            <a href="${pageContext.request.contextPath}/students/edit/${student.id}" 
                               class="button edit">
                                Editar
                            </a>
                            <!-- Botón Eliminar -->
                            <a href="${pageContext.request.contextPath}/students/delete/${student.id}" 
                               class="button delete" 
                               onclick="return confirm('¿Estás seguro de que quieres eliminar este estudiante?')">
                                Eliminar
                            </a>
							<!-- Botón Editar -->
                            <a href="${pageContext.request.contextPath}/report/${student.id}" 
                               class="button edit">
                                Ver PDF
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <p>Context Path: ${pageContext.request.contextPath}</p>
    </div>
</body>
</html>