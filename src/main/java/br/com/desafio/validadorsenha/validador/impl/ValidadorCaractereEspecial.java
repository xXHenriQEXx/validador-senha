package br.com.desafio.validadorsenha.validador.impl;

import br.com.desafio.validadorsenha.validador.ValidadorSenha;
import org.springframework.stereotype.Component;

/** Valida se a senha contém ao menos um dos caracteres especiais: {@code !@#$%^&*()-+} */
@Component
public class ValidadorCaractereEspecial implements ValidadorSenha {

    private static final String CARACTERES_ESPECIAIS = "!@#$%^&*()-+";

    @Override
    public boolean validar(String senha) {
        if (senha == null) return false;
        return senha.chars().anyMatch(c -> CARACTERES_ESPECIAIS.indexOf(c) >= 0);
    }
}
