package br.com.desafio.validadorsenha.validador.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ValidadorSemCaracteresRepetidos")
class ValidadorSemCaracteresRepetidosTest {

    private ValidadorSemCaracteresRepetidos validador;

    @BeforeEach
    void configurar() {
        validador = new ValidadorSemCaracteresRepetidos();
    }

    @Test
    @DisplayName("Deve retornar falso para senha nula")
    void deveRetornarFalsoParaSenhaNula() {
        assertFalse(validador.validar(null));
    }

    @Test
    @DisplayName("Deve retornar falso para senha com caractere repetido")
    void deveRetornarFalsoParaSenhaComRepetido() {
        assertFalse(validador.validar("AbTp9!foA")); // 'A' repetido
    }

    @Test
    @DisplayName("Deve retornar falso para senha com espaço em branco")
    void deveRetornarFalsoParaSenhaComEspaco() {
        assertFalse(validador.validar("AbTp9 fok"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para senha com todos os caracteres únicos")
    void deveRetornarVerdadeiroParaSenhaSemRepetidos() {
        assertTrue(validador.validar("AbTp9!fok"));
    }

    @Test
    @DisplayName("Deve distinguir maiúsculas de minúsculas (case-sensitive)")
    void deveSerCaseSensitive() {
        // 'a' e 'A' são caracteres distintos
        assertTrue(validador.validar("aAbBcC1!X"));
    }
}
