package org.lesly.ManejoDeSesiones.filtro;

import org.lesly.ManejoDeSesiones.util.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
//2) Creamos una clase para hacer el testeo
public class testConnect extends Conexion {
    //hacemos el main
    public static void main(String[] args) {
        System.out.println("Probando la conexao");
        //hacemos un try catch para que la app no se vaya con Diosito
        //conn debe darme la conexión
        try (Connection conn = Conexion.getConnection()) {
            //si la conexión no es nula y no está cerrada entonces me dirá exitosa
            if (conn != null && !conn.isClosed()) {
                System.out.println("YEEEEEEEEEEEEEEEEI ¡Conexión exitosa!");
                //caso contrario, me dira un error de conexión
            } else {
                System.out.println("WRAAAAAAAAA No se pudo establecer la conexión.");
            }
            //cualquier error de sql me pondrá en este bloque de catch
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }
}
