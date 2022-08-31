package com.poo.classes.abstratos;

import com.poo.classes.Funcionario;
import com.poo.classes.Visitantes;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Administracao extends AbstratoFuncionamento {

    private boolean flagIniciouSistema = false;
    private boolean calculouReceitaVisitantes = false;
    private boolean calculouFolhaPagamento = false;

    private static double receitaVisitantes = 0;
    private static double folhaPagamentoFuncionarios = 0;

    public void executaPrograma() {
        iniciaPrograma();
        realizaLogin();
        mostraMenuInicial();
        trataOpcaoMenuInicial();
        finalizaPrograma();
    }

    // ACHO QUE ISSO AQUI DEVE ESTAR DENTRO DE CADA CLASSE
    public void realizaCadastroVisitante(int codigoVerificacao, String cpf, int idade) {
        Visitantes v = new Visitantes(codigoVerificacao, cpf, idade);

        if (flagIniciouSistema && !calculouReceitaVisitantes) {
            receitaVisitantes += v.getReceitaVisitantes();
            calculouReceitaVisitantes = true;
        }

        if (v.cadastrarVisitante()) {
            mostraMsgInformacaoTela("Sucesso! Visitante Cadastrado");
            receitaVisitantes += v.getValorIngresso();
        } else {
            mostraMsgErroTela("Erro inesperado! O programa sera finalizado");
        }
    }

    public void calculaFolhaPagamento() {
        if (flagIniciouSistema && !calculouFolhaPagamento) {
            Funcionario f = new Funcionario();
            folhaPagamentoFuncionarios += f.folhaPagamentoTotal();
            calculouFolhaPagamento = true;
        }
    }
}
