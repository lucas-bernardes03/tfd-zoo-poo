package com.poo.classes;

import com.poo.exceptions.CPFInvalidoException;
import com.poo.interfaces.VisitantesConfig;
import com.poo.validacoes.CPF;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Visitantes extends AbstratoZoologico implements VisitantesConfig {

    private int codigoVerificacao;
    private String cpf;
    private int idade;

    private CPF cpfValidado;
    private double valorIngresso;

    public Visitantes(int codigoVerificacao, String cpf, int idade) {
        try {
            this.codigoVerificacao = codigoVerificacao;
            this.cpf = cpf;
            this.idade = idade;
            cpfValidado = new CPF(this.cpf);
            valorIngresso = valorIngresso();
        } catch (CPFInvalidoException exception) {
            mostraAvisoTela("O CPF informado nao e valido!");
        }
    }

    @Override
    public double valorIngresso() {
        double valor = 0;

        if (idade < 18) valor = 45;
        if (idade >= 18 && idade < 60) valor = 80;
        if (idade > 60) valor = 30;

        if (validarSocio()) valor -= 50;
        if (valor < 0) valor = 0;

        return valor;
    }

    @Override
    public boolean validarSocio() {
        try {
            FileReader pathSocios =  new FileReader("src/main/java/com/poo/arquivos/socios.csv");
            BufferedReader bf = new BufferedReader(pathSocios);
            List<String> cpfsCadastrados = new ArrayList<>();

            String linha = bf.readLine();
            while (linha != null) {
                if (linha.contains("nome,cpf")) {
                    linha = bf.readLine();
                    continue;
                }

                String[] campos = linha.split(",");
                cpfsCadastrados.add(campos[1]);
                linha = bf.readLine();
            }

            return cpfsCadastrados.contains(cpfValidado.getCpfNumeros().trim());
        } catch (Exception e) {
            e.printStackTrace();
            mostraAvisoTela("Erro generico, o Programa sera finalizado!");
        }

        return false;
    }

}
