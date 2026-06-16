package br.com.desafio.validadorsenha.validador.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ValidadorLetraMinuscula")
class ValidadorLetraMinusculaTest {

    private ValidadorLetraMinuscula validador;

    @BeforeEach
    void configurar() {
        validador = new ValidadorLetraMinuscula();
    }

    @Test
    @DisplayName("Deve retornar falso para senha nula")
    void deveRetornarFalsoParaSenhaNula() {
        assertFalse(validador.validar(null));
    }

    @Test
    @DisplayName("Deve retornar falso para senha sem letra minúscula")
    void deveRetornarFalsoParaSenhaSemMinuscula() {
        assertFalse(validador.validar("ABTP9!FOK"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para senha com ao menos uma letra minúscula")
    void deveRetornarVerdadeiroParaSenhaComMinuscula() {
        assertTrue(validador.validar("AbTp9!FOK"));
    }
}
