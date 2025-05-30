package org.lesly.ManejoDeSesiones.controllers;

import org.lesly.ManejoDeSesiones.models.Productos;
import org.lesly.ManejoDeSesiones.services.ProductoService;
import org.lesly.ManejoDeSesiones.services.ProductoServiceJdbcImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/form")

public class ProductoFormControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req. getAttribute("conn");

        ProductoService service = new ProductoServiceJdbcImplement(conn);

        Integer id;

        try{

            id=Integer.parseInt(req.getParameter("idArticulo"));

        }catch(NumberFormatException e){

            id=0;

        }

        Productos productos = new Productos();
        if (id>0){
            //Creamos una variable de tipo optional
            //para obtener la categoria por id
            Optional <Productos> optionalProductos= service.porId(id);
            //Si la variable optional está presente
            //obtenemos todos los valores
            if(optionalProductos.isPresent()){
                productos = optionalProductos.get();
            }
        }

        req.setAttribute("productos", productos);
        getServletContext().getRequestDispatcher("/FormularioProductos.jsp").forward(req,resp);













    }
}
