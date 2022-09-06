package com.poo.interfaces;

public interface FuncionamentoConfig {
    /**
     * Metodo para verificar se o programa sera iniciado.
     */
    void iniciaPrograma();

    /**
     * Metodo para checar as credencias e realizar login.
     */
    void realizaLogin();

    /**
     * Metodo para mostrar as opçoes do programa de gerenciamento do zoologico.
     */
    void mostraMenuInicial();

    /**
     * Metodo para determinar quais funçoes serao chamadas.
     */
    void trataOpcaoMenuInicial();

    /**
     * Metodo para finalizar o programa.
     */
    void finalizaPrograma();
}
