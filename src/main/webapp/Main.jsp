<%--
  Created by IntelliJ IDEA.
  User: Lesly Becerra
  Date: 29/5/2025
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.lesly.ManejoDeSesiones.models.*, java.util.*" %>

<%
  List<Productos> productos = (List<Productos>) request.getAttribute("productos");
  Optional<String> username = (Optional<String>) request.getAttribute("username");
  if (productos == null) {
    productos = new ArrayList<>();
  }
%>

<html>
<head>
  <title>Listado Productos</title>
  <style>
    table, th, td { border: 1px solid black; border-collapse: collapse; padding: 5px; }
  </style>
</head>
<body>

<%
  if(username.isPresent()){%>

<div style="color:blue;"> Hola <%= username.get()%>, bienvenido a la aplicación</div>

<div><p><a href="${pageContext.request.contextPath}/productos/form">Ingrese el producto</a></p></div>
<%}%>


<h1>Listado de Productos</h1>

<table>
  <thead>
  <tr>
    <th>Id Artículo</th>
    <th>Id Categoría</th>
    <th>Código</th>
    <th>Nombre</th>
    <th>Stock</th>
    <th>Descripción</th>
    <th>Imagen</th>
    <th>Condición</th>
    <th>Acciones</th>
  </tr>
  </thead>
  <tbody>
  <% for (Productos pro : productos) { %>
  <tr>
    <td><%= pro.getIdArticulo() %></td>
    <td><%= pro.getIdCategoria() %></td>
    <td><%= pro.getCodigo() %></td>
    <td><%= pro.getNombre() %></td>
    <td><%= pro.getStock() %></td>
    <td><%= pro.getDescripcion() %></td>
    <td><%= pro.getImagen() %></td>
    <td><%= pro.getCondicion() %></td>
    <td>
      <a href="#">Editar</a> |
      <a href="#">Activar/Desactivar</a>
    </td>
  </tr>
  <% } %>
  </tbody>
</table>

</body>
</html>
