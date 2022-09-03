package com.poo.classes;

import com.poo.classes.abstratos.AbstratoZoologico;
import com.poo.exceptions.CPFInvalidoException;
import com.poo.interfaces.VisitantesConfig;
import com.poo.validacoes.CPF;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Visitantes extends AbstratoZoologico implements VisitantesConfig {

    @Getter private Long codigoVerificacao;
    @Getter private String cpf;
    @Getter private int idade;
    @Getter private double valorIngresso;
    private CPF cpfValidado;
    private static double receitaVisitantes = 0;

    /**
     * Construtor obrigatorio para qualquer cadastro de visitantes, para que as informaçoes iniciais sejam coletadas.
     * @param codigoVerificacao Codigo que identifica o visitante, dia, mes e ano.
     * @param cpf CPF do visitante.
     * @param idade Idade em anos do visitante.
     */
    public Visitantes(Long codigoVerificacao, String cpf, int idade) {
        try {
            this.codigoVerificacao = codigoVerificacao;
            this.cpf = cpf;
            this.idade = idade;
            cpfValidado = new CPF(this.cpf);
            valorIngresso = valorIngresso();
        } catch (CPFInvalidoException exception) {
            mostraMsgAvisoTela("O CPF informado nao e valido!");
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
            mostraMsgErroTela("Erro generico, o programa sera finalizado!");
        }

        return false;
    }

    @Override
    public void realizaCadastroVisitante() {
        if (cadastrarVisitante()) {
            mostraMsgInformacaoTela("Sucesso! Visitante Cadastrado");
        } else {
            mostraMsgErroTela("Erro inesperado! O programa sera finalizado");
        }
    }

    private boolean cadastrarVisitante() {
        try {
            //codigo - 20220815000
            String dia = String.valueOf(codigoVerificacao).substring(6, 8);
            String mes = String.valueOf(codigoVerificacao).substring(4, 6);

            OutputStream os = new FileOutputStream("src/main/java/com/poo/arquivos/visitantes.csv");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.println();
            StringBuilder sb = new StringBuilder();

            sb.append(dia + ",")
                    .append(mes + ",")
                    .append(codigoVerificacao + ",")
                    .append(cpfValidado.getCpfNumeros() + ",")
                    .append(idade + ",")
                    .append(valorIngresso + "\n");

            writer.write(sb.toString());
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public double getReceitaVisitantes() {
        try {
            double receitaTotal = 0;
            FileReader pathVisitantes =  new FileReader("src/main/java/com/poo/arquivos/visitantes.csv");
            BufferedReader bf = new BufferedReader(pathVisitantes);

            String linha = bf.readLine();
            while (linha != null) {
                if (linha.contains("dia,mes")) {
                    linha = bf.readLine();
                    continue;
                }

                String[] campos = linha.split(",");
                receitaTotal += Double.parseDouble(campos[5]);
                linha = bf.readLine();
            }

            bf.close();
            return receitaTotal;
        } catch (Exception e) {
            mostraMsgErroTela("Erro generico, o programa sera finalizado!");
            return 0;
        }
    }
}
