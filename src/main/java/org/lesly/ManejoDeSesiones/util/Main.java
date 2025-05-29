package org.lesly.ManejoDeSesiones.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase principal que intenta establecer una conexión a la base de datos
 * usando una clase auxiliar llamada Conexion.
 */
public class Main {

    // Método principal que se ejecuta al iniciar el programa
    public static void main(String[] args) {

        // Mensaje informativo para indicar que se intentará conectar a la BD
        System.out.println("Intentando conectar a la base de datos...");

        // Bloque try-with-resources: asegura que la conexión se cierre automáticamente
        try (Connection conn = Conexion.getConnection()) {

            // Verifica si la conexión no es nula y no está cerrada
            if (conn != null && !conn.isClosed()) {

                // Si la conexión es válida, imprime información de la conexión
                System.out.println("Conexión exitosa a la base de datos.");
                System.out.println("URL: " + conn.getMetaData().getURL());
                System.out.println("Usuario: " + conn.getMetaData().getUserName());
                System.out.println("Base de datos: " + conn.getCatalog());
            }

            // Captura errores relacionados con la conexión a la base de datos
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            System.err.println("Mensaje: " + e.getMessage());            // Mensaje detallado del error
            System.err.println("Código de error SQL: " + e.getSQLState()); // Código de estado SQL
            e.printStackTrace();  // Imprime el stack trace para diagnóstico detallado
        }
    }
}