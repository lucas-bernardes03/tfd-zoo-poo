package com.poo.classes.abstratos;

import com.poo.classes.Funcionario;
import com.poo.classes.Visitantes;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Administracao extends AbstratoFuncionamento {

    private boolean flagIniciouSistema = false;
    private boolean calculouReceitaVisitantes = false;
    private boolean calculouFolhaPagamento = false;

    private static double folhaPagamentoFuncionarios = 0;

    public void executaPrograma() {
        iniciaPrograma();
        realizaLogin();
        mostraMenuInicial();
        trataOpcaoMenuInicial();
        finalizaPrograma();
    }

    // ACHO QUE ISSO AQUI DEVE ESTAR DENTRO DE CADA CLASSE

    public void calculaFolhaPagamento() {
        if (flagIniciouSistema && !calculouFolhaPagamento) {
            Funcionario f = new Funcionario();
            folhaPagamentoFuncionarios += f.folhaPagamentoTotal();
            calculouFolhaPagamento = true;
        }
    }
}
