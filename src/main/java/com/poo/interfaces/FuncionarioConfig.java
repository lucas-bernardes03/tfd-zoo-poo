package com.poo.interfaces;

public interface FuncionarioConfig {
    /**
     * Metodo para cadastrar um funcionario
     */
    void cadastrarFuncionario();

    /**
     * Metodo para calcular a folha de pagamento de todos os funcionarios
     *
     * @return O valor da folha de pagamento total
     */
    double folhaPagamentoTotal();
}
