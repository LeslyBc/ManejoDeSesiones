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
public class CategoriaFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        Long id;
        try {
            id = Long.parseLong(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Categoria categoria = new Categoria();

        if (id > 0) {
            try {
                Optional<Categoria> optionalCategoria = service.porId(id);
                if (optionalCategoria.isPresent()) {
                    categoria = optionalCategoria.get();
                }
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

        Long idCategoria;
        try {
            idCategoria = Long.parseLong(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            idCategoria = 0L;
        }

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        try {
            service.guardar(categoria);
            resp.sendRedirect(req.getContextPath() + "/categorias");
        } catch (SQLException e) {
            throw new ServletException("Error al guardar la categoría", e);
        }
    }
}