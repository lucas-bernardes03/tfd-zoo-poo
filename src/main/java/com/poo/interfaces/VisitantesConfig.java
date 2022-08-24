package com.poo.interfaces;

import java.io.FileNotFoundException;

public interface VisitantesConfig {
    double valorIngresso();
    boolean validarSocio();
    boolean cadastrarVisitante() throws FileNotFoundException;
}
