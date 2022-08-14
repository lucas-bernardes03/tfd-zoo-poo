package com.poo.classes;

import com.poo.exceptions.CPFInvalidoException;
import com.poo.interfaces.VisitantesConfig;
import com.poo.validacoes.CPF;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Visitantes extends AbstratoZoologico implements VisitantesConfig {

    @Getter private int codigoVerificacao;
    @Getter private String cpf;
    @Getter private int idade;
    @Getter private double valorIngresso;
    private CPF cpfValidado;

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

            bf.close();
            return cpfsCadastrados.contains(cpfValidado.getCpfNumeros());
        } catch (Exception e) {
            e.printStackTrace();
            mostraAvisoTela("Erro generico, o Programa sera finalizado!");
        }

        return false;
    }

    @Override
    public String toString() {
        return "Visitantes [codigoVerificacao=" + codigoVerificacao + ", cpf=" + cpfValidado.getCpfFormatado()
                + ", idade=" + idade + ", valorIngresso=" + valorIngresso + "]";
    }

}
