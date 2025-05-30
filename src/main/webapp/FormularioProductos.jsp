<%--
  Created by IntelliJ IDEA.
  User: Lesly Becerra
  Date: 30/5/2025
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.lesly.ManejoDeSesiones.models.Categoria" %>
<%@ page import="org.lesly.ManejoDeSesiones.models.Productos" %>
<%
  Productos productos = (Productos) request.getAttribute("productos");
  if (productos == null) {
    productos = new Productos(); // categoría vacía para creación
  }
%>
<%-- Declaramos el tipo de contenido de la página y codificación --%>
<html>
<head>
  <%-- El título de la página cambia según si estamos editando o creando una categoría --%>
  <title>Nueva Categoría</title>

  <link rel="stylesheet" href="<%= request.getContextPath()%>css/formularioCategoria.css">
</head>
<body>

<%-- Muestra el encabezado dinámicamente según si estamos creando o editando una categoría --%>
<h1>Nuevos Productos</h1>

<%-- Formulario que envía los datos al controlador CategoriaFormControlador (POST) --%>
<form action="<%= request.getContextPath() %>/productos/form" method="post">

  <input type="hidden" name="idArticulo" value="<%= productos.getIdArticulo() != null ? productos.getIdArticulo() : 0 %>"/>
  <input type="hidden" name="idCategoria" value="<%= productos.getIdCategoria() != null ? productos.getIdCategoria() : 0 %>" />

  <label for="codigo">Codigo</label>
  <input type="text" id="codigo" name="codigo" value="<%= productos.getCodigo() != null ? productos.getCodigo() : "" %>" required />

  <br/> <br/>


  <label for="nombre">Nombre:</label>
  <input type="text" id="nombre" name="nombre" value="<%= productos.getNombre() != null ? productos.getNombre() : "" %>" required />

  <br/><br/>

  <label for="stock">Stock</label>
  <input type="number" id="stock" name="stock" value="<%= productos.getStock() != null ? productos.getStock() : "" %>" required />>

  <br/><br/>

  <label for="descripcion">Descripción:</label>
  <textarea id="descripcion" name="descripcion" required><%= productos.getDescripcion() != null ? productos.getDescripcion() : "" %></textarea>

  <br/><br/>

  <label for="imagen">Nombre de la imagen:</label>
  <input type="text" id="imagen" name="imagen" value="<%= productos.getImagen() != null ? productos.getImagen() : "" %>" required>
  <br/><br/>

  <%-- Botón para guardar y un enlace para cancelar (regresa al listado) --%>
  <button type="submit">Guardar</button>
  <a href="<%= request.getContextPath() %>/productos">Cancelar</a>
</form>
</body>
</html>
