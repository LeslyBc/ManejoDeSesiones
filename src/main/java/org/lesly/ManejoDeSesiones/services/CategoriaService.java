package org.lesly.ManejoDeSesiones.services;

import org.lesly.ManejoDeSesiones.models.Categoria;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar() throws SQLException;
    Optional<Categoria> porId(Long id) throws SQLException;
    void guardar(Categoria categoria) throws SQLException;
    void eliminar(Long id) throws SQLException;
}