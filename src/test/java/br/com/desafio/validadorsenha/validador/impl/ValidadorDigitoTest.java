package br.com.desafio.validadorsenha.validador.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ValidadorDigito")
class ValidadorDigitoTest {

    private ValidadorDigito validador;

    @BeforeEach
    void configurar() {
        validador = new ValidadorDigito();
    }

    @Test
    @DisplayName("Deve retornar falso para senha nula")
    void deveRetornarFalsoParaSenhaNula() {
        assertFalse(validador.validar(null));
    }

    @Test
    @DisplayName("Deve retornar falso para senha sem dígitos")
    void deveRetornarFalsoParaSenhaSemDigito() {
        assertFalse(validador.validar("AbTp!fok"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para senha com ao menos um dígito")
    void deveRetornarVerdadeiroParaSenhaComDigito() {
        assertTrue(validador.validar("AbTp9!fok"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para senha com vários dígitos")
    void deveRetornarVerdadeiroParaSenhaComVariosDigitos() {
        assertTrue(validador.validar("AbT123!fok"));
    }
}
