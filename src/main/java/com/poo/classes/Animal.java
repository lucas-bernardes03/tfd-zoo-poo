package com.poo.classes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"dieta"})
public class Animal {
    private String nome;
    private String especie;
    private Double peso;
    private Integer idade;

    //<Alimento,Horário> - linked hash map ordena por ordem de adicao
    private Map<String,String> dieta = new LinkedHashMap<>();

    public Animal(String nome, String especie, Double peso, Integer idade) {
        this.nome = nome;
        this.especie = especie;
        this.peso = peso;
        this.idade = idade;
    }

}
