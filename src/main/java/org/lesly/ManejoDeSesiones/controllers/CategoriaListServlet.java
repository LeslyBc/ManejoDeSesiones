package org.lesly.ManejoDeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lesly.ManejoDeSesiones.services.CategoriaService;
import org.lesly.ManejoDeSesiones.services.CategoriaServiceJdbcImplement;
import org.lesly.ManejoDeSesiones.models.Categoria;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/categoria")
public class CategoriaListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        try {
            List<Categoria> categorias = service.listar();
            req.setAttribute("categorias", categorias);
            getServletContext().getRequestDispatcher("/categorias.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error al listar categorías", e);
        }
    }
}