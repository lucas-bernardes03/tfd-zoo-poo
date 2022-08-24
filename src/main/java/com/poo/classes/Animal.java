package com.poo.classes;

import com.poo.exceptions.HorarioInvalidoException;

import lombok.*;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
public class Animal extends AbstratoZoologico {
    @Getter private String nome;
    @Getter private String especie;
    @Getter private Double peso;
    @Getter private Integer idade;
    @Getter private double precoTotalDieta = 0;

    Map<String, Alimento> dieta = new LinkedHashMap<>();

    public Animal(String nome, String especie, Double peso, Integer idade) {
        this.nome = nome;
        this.especie = especie;
        this.peso = peso;
        this.idade = idade;
    }

    public void adicionarAlimentacao(int hora, int minuto, Alimento alimento) {
        try {
            dieta.put(alimento.formataHorarioDieta(hora, minuto), alimento);
            precoTotalDieta += alimento.calculaPrecoTotal();
        } catch (HorarioInvalidoException exception) {
            mostraAvisoTela("O horario digitado nao e valido!");
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
