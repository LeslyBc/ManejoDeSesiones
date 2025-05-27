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
    <link rel="stylesheet" type="text/css" href="estilos.css">
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