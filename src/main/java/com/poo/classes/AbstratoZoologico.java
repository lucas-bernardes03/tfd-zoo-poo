package com.poo.classes;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class AbstratoZoologico {

    private String titleMensagens = "Zoologico TAL";

    public void mostraAvisoTela(String aviso) {
        JOptionPane.showMessageDialog(null, aviso, titleMensagens, JOptionPane.WARNING_MESSAGE);
    }

    public void mostraMsgSucessoTela(String msg) {
        JOptionPane.showMessageDialog(null, msg, titleMensagens, JOptionPane.INFORMATION_MESSAGE);
    }

    public HashMap<String, String> loginsValidos() {
        HashMap<String, String> logins = new HashMap<>();
        try {
            FileReader pathAdms = new FileReader("src/main/java/com/poo/arquivos/adm.csv");
            BufferedReader bf = new BufferedReader(pathAdms);
            StringBuilder sb = new StringBuilder();

            String linha = bf.readLine();
            while (linha != null) {
                if (linha.contains("login,password")) {
                    linha = bf.readLine();
                    continue;
                }
                String[] campos = linha.split(",");
                logins.put(campos[1], campos[2]);
                linha = bf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return logins;
    }

    public void verificaSeFinaliza(Object obj) {
        if (obj == null) {
            JOptionPane.showMessageDialog(null, "Obrigado por utilizar o programa! Até mais \uD83D\uDE03");
            System.exit(0);
            return;
        }
    }
}
