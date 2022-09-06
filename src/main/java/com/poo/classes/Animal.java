package com.poo.classes;

import com.poo.classes.abstratos.AbstratoZoologico;
import com.poo.exceptions.HorarioInvalidoException;

import lombok.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class Animal extends AbstratoZoologico {
    @Getter private String nome;
    @Getter private String especie;
    @Getter private Double peso;
    @Getter private Integer idade;
    @Getter private double precoTotalDieta = 0;

    Map<String, Alimento> dieta = new LinkedHashMap<>();
    List<String> animais = new ArrayList<>();

    public Animal(String nome, String especie, Double peso, Integer idade) {
        this.nome = nome;
        this.especie = especie;
        this.peso = peso;
        this.idade = idade;
    }

    /**
     * Metodo para realizar o cadastro do Aninal no seu arquivo.
     */
    public void cadastrarAnimal(){
        File myFile = new File("src/main/java/com/poo/arquivos/animais.csv");
        PrintWriter writer;
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(myFile, true), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            
            sb.append(nome + ",")
            .append(especie + ",")
            .append(peso + ",")
            .append(idade + ",");
            
            writer.println(sb.toString());
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para adicionar um alimento no seu arquivo.
     *
     * @param hora Hora da alimentaçao
     * @param minuto Minuto da alimentaçao
     * @param alimento Objeto Alimento
     */
    public void adicionarAlimentacao(int hora, int minuto, Alimento alimento) {
        File myFile = new File("src/main/java/com/poo/arquivos/alimentos.csv");
        PrintWriter writer;
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(myFile, true), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            
            sb.append(nome + ", ")
            .append(alimento.formataHorarioDieta(hora, minuto) + ",")
            .append(alimento.getAlimento() + ",")
            .append(alimento.getQuantidadeKilos() + ",")
            .append(alimento.getPrecoPorKilo() + ",");
            
            writer.println(sb.toString());
            writer.close();
            
            dieta.put(alimento.formataHorarioDieta(hora, minuto), alimento);
            precoTotalDieta += alimento.calculaPrecoTotal();
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (HorarioInvalidoException exception) {
            mostraMsgAvisoTela("O horario digitado nao e valido!");
        }
    }

    /**
     * Metodo para calcular os gastos com o alimento.
     *
     * @return O gasto
     */
    public double gastosTotais(){
        try {
            double gastosTotal = 0;
            FileReader pathAlimentos =  new FileReader("src/main/java/com/poo/arquivos/alimentos.csv");
            BufferedReader bf = new BufferedReader(pathAlimentos);

            String linha = bf.readLine();
            while (linha != null) {
                if (linha.contains("nome_animal")) {
                    linha = bf.readLine();
                    continue;
                }

                String[] campos = linha.split(",");
                gastosTotal += Double.parseDouble(campos[3]) * Double.parseDouble(campos[4]);
                linha = bf.readLine();
            }

            bf.close();
            return gastosTotal;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void deletarAnimal(String nome){
        try {
            FileReader pathAnimais =  new FileReader("src/main/java/com/poo/arquivos/animais.csv");
            BufferedReader bf = new BufferedReader(pathAnimais);

            String linha = bf.readLine();
            while (linha != null) {
                if(!linha.contains(nome)) animais.add(linha);
                linha = bf.readLine();
            }

            File myFile = new File("src/main/java/com/poo/arquivos/animais.csv");
            PrintWriter writer;

            try {
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(myFile, false), "UTF-8"));
                for(String s : animais) writer.println(s);
                writer.close();
            } catch (Exception e){
                e.printStackTrace();
            }

            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("-- Animal cadastrado ---" + '\n');
        sb.append("Nome: " + nome + '\n');
        sb.append("Especie: " + especie + '\n');
        sb.append("Peso: " + peso + '\n');
        sb.append("Idade: " + idade + "\n\n");

        sb.append("--- Dieta cadastrada ---\n");
        sb.append("Horario - Refeicao\n");
        for (String refeicao : dieta.keySet()) {
            sb.append(refeicao).append(" - ").append(dieta.get(refeicao)).append("\n");
        }

        return sb.toString();
    }
}
