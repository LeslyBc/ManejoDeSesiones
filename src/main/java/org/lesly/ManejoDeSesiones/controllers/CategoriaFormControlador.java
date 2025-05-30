package org.lesly.ManejoDeSesiones.controllers;

import org.lesly.ManejoDeSesiones.models.Categoria;
import org.lesly.ManejoDeSesiones.services.CategoriaService;
import org.lesly.ManejoDeSesiones.services.CategoriaServiceJdbcImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/categoria/form")
public class CategoriaFormControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //traemos la conexión a la base de datos
        Connection conn = (Connection) req. getAttribute("conn");

        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        Integer id;

        try{
            //En la variable id guardamos lo que
            //estamos mapeano por el mpetodo get idCategoria
            id=Integer.parseInt(req.getParameter("idCategoria"));
        }catch(NumberFormatException e){
            id=0;
        }

        //Creamos un objeto Categoria vacio
        Categoria categorias = new Categoria();
        //Verificamos si el id > 0
        if (id>0){
            //Creamos una variable de tipo optional
            //para obtener la categoria por id
            Optional<Categoria> optionalCategoria= service.porId(id);
            //Si la variable optional está presente
            //obtenemos todos los valores
            if(optionalCategoria.isPresent()){
                categorias=optionalCategoria.get();
            }
        }
        //Setteamos loa atributos en el alcance de
        //request
        req.setAttribute("categorias", categorias);
        getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req,resp);

    }

    //Sobreescribimos el método doPost
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException,
            IOException {
        Connection conn = (Connection) req.
                getAttribute("conn");
        CategoriaService service= new CategoriaServiceJdbcImplement(conn);
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        //Obtenemos el idCategoria
        Integer idCategoria;
        try{
            idCategoria=Integer.parseInt(req.getParameter("idCategoria"));
        }catch(NumberFormatException e){
            idCategoria=0;
        }

        Categoria categoria=new Categoria();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        service.guardar(categoria);
        //Redireccionamos al listado para no nos
        //ejecute el método doPost
        resp.sendRedirect(req.getContextPath()+"/categoria");
    }

}