package org.lesly.ManejoDeSesiones.services;

import org.lesly.ManejoDeSesiones.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> porId(Integer id);
    void guardar(Categoria categoria );
}