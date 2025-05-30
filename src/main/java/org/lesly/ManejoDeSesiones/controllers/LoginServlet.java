package org.lesly.ManejoDeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.lesly.ManejoDeSesiones.services.LoginService;
import org.lesly.ManejoDeSesiones.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);

        if (usernameOptional.isPresent()) {
            //Redirigir al servlet de productos, no directamente a Main.jsp
            resp.sendRedirect("productos");
        } else {
            //Mostrar formulario de login
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            // ✅ Redirigir al servlet de productos después del login
            resp.sendRedirect("productos");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no tiene acceso");
        }
    }
}