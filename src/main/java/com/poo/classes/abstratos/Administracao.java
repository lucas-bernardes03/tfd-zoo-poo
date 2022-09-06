package com.poo.classes.abstratos;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Administracao extends AbstratoFuncionamento {

    public void executaPrograma() {
        iniciaPrograma();
        realizaLogin();
        mostraMenuInicial();
        trataOpcaoMenuInicial();
        finalizaPrograma();
    }
}
