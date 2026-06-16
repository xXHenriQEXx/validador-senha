package br.com.desafio.validadorsenha.validador.impl;

import br.com.desafio.validadorsenha.validador.ValidadorSenha;
import org.springframework.stereotype.Component;

/** Valida se a senha possui ao menos uma letra minúscula. */
@Component
public class ValidadorLetraMinuscula implements ValidadorSenha {

    @Override
    public boolean validar(String senha) {
        if (senha == null) return false;
        return senha.chars().anyMatch(Character::isLowerCase);
    }
}
