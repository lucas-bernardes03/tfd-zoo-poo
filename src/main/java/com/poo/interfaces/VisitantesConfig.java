package com.poo.interfaces;

public interface VisitantesConfig {
    /**
     * Metodo para calcular o valor do ingresso do Visitante
     *
     * @return O valor do ingreso
     */
    double valorIngresso();

    /**
     * Metodo para checar se um Visitante e socio ou nao
     *
     * @return True: se for socio | False: se nao for socio cadastrado
     */
    boolean validarSocio();

    /**
     * Metodo responsavel por fazer cadastro do Visitante
     */
    void realizaCadastroVisitante();

    /**
     * Metodo para calcular o valor arrecadado com os ingressos dos Visitantes
     *
     * @return Valor da receita total
     */
    double getReceitaVisitantes();
}
