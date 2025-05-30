package org.lesly.ManejoDeSesiones.repository;

import org.lesly.ManejoDeSesiones.models.Categoria;
import org.lesly.ManejoDeSesiones.models.Productos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductosRepositoryJdbcImplement implements Repository<Productos> {
    //1) Creamos una variable donde vamos a guardar la conexión
    private Connection conn;

    //2) Genero un constructor que recibe la conexión
    public ProductosRepositoryJdbcImplement(Connection conn) {
        //va a traer la conexión y la guardará en el conn que está en la parte derecha del igual
        this.conn = conn;
    }

    @Override
    public List<Productos> listar() throws SQLException {
        List<Productos> productos = new ArrayList<>(); //Creamos un nuevo objeto de tipo categoría
        try(Statement stmt = conn.createStatement(); //Esto me permite interactuar con la bdd
            ResultSet rs = stmt.executeQuery("select * from articulo")){ //Me permite realizar la consulta
            while (rs.next()) { //mientas lo siga recorriendo
                Productos p = getProductos(rs);

                productos.add(p);
            }
        }
        return productos;
    }



    @Override
    public Productos porId(Integer id) throws SQLException {
        //Creo un objeto de tipo categoría nulo
        Productos productos = null;
        try(PreparedStatement stmt = conn.prepareStatement("select * from articulo where id = ?")){ //Selecciona todo de categoria donde el id del método
            stmt.setInt(1, id); //Setea el valor en la columna número uno
            try(ResultSet rs = stmt.executeQuery()){
                productos = getProductos(rs);
            }
        }
        return productos;
    }

    @Override
    public void guardar(Productos productos) throws SQLException {
        // Declaración de la variable SQL
        String sql;

        // Determina si se trata de una actualización (UPDATE) o una inserción nueva (INSERT)
        boolean esUpdate = productos.getIdCategoria() != null && productos.getIdCategoria() > 0;

        if (esUpdate) {
            // Si el ID existe, se actualiza la categoría existente
            sql = "UPDATE articulo SET codigo= ?, nombre = ?, stock = ?, descripcion = ?, imagen= ? WHERE idCategoria = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, productos.getIdArticulo());
                stmt.setLong(2, productos.getIdCategoria());
                stmt.setString(3, productos.getCodigo());
                stmt.setString(4, productos.getNombre());
                stmt.setLong(5, productos.getStock());
                stmt.setString(6, productos.getDescripcion());
                stmt.setString(7, productos.getImagen());
                stmt.executeUpdate();
            }
        } else {
            // Si el ID no existe, se inserta una nueva categoría
            // La condición se pone en 1 por defecto (activo)
            sql = "INSERT INTO categoria (codigo, nombre, stock, descripcion, imagen, condicion) VALUES (?, ?, ?, ?, ? , 1)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, productos.getIdArticulo());
                stmt.setLong(2, productos.getIdCategoria());
                stmt.setString(3, productos.getCodigo());
                stmt.setString(4, productos.getNombre());
                stmt.setLong(5, productos.getStock());
                stmt.setString(6, productos.getDescripcion());
                stmt.setString(7, productos.getImagen());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void eliminar(Integer id) throws SQLException {

        String sql = "update categoria set condicion = 0 where idCategoria = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }

    private static Productos getProductos(ResultSet rs) throws SQLException {
        Productos p = new Productos(); //Creo un nuevo objeto vació de la clase categoría porque lo lleno con lo de abajo
        p.setIdArticulo(rs.getInt("idArticulo"));
        p.setIdCategoria(rs.getInt("idCategoria"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombre(rs.getString("nombre")); //Settear el nombre del método getString del javaBeans
        p.setStock(rs.getInt("stock"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setImagen(rs.getString("imagen"));
        p.setCondicion(rs.getInt("condicion"));
        return p;
    }
}
