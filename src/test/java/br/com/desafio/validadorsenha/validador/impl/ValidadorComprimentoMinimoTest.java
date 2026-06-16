package br.com.desafio.validadorsenha.validador.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ValidadorComprimentoMinimo")
class ValidadorComprimentoMinimoTest {

    private ValidadorComprimentoMinimo validador;

    @BeforeEach
    void configurar() {
        validador = new ValidadorComprimentoMinimo();
    }

    @Test
    @DisplayName("Deve retornar falso para senha nula")
    void deveRetornarFalsoParaSenhaNula() {
        assertFalse(validador.validar(null));
    }

    @Test
    @DisplayName("Deve retornar falso para senha vazia")
    void deveRetornarFalsoParaSenhaVazia() {
        assertFalse(validador.validar(""));
    }

    @Test
    @DisplayName("Deve retornar falso para senha com menos de 9 caracteres")
    void deveRetornarFalsoParaSenhaCurta() {
        assertFalse(validador.validar("AbTp9!fo"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para senha com exatamente 9 caracteres")
    void deveRetornarVerdadeiroParaSenhaComNoveCaracteres() {
        assertTrue(validador.validar("AbTp9!fok"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para senha com mais de 9 caracteres")
    void deveRetornarVerdadeiroParaSenhaLonga() {
        assertTrue(validador.validar("AbTp9!fokXYZ"));
    }
}
