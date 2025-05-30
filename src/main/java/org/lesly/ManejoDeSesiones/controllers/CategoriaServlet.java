package org.lesly.ManejoDeSesiones.controllers;

import org.lesly.ManejoDeSesiones.models.Categoria;
import org.lesly.ManejoDeSesiones.services.CategoriaService;
import org.lesly.ManejoDeSesiones.services.CategoriaServiceJdbcImplement;
import org.lesly.ManejoDeSesiones.services.LoginService;
import org.lesly.ManejoDeSesiones.services.LoginServiceSessionImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet ("/categoria")
public class CategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos la conexión a la Base de Datos
        Connection conn= (Connection) req.getAttribute("conn");

        //Creamos el nuevo objeto de categorías
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        //Creamos una lista de tipo categorías llamada categorias
        List<Categoria> categorias = service.listar();

        //Para poder verse, necesitamos la autorización así que obtendremos el user name
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> userName= auth.getUserName(req);

        //Setteamos los parámetros y son enviados a la vista
        req.setAttribute("categorias", categorias); //Setteo de categorias clave-valor
        req.setAttribute("username", userName);//setteo de userName

        //luego redireccionamos a la vista de categoria
        getServletContext().getRequestDispatcher("/listadoCategoria.jsp").forward(req, resp);
    }
}