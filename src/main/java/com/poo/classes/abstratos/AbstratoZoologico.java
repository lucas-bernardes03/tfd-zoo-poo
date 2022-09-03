package com.poo.classes.abstratos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import static javax.swing.JOptionPane.*;

public abstract class AbstratoZoologico {

    private String titleMensagens = "Zoologico POO";

    public void mostraMsgTela(String msg) {
        JOptionPane.showMessageDialog(null, msg, titleMensagens, PLAIN_MESSAGE);
    }

    public void mostraMsgAvisoTela(String aviso) {
        JOptionPane.showMessageDialog(null, aviso, titleMensagens, JOptionPane.WARNING_MESSAGE);
    }

    public void mostraMsgInformacaoTela(String info) {
        JOptionPane.showMessageDialog(null, info, titleMensagens, INFORMATION_MESSAGE);
    }

    public void mostraMsgQuestionTela(String pergunta) {
        JOptionPane.showMessageDialog(null, pergunta, titleMensagens, QUESTION_MESSAGE);
    }

    public void mostraMsgErroTela(String erro) {
        JOptionPane.showMessageDialog(null, erro, titleMensagens, JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public void mostraMsgOkCancelTela(String msg) {
        int opcao = JOptionPane.showConfirmDialog(null, msg,
                "Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, QUESTION_MESSAGE);
        if (opcao == CANCEL_OPTION) verificaSeFinaliza(null);
    }

    /**
     * Metodo para leitura de qualquer arquivo no diretorio arquivos
     *
     * @param linhaInicial Conteudo da primeira linha do CSV, que nao deve ser lida.
     * @param pathFinal Nome do arquivo em CSV.
     * @return Retorna todas as linhas concatenadas em String
     */
    public String leArquivo(String linhaInicial, String pathFinal) {
        String linhasTotais = "";
        int numeroLinha = 1;

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

                linhasTotais += "| " + numeroLinha + "," + linha + " |\n";
                numeroLinha++;
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
        }
    }
}
