package org.lesly.ManejoDeSesiones.services;

//Importacion de librerias
import org.lesly.ManejoDeSesiones.models.Productos;
import org.lesly.ManejoDeSesiones.repository.ProductosRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplement implements ProductoService { // Declara la clase que implementa la interfaz ProductoService

    //Creamos una variable de tipo CategoríaRepositoryJdbcImplement
    private ProductosRepositoryJdbcImplement repositoryJdbc;

    //Creamos un constructor donde recibimos la conexión
    public ProductoServiceJdbcImplement(Connection conn) {
        this.repositoryJdbc = new ProductosRepositoryJdbcImplement(conn);
    }

    @Override
    public List<Productos> listar() {

        try{
            return repositoryJdbc.listar();
        }catch(SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


    @Override
    public Optional<Productos> porId(Integer id) {
        try{
            return Optional.ofNullable(repositoryJdbc.porId(id));
        }catch(SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Productos producto) {
        try {
            repositoryJdbc.guardar(producto);
        }catch ( SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }
}










