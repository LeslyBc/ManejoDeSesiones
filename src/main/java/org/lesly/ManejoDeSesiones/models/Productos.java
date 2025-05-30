package org.lesly.ManejoDeSesiones.models;

public class Productos { // Declara la clase Producto


    private Integer idArticulo;

    private Integer idCategoria;

    private String codigo;

    private String nombre;

    private Integer stock;

    private String descripcion;

    private String imagen;

    private Integer condicion;

    public Productos(){

    }

    public Productos(Integer idArticulo, Integer idCategoria, String codigo, String nombre, Integer stock, String descripcion, String imagen, Integer condicion) {
        this.idArticulo = idArticulo;
        this.idCategoria = idCategoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.condicion = condicion;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCondicion() {
        return condicion;
    }

    public void setCondicion(Integer condicion) {
        this.condicion = condicion;
    }
}