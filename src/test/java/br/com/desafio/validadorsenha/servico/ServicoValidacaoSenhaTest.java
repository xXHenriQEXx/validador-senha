package br.com.desafio.validadorsenha.servico;

import br.com.desafio.validadorsenha.validador.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes do serviço de validação com todos os validadores reais. */
@DisplayName("ServicoValidacaoSenha")
class ServicoValidacaoSenhaTest {

    private ServicoValidacaoSenha servico;

    @BeforeEach
    void configurar() {
        servico = new ServicoValidacaoSenha(List.of(
                new ValidadorComprimentoMinimo(),
                new ValidadorDigito(),
                new ValidadorLetraMinuscula(),
                new ValidadorLetraMaiuscula(),
                new ValidadorCaractereEspecial(),
                new ValidadorSemCaracteresRepetidos()
        ));
    }

    @Test
    @DisplayName("Deve retornar falso para senha nula")
    void deveRetornarFalsoParaSenhaNula() {
        assertFalse(servico.eValida(null));
    }

    @ParameterizedTest(name = "Deve retornar falso para: \"{0}\"")
    @ValueSource(strings = {
            "",
            "aa",
            "ab",
            "AAAbbbCc",
            "AbTp9!foo",
            "AbTp9!foA",
            "AbTp9 fok"
    })
    @DisplayName("Deve retornar falso para senhas inválidas do enunciado")
    void deveRetornarFalsoParaSenhasInvalidas(String senha) {
        assertFalse(servico.eValida(senha));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para senha válida (AbTp9!fok)")
    void deveRetornarVerdadeiroParaSenhaValida() {
        assertTrue(servico.eValida("AbTp9!fok"));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para outra senha válida")
    void deveRetornarVerdadeiroParaOutraSenhaValida() {
        assertTrue(servico.eValida("X1y@Zm#Qw"));
    }
}
