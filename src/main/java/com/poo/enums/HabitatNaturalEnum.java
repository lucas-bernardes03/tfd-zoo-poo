package com.poo.enums;

public enum HabitatNaturalEnum {
    HABITAT_TERRESTE("Habitat Terrestre", 1000),
    HABITAT_AQUATICO("Habitat Aquatico", 2000);

    String nome;
    double tamanhoTotal;

    HabitatNaturalEnum(String nome, double tamanhoTotal) {
        this.nome = nome;
        this.tamanhoTotal = tamanhoTotal;
    }
}
