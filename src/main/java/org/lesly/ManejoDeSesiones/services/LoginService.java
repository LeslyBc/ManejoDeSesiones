package org.lesly.ManejoDeSesiones.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

//La interfaz me permite implementar unh método
public interface LoginService {
    //Optional: Devuelve un string y dato vacío
    Optional<String> getUserName(HttpServletRequest request);

}