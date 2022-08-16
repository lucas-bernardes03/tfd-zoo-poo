package com.poo.classes;

import com.poo.exceptions.HorarioInvalidoException;
import lombok.*;

import java.util.regex.Pattern;

@Getter @Setter
@NoArgsConstructor
public class Alimento {

    private String alimento;
    private double quantidadeKilos;
    private double precoPorKilo;

    public Alimento(String alimento, double quantidadeKilos, double precoPorKilo) {
        this.alimento = alimento;
        this.quantidadeKilos = quantidadeKilos;
        this.precoPorKilo = precoPorKilo;
    }

    public double calculaPrecoTotal() {
        return quantidadeKilos * precoPorKilo;
    }

    public String formataHorarioDieta(int hora, int minuto) throws HorarioInvalidoException {
        String pattern = "\\d{2}";
        String horaFormatada = String.valueOf(hora);
        String minutoFormatado = String.valueOf(minuto);

        if (!Pattern.compile(pattern).matcher(horaFormatada).matches() || !Pattern.compile(pattern).matcher(minutoFormatado).matches())
            throw new HorarioInvalidoException();
        if (hora <= 9) horaFormatada = "0" + hora;
        if (minuto <= 9) minutoFormatado = "0" + minuto;

        return horaFormatada + ":" + minutoFormatado;
    }

}
