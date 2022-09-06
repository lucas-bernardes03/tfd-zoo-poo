package com.poo.classes.abstratos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import static javax.swing.JOptionPane.*;

public abstract class AbstratoZoologico {

    private String titleMensagens = "Zoologico POO";

    /**
     * Metodo para mostrar uma mensagem sem nenhum icone na tela.
     *
     * @param msg Mensagem a ser mostrada
     */
    public void mostraMsgTela(String msg) {
        JOptionPane.showMessageDialog(null, msg, titleMensagens, PLAIN_MESSAGE);
    }

    /**
     * Metodo para mostrar uma mensagem de aviso com icone de atençao na tela.
     *
     * @param aviso Aviso a ser mostrado
     */
    public void mostraMsgAvisoTela(String aviso) {
        JOptionPane.showMessageDialog(null, aviso, titleMensagens, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Metodo para mostrar uma mensagem de informaçao com icone de info na tela.
     *
     * @param info Informaçao a ser mostrada
     */
    public void mostraMsgInformacaoTela(String info) {
        JOptionPane.showMessageDialog(null, info, titleMensagens, INFORMATION_MESSAGE);
    }

    /**
     * Metodo para mostrar uma mensagem de questionamento com icone de duvida na tela.
     *
     * @param pergunta Pergunta a ser mostrada
     */
    public void mostraMsgQuestionTela(String pergunta) {
        JOptionPane.showMessageDialog(null, pergunta, titleMensagens, QUESTION_MESSAGE);
    }

    /**
     * Metodo para mostrar uma mensagem de erro com icone de error na tela e tambem finalizar o programa.
     *
     * @param erro Erro a ser mostrado
     */
    public void mostraMsgErroTela(String erro) {
        JOptionPane.showMessageDialog(null, erro, titleMensagens, JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    /**
     * Metodo para mostrar uma pergunta com opçoes de 'OK' e 'CANCEL'.
     *
     * @param msg Mensagem, pergunta, alerta a ser mostrado
     */
    public void mostraMsgOkCancelTela(String msg) {
        int opcao = JOptionPane.showConfirmDialog(null, msg,
                "Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, QUESTION_MESSAGE);
        if (opcao == CANCEL_OPTION) verificaSeFinaliza(null);
    }

    /**
     * Metodo para leitura de qualquer arquivo no diretorio /arquivos
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

                linhasTotais += "| " + numeroLinha + ", " + linha + " |\n";
                numeroLinha++;
                linha = bf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return linhasTotais;
    }

    /**
     * Metodo para fazer a leitura dos logins cadastrados
     *
     * @return Uma coleçao com key: 'login' e value: 'password'
     */
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

    /**
     * Metodo para verificar se finaliza o programa a partir de uma mensagem mostrada na tela
     *
     * @param obj Variavel que mostre algo na tela: showMessage, inputDialog...
     */
    public void verificaSeFinaliza(Object obj) {
        if (obj == null) {
            JOptionPane.showMessageDialog(null, "Obrigado por utilizar o programa! Até mais \uD83D\uDE03");
            System.exit(0);
        }
    }
}
