package com.poo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.poo.classes.Visitantes;

public class Testes {
    Visitantes v = new Visitantes();
    
    @Test
    public void receita(){
       assertEquals(1830.1, v.getReceitaVisitantes(), 0.1);

    }
}
