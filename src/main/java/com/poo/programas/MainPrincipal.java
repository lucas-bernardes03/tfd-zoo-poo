package com.poo.programas;

import com.poo.classes.Administracao;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.*;

public class MainPrincipal {

    private static Administracao adm = new Administracao();

    public static void main(String[] args) {
        int opcaoInicial = JOptionPane.showConfirmDialog(null, "Iniciar Gerenciador do Zoologico?",
                "Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, QUESTION_MESSAGE);

        if (opcaoInicial == OK_OPTION) {
            boolean b = fazLogin();
            while (!b) {
                adm.mostraAvisoTela("USUARIO OU SENHA INVALIDOS. Tente novamente!");
                b = fazLogin();
            }
            adm.mostraMsgSucessoTela("Sucesso, login feito!");
        } else {
            JOptionPane.showMessageDialog(null, "O programa sera finalizado! \uD83D\uDE03");
            System.exit(0);
        }
    }

    public static boolean fazLogin() {
        boolean b = false;
        String login = JOptionPane.showInputDialog(null, "Login:",
                "Login Administraçao", OK_CANCEL_OPTION);
        adm.verificaSeFinaliza(login);
        String password = JOptionPane.showInputDialog(null, "Password:",
                "Login Administraçao", OK_CANCEL_OPTION);
        adm.verificaSeFinaliza(password);

        HashMap<String, String> logins = adm.loginsValidos();
        for (Map.Entry<String, String> pair : logins.entrySet()) {
            if (pair.getKey().equals(login) && pair.getValue().equals(password)) {
                b = true;
                break;
            }
        }

        return b;
    }
}
