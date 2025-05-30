package org.lesly.ManejoDeSesiones.services;

import org.lesly.ManejoDeSesiones.models.Categoria;
import org.lesly.ManejoDeSesiones.models.Productos; // Importa la clase Producto del paquete models

import java.sql.SQLException;
import java.util.List; // Importa la interfaz List para manejar listas
import java.util.Optional;

public interface ProductoService {
    List<Productos> listar();

    Optional<Productos> porId(Integer id);

    void guardar(Productos producto );

}

