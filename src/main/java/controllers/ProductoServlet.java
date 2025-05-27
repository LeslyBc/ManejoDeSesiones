
/*
 *Nombre:Lesly Becerra
 * Fecha: 15/05/2025
 * Descripcion: Desarrollo de clase productos de servlet para poder mostrar los productos mediante una tabla
 * */
// Paquete donde se encuentra esta clase (organización del proyecto)
package controllers;

// Importaciones necesarias
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.LoginService;
import services.LoginServiceSessionImplement;
import services.ProductoService;
import services.ProductosServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

// Define que esta clase es un Servlet y responde a la URL "/productos"
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    // Servicio para manejar los productos (obtener lista, etc.)
    private final ProductoService productoService = new ProductosServiceImplement();

    // Servicio para manejar la autenticación (verificar sesión)
    private final LoginService authService = new LoginServiceSessionImplement();

    // Método que maneja las solicitudes GET a /productos
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Verifica si hay un usuario logueado y obtiene su nombre
        Optional<String> username = authService.getUserName(req);
        // Bandera que indica si hay sesión activa
        boolean isLoggedIn = username.isPresent();

        // Configura el tipo de contenido de la respuesta como HTML con UTF-8
        resp.setContentType("text/html;charset=UTF-8");

        // Try-with-resources para manejar el PrintWriter (se cierra automáticamente)
        try (PrintWriter out = resp.getWriter()) {
            // Comienza a generar el documento HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");  // Encoding del documento
            out.println("<title>Productos</title>");  // Título de la pestaña

            // Enlace al archivo CSS externo (ubicado en webapp/estilos.css)
            out.println("<link rel='stylesheet' href='estilos.css'>");
            out.println("</head>");

            // Cuerpo del documento HTML
            out.println("<body>");

            // Mensaje condicional según si está logueado o no
            if (isLoggedIn) {
                // Saludo personalizado al usuario logueado
                out.println("<h2 class='mensaje bienvenida'>Hola " + username.get() + "</h2>");
            } else {
                // Mensaje invitando a iniciar sesión (con enlace)
                out.println("<div class='login-message'>"
                        + "<a href='login.html'>Inicie sesión</a> logueate para ver precios"
                        + "</div>");
            }

            // Comienza la tabla de productos
            out.println("<table class='product-table'>");

            // Encabezados de la tabla
            out.println("<tr><th>ID</th><th>Nombre</th><th>Categoría</th>");
            // Encabezado de precio solo visible para usuarios logueados
            if (isLoggedIn) out.println("<th>Precio</th>");
            out.println("</tr>");

            // Recorre todos los productos y genera una fila por cada uno
            productoService.listar().forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");  // Columna ID
                out.println("<td>" + p.getNombre() + "</td>");  // Columna Nombre
                out.println("<td>" + p.getTipo() + "</td>");  // Columna Categoría
                // Columna Precio solo visible para usuarios logueados
                if (isLoggedIn) out.println("<td class='price'>$" + p.getPrecio() + "</td>");
                out.println("</tr>");
            });

            // Cierra la tabla
            out.println("</table>");

            // Enlace para volver a la página de inicio
            out.println("<p class='return-link'><a href='index.html'>Volver a pagina inicio</a></p>");

            // Cierra el cuerpo y el HTML
            out.println("</body>");
            out.println("</html>");
        }
    }
}