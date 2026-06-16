package br.com.desafio.validadorsenha.validador.impl;

import br.com.desafio.validadorsenha.validador.ValidadorSenha;
import org.springframework.stereotype.Component;

/** Valida se a senha possui ao menos uma letra maiúscula. */
@Component
public class ValidadorLetraMaiuscula implements ValidadorSenha {

    @Override
    public boolean validar(String senha) {
        if (senha == null) return false;
        return senha.chars().anyMatch(Character::isUpperCase);
    }
}
