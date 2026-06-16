package br.com.desafio.validadorsenha.validador.impl;

import br.com.desafio.validadorsenha.validador.ValidadorSenha;
import org.springframework.stereotype.Component;

/**
 * Valida se a senha não possui caracteres repetidos nem espaços em branco.
 * A comparação é case-sensitive: 'A' e 'a' são considerados distintos.
 */
@Component
public class ValidadorSemCaracteresRepetidos implements ValidadorSenha {

    @Override
    public boolean validar(String senha) {
        if (senha == null) return false;
        if (senha.contains(" ")) return false;
        return senha.chars().distinct().count() == senha.length();
    }
}
