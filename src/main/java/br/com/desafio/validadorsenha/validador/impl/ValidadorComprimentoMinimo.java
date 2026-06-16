package br.com.desafio.validadorsenha.validador.impl;

import br.com.desafio.validadorsenha.validador.ValidadorSenha;
import org.springframework.stereotype.Component;

/** Valida se a senha possui ao menos 9 caracteres. */
@Component
public class ValidadorComprimentoMinimo implements ValidadorSenha {

    private static final int COMPRIMENTO_MINIMO = 9;

    @Override
    public boolean validar(String senha) {
        return senha != null && senha.length() >= COMPRIMENTO_MINIMO;
    }
}
