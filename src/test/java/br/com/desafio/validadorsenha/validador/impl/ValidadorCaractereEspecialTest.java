package br.com.desafio.validadorsenha.validador.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ValidadorCaractereEspecial")
class ValidadorCaractereEspecialTest {

    private ValidadorCaractereEspecial validador;

    @BeforeEach
    void configurar() {
        validador = new ValidadorCaractereEspecial();
    }

    @Test
    @DisplayName("Deve retornar falso para senha nula")
    void deveRetornarFalsoParaSenhaNula() {
        assertFalse(validador.validar(null));
    }

    @Test
    @DisplayName("Deve retornar falso para senha sem caractere especial")
    void deveRetornarFalsoParaSenhaSemEspecial() {
        assertFalse(validador.validar("AbTp9fok1"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para cada caractere especial permitido")
    void deveRetornarVerdadeiroParaCadaCaractereEspecial() {
        String[] especiais = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "+"};
        for (String especial : especiais) {
            assertTrue(validador.validar("AbTp9fok" + especial),
                    "Falhou para o caractere especial: " + especial);
        }
    }

    @Test
    @DisplayName("Deve retornar falso para caractere especial não permitido (ex: ponto)")
    void deveRetornarFalsoParaCaractereEspecialNaoPermitido() {
        assertFalse(validador.validar("AbTp9fok."));
    }
}
