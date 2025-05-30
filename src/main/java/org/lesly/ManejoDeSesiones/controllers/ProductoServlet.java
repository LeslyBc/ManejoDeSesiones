
/*
 *Nombre:Lesly Becerra
 * Fecha: 15/05/2025
 * Descripcion: Desarrollo de clase productos de servlet para poder mostrar los productos mediante una tabla
 * */
// Paquete donde se encuentra esta clase (organización del proyecto)
package org.lesly.ManejoDeSesiones.controllers;

import org.lesly.ManejoDeSesiones.models.Categoria;
import org.lesly.ManejoDeSesiones.models.Productos;
import org.lesly.ManejoDeSesiones.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtenemos la conexión desde el filtro o contexto
        Connection conn = (Connection) req.getAttribute("conn");

        if (conn == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo establecer conexión a BD");
            return;
        }

        // Instanciamos el servicio de productos
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        // Obtenemos la lista de productos desde el servicio
        List<Productos> productos = service.listar();

        // Servicio de autenticación para obtener el nombre de usuario
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> userName = auth.getUserName(req);

        // Establecemos los atributos que se enviarán a la vista
        req.setAttribute("productos", productos);
        req.setAttribute("username", userName);

        // Redireccionamos a la vista JSP
        getServletContext().getRequestDispatcher("/Main.jsp").forward(req, resp);
    }

    //Sobreescribimos el método doPost
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,
            IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        ProductoService service= new ProductoServiceJdbcImplement(conn);
        String codigo = req.getParameter("codigo");
        String nombre = req.getParameter("nombre");
        Integer stock = Integer.parseInt(req.getParameter("stock"));
        String descripcion = req.getParameter("descripcion");
        String imagen = req.getParameter("imagen");

        //Obtenemos el
        Integer idArticulo;

        try{
            idArticulo=Integer.parseInt(req.getParameter("idArticulo"));
        }catch(NumberFormatException e){
            idArticulo=0;
        }

        Productos productos=new Productos();
        productos.setIdArticulo(idArticulo);
        productos.setCodigo(codigo);
        productos.setNombre(nombre);
        productos.setStock(stock);
        productos.setDescripcion(descripcion);
        productos.setImagen(imagen);
        service.guardar(productos);

        resp.sendRedirect(req.getContextPath()+"/productos");
    }
}
