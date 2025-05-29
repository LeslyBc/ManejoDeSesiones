<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.lesly.ManejoDeSesiones.models.*" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    String username = (String) request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Listado Categoría</title>
    <link rel="stylesheet" href="estilos.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">

<% if (username != null && !username.isEmpty()) { %>
<div style="color:blue;">
    Hola <%= username %>, bienvenido a la aplicación
</div>
<% } %>

<div>
    <p><a href="<%= request.getContextPath() %>/categoria/form" class="btn btn-primary">Ingresar nueva categoría</a></p>
</div>

<h1 class="mb-4">Listado Categoría</h1>
<a href="index.html" class="btn btn-secondary mb-3">Volver al menú</a>
<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th>Id Categoría</th>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Condición</th>
        <th colspan="2">Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (categorias != null && !categorias.isEmpty()) {
            for (Categoria cat : categorias) {
    %>
    <tr>
        <td><%= cat.getIdCategoria() %></td>
        <td><%= cat.getNombre() %></td>
        <td><%= cat.getDescripcion() %></td>
        <td><%= cat.getCondicion() %></td>
        <td><a href="<%= request.getContextPath() %>/categoria/form?idCategoria=<%= cat.getIdCategoria() %>" class="btn btn-sm btn-warning">Editar</a></td>
        <td>
            <form action="<%= request.getContextPath() %>/categoria/activarDesactivar" method="post" style="display:inline;">
                <input type="hidden" name="idCategoria" value="<%= cat.getIdCategoria() %>">
                <button type="submit" class="btn btn-sm btn-secondary">
                    <%
                        // SOLUCIÓN: convertir la condición a String antes de comparar
                        String condicionStr = String.valueOf(cat.getCondicion());
                        out.print("Activo".equalsIgnoreCase(condicionStr) ? "Desactivar" : "Activar");
                    %>
                </button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6" class="text-center">No hay categorías disponibles.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>