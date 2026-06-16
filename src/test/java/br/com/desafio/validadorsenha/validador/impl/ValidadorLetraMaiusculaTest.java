package br.com.desafio.validadorsenha.validador.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ValidadorLetraMaiuscula")
class ValidadorLetraMaiusculaTest {

    private ValidadorLetraMaiuscula validador;

    @BeforeEach
    void configurar() {
        validador = new ValidadorLetraMaiuscula();
    }

    @Test
    @DisplayName("Deve retornar falso para senha nula")
    void deveRetornarFalsoParaSenhaNula() {
        assertFalse(validador.validar(null));
    }

    @Test
    @DisplayName("Deve retornar falso para senha sem letra maiúscula")
    void deveRetornarFalsoParaSenhaSemMaiuscula() {
        assertFalse(validador.validar("abtp9!fok"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para senha com ao menos uma letra maiúscula")
    void deveRetornarVerdadeiroParaSenhaComMaiuscula() {
        assertTrue(validador.validar("AbTp9!fok"));
    }
}
