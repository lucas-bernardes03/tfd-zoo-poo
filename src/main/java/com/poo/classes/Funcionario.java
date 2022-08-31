package com.poo.classes;

import com.poo.classes.abstratos.AbstratoZoologico;
import com.poo.exceptions.CPFInvalidoException;
import com.poo.interfaces.FuncionarioConfig;
import com.poo.validacoes.CPF;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;

@NoArgsConstructor
public class Funcionario extends AbstratoZoologico implements FuncionarioConfig {

    private String nome;
    private CPF cpf;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cpf, String cargo, double salario) {
        try {
            this.nome = nome;
            this.cpf = new CPF(cpf);
            this.cargo = cargo;
            this.salario = salario;
        } catch (CPFInvalidoException e) {
            mostraAvisoTela("O CPF informado nao e valido!");
        }
    }

    @Override
    public double folhaPagamentoTotal() {
        try {
            FileReader pathFuncionarios =  new FileReader("src/main/java/com/poo/arquivos/funcionarios.csv");
            BufferedReader bf = new BufferedReader(pathFuncionarios);
            StringBuilder sb = new StringBuilder();
            double folhaPagamento = 0;

            String linha = bf.readLine();
            while (linha != null) {
                if (linha.contains("nome,cpf")) {
                    linha = bf.readLine();
                    continue;
                }
                String[] campos = linha.split(",");
                folhaPagamento = Double.parseDouble(campos[3]);
                linha = bf.readLine();
            }

            bf.close();
            return folhaPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            mensagemParaExceptionTela("Erro generico, o programa sera finalizado!");
            return 0;
        }
    }

    @Override
    public double folhaPagamentoPorCargo(String cargo) {
        try {
            FileReader pathFuncionarios =  new FileReader("src/main/java/com/poo/arquivos/funcionarios.csv");
            BufferedReader bf = new BufferedReader(pathFuncionarios);
            StringBuilder sb = new StringBuilder();
            double folhaPagamento = 0;

            String linha = bf.readLine();
            while (linha != null) {
                if (linha.contains("nome,cpf")) {
                    linha = bf.readLine();
                    continue;
                }
                String[] campos = linha.split(",");
                if (cargo.equals(campos[2])) folhaPagamento = Double.parseDouble(campos[3]);
                linha = bf.readLine();
            }

            bf.close();
            return folhaPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            mensagemParaExceptionTela("Erro generico, o programa sera finalizado!");
            return 0;
        }
    }
}
