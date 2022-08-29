package com.poo.classes;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class AbstratoZoologico {

    private String titleMensagens = "Zoologico POO";

    public void mostraAvisoTela(String aviso) {
        JOptionPane.showMessageDialog(null, aviso, titleMensagens, JOptionPane.WARNING_MESSAGE);
    }

    public void mostraMsgSucessoTela(String msg) {
        JOptionPane.showMessageDialog(null, msg, titleMensagens, JOptionPane.INFORMATION_MESSAGE);
    }

    public void mensagemParaExceptionTela(String msgErro) {
        JOptionPane.showMessageDialog(null, msgErro, titleMensagens, JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public String leArquivo(String linhaInicial, String pathFinal) {
        String linhasTotais = "";

        try {
            String path = "src/main/java/com/poo/arquivos/";
            FileReader pathProcurado = new FileReader(path + pathFinal);
            BufferedReader bf = new BufferedReader(pathProcurado);
            StringBuilder sb = new StringBuilder();

            String linha = bf.readLine();
            while (linha != null) {
                if (linha.contains(linhaInicial)) {
                    linha = bf.readLine();
                    continue;
                }

                linhasTotais += linha + "\n";
                linha = bf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return linhasTotais;
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
