package org.lesly.ManejoDeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lesly.ManejoDeSesiones.models.Categoria;
import org.lesly.ManejoDeSesiones.services.CategoriaService;
import org.lesly.ManejoDeSesiones.services.CategoriaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/categoria/form")
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        Long id = 0L;
        String idParam = req.getParameter("idCategoria");
        if (idParam != null && !idParam.isBlank()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                id = 0L;
            }
        }

        Categoria categoria = new Categoria();

        if (id > 0) {
            try {
                Optional<Categoria> optionalCategoria = service.porId(id);
                categoria = optionalCategoria.orElse(new Categoria());
            } catch (SQLException e) {
                throw new ServletException("Error al obtener la categoría", e);
            }
        }

        req.setAttribute("categoria", categoria);
        getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        Long idCategoria = 0L;
        String idParam = req.getParameter("idCategoria");
        if (idParam != null && !idParam.isBlank()) {
            try {
                idCategoria = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                idCategoria = 0L;
            }
        }

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        try {
            service.guardar(categoria);
            // Asegúrate de que exista un servlet y JSP para "/categoria"
            resp.sendRedirect(req.getContextPath() + "/categoria");
        } catch (SQLException e) {
            throw new ServletException("Error al guardar la categoría", e);
        }
    }
}