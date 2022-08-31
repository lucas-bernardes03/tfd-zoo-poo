package com.poo.classes;

import com.poo.classes.abstratos.AbstratoZoologico;
import com.poo.interfaces.DiaConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dia extends AbstratoZoologico implements DiaConfig {

    private List<Visitantes> visitantes = new ArrayList<>();
    private int dia;
    private int mes;
    private int totalVisitantes = 0;
    private double rendaDiaria = 0;

    public Dia(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    @Override
    public double calculaRenda() {
        double rendaTotal = 0;
        for (Visitantes visitante : visitantes) {
            rendaTotal += visitante.valorIngresso();
        }

        return rendaTotal;
    }

    @Override
    public void mostrarVisitantes() {
        try {
            FileReader pathVisitantes =  new FileReader("src/main/java/com/poo/arquivos/visitantes.csv");
            BufferedReader bf = new BufferedReader(pathVisitantes);
            StringBuilder sb = new StringBuilder();

            String linha = bf.readLine();
            while (linha != null) {
                if (linha.contains("dia,mes")) {
                    linha = bf.readLine();
                    continue;
                }
                String[] campos = linha.split(",");
                obtemInfos(campos, dia, mes);
                linha = bf.readLine();
            }

            sb.append("Dia: " + dia + " - " + "Mes: " + mes + '\n');
            sb.append("Total de visitantes: " + totalVisitantes + '\n');
            sb.append("Total renda diaria: " +  rendaDiaria + '\n');
            // imprimir infos sb

            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostraMsgErroTela("Erro generico, o programa sera finalizado!");
        }
    }

    private void obtemInfos(String[] camposLinha, int diaProcurado, int mesProcurado) {
        String id = camposLinha[0];
        String dia = id.substring(6, 7);
        String mes = id.substring(4, 5);

        if (dia.equals(String.valueOf(diaProcurado))) {
            if (mes.equals(String.valueOf(mesProcurado))) {
                totalVisitantes++;
                rendaDiaria += Double.parseDouble(camposLinha[5]);
            }
        }
    }
}
