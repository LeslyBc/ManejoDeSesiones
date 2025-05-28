<!--
User: Lesly Becerra
Fecha: 15/05/2025
Descripcion: Desarrollo de HTML en archivo jsp  para la visualizacion de el ingreso al ingreso de los datos
del cliente al ingresar a la ruta del jsp.-->

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="estilos.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ancizar+Serif:ital,wght@0,300..900;1,300..900&family=IBM+Plex+Mono:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
</head>
<body>
<div class="login-container">
    <h1>Login de usuario</h1>
    <form action="login" method="post">
        <label for="username">Nombre de usuario:</label>
        <input type="text" name="username" id="username" required>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>

        <input type="submit" value="Enviar">
    </form>
</div>
</body>
</html>