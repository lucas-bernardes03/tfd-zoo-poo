package com.poo.programas;

import com.poo.classes.Alimento;
import com.poo.classes.Animal;
import com.poo.classes.Funcionario;
import com.poo.classes.Visitantes;
import com.poo.classes.abstratos.Administracao;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTeste {

    private static Administracao adm = new Administracao();

    @BeforeClass
    public static void inicializaClass() {
        adm.mostraMsgInformacaoTela("Iniciou série de testes para Zoologico POO");
    }

    @Test
    public void primeiroTeste() {
        Visitantes v = new Visitantes(20220906001L, "08137010157", 19);
        assertEquals(30.0,v.valorIngresso(),0.1);
        assertTrue(v.validarSocio());
        assertTrue(v.cadastrarVisitante());
        adm.mostraMsgInformacaoTela("Passou TESTE 1!");
    }

    @Test
    public void segundoTeste() {
        Animal a = new Animal("Teste", "Apenas teste", 10.0, 1);
//        a.cadastrarAnimal();
//        a.adicionarAlimentacao(19, 30, new Alimento("Macas", 1.0, 2.5));
        adm.mostraMsgInformacaoTela("Passou TESTE 2!");
    }

    @Test
    public void terceiroTeste() {
        Funcionario f = new Funcionario("Felipe Teste", "08137010157", 0.0);
        assertEquals(25000.0, f.folhaPagamentoTotal(), 0.1);
//        f.cadastrarFuncionario();
        adm.mostraMsgInformacaoTela("Passou TESTE 4!");
    }

    @AfterClass
    public static void finalizaClass() {
        adm.mostraMsgInformacaoTela("Finalizou série de testes para Zoologico POO");
    }
}
