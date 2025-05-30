package org.lesly.ManejoDeSesiones.services;

import org.lesly.ManejoDeSesiones.models.Productos; // Importa la clase Producto del paquete models
import java.util.List; // Importa la interfaz List para manejar listas

public interface ProductoService { // Declara la interfaz ProductoService
    List<Productos> listar(); // Método abstracto que retorna una lista de productos
}
