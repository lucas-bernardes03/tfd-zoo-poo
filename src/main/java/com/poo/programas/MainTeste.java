package com.poo.programas;

import com.poo.classes.Visitantes;

import javax.swing.*;

import static javax.swing.JOptionPane.*;

public class MainTeste {

    public static void main(String[] args) {

        String op = JOptionPane.showInputDialog(null, "Codigo: ", "TESTE", OK_CANCEL_OPTION);
        String cpf = JOptionPane.showInputDialog(null, "CPF: ", "TESTE", OK_CANCEL_OPTION);
        String idade = JOptionPane.showInputDialog(null, "Idade: ", "TESTE", OK_CANCEL_OPTION);
        Visitantes v = new Visitantes(Integer.parseInt(op), cpf, Integer.parseInt(idade));
        JOptionPane.showInputDialog(null, v, "TESTE", OK_CANCEL_OPTION);
    }
}
