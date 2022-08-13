package com.poo.classes;

import javax.swing.*;

public class AbstratoZoologico {

    private String titleMensagens = "Zoologico TAL";

    protected void mostraAvisoTela(String aviso) {
        JOptionPane.showMessageDialog(null, aviso, titleMensagens, JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }
}
