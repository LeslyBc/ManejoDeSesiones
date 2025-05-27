package org.lesly.ManejoDeSesiones.services;

import org.lesly.ManejoDeSesiones.models.Productos;
import java.util.List;

public interface ProductoService {
    // Método para obtener la lista de productos
    List<Productos>listar();
}