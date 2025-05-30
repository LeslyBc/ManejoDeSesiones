package org.lesly.ManejoDeSesiones.services;

//Importacion de librerias
import org.lesly.ManejoDeSesiones.models.Productos;

import java.util.Arrays;
import java.util.List;

public class ProductosServiceImplement implements ProductoService { // Declara la clase que implementa la interfaz ProductoService

    @Override
    public List<Productos> listar() {
        // Retorna una lista inmutable con tres productos de ejemplo
        return Arrays.asList(new Productos(1L, "laptop", "computacion", 523.21),
                new Productos(2L, "Mouse", "inalambrico", 15.25),
                new Productos(3L, "Impresora", "tinta continua", 256.25));
    }
}





