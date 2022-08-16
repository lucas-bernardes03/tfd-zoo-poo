package com.poo.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dia extends AbstratoZoologico {

    private List<Visitantes> visitantes = new ArrayList<>();
    private int totalVisitantes = 0;
    private double rendaDiaria = 0;

    public double renda() {
        double rendaTotal = 0;
        for (Visitantes visitante : visitantes) {
            rendaTotal += visitante.valorIngresso();
        }

        return rendaTotal;
    }

    public void logVisitante(int dia, int mes) {
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
            mostraAvisoTela("Erro generico, o Programa sera finalizado!");
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
