package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {
    @Test
    void testSetNumeroValido(){
        final Conta instance = new Conta();
        final String esperado = "12345-6";
        instance.setNumero(esperado);
        final String obtido = instance.getNumero();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testSetNumeroInvalidoNaoArmazena(){
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));
        final String obtido = instance.getNumero();
        assertNotEquals(invalido, obtido);
    }
    
    @Test 
    void testInstanciaPadraoPoupanca(){
        final Conta instance = new Conta();
        assertTrue(instance.isPoupanca());
    }
    
    @Test
    void testSetLimitContaEspecial(){
        final Conta instance = new Conta();
        instance.setEspecial(true);
        final double esperado = 1000;
        instance.setLimite(esperado);
        final double obtido = instance.getLimite();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testSetLimitContNaoEspecial(){
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
    }
    
    @Test
    void testHistoricoNotNull(){
        final Conta instance = new Conta();
        assertNotNull(instance.getMovimentacoes());
    }
    
    @Test
    void testGetSaldo(){
        final double limite = 500;
        final double esperado = limite;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido);
   }
    
    @Test
    void testDepositoDinheiro(){
        final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.001);
    }
    @Test
    void testAddMovimentacaoCredito() {
        Conta instance = new Conta();
        Movimentacao mov = new Movimentacao(instance);
        mov.setConfirmada(true);
        mov.setTipo('C');
        final double esperado = 100.50;
        mov.setValor(esperado);
        instance.addMovimentacao(mov);
        assertEquals(esperado, instance.getSaldoTotal());
    }
    @Test
    void testAddMovimentacaoDebito() {
        Conta instance = new Conta();
        Movimentacao mov = new Movimentacao(instance);
        mov.setConfirmada(true);
        mov.setTipo('D');
        final double valor = 100.50;
        final double esperado = -valor;
        mov.setValor(valor);
        instance.addMovimentacao(mov);
        assertEquals(esperado, instance.getSaldoTotal());
    }
    
    @Test 
    void testGetSaldoTotal(){
        Conta instance = new Conta();
        final double esperado = 100;
        instance.setLimite(esperado);
        assertEquals(esperado, instance.getSaldoTotal());
    }
}
