package com.poo.classes;

import com.poo.classes.abstratos.AbstratoZoologico;
import com.poo.exceptions.CPFInvalidoException;
import com.poo.interfaces.FuncionarioConfig;
import com.poo.validacoes.CPF;
import lombok.NoArgsConstructor;

import java.io.*;

@NoArgsConstructor
public class Funcionario extends AbstratoZoologico implements FuncionarioConfig {

    private String nome;
    private String cpf;
    private double salario;
    private CPF cpfValidado;

    public Funcionario(String nome, String cpf, double salario) {
        try {
            this.nome = nome;
            this.cpf = cpf;
            this.salario = salario;
            cpfValidado = new CPF(this.cpf);
        } catch (CPFInvalidoException e) {
            mostraMsgAvisoTela("O CPF informado nao e valido!");
        }
    }

    @Override
    public void cadastrarFuncionario() {
        try {
            File myFile = new File("src/main/java/com/poo/arquivos/funcionarios.csv");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(myFile, true), "UTF-8"));
            StringBuilder sb = new StringBuilder();

            sb.append(nome + ",")
                    .append(cpfValidado.getCpfNumeros() + ",")
                    .append(salario);

            writer.println(sb.toString());
            writer.close();
        } catch (Exception e) {
            mostraMsgErroTela("Erro generico! O programa sera finalizado");
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
                folhaPagamento += Double.parseDouble(campos[2]);
                linha = bf.readLine();
            }

            bf.close();
            return folhaPagamento;
        } catch (Exception e) {
            e.printStackTrace();
            mostraMsgErroTela("Erro generico, o programa sera finalizado!");
            return 0;
        }
    }
}
