package br.com.desafio.validadorsenha.servico;

import br.com.desafio.validadorsenha.validador.ValidadorSenha;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Orquestra a validação aplicando todas as regras registradas.
 * A senha é válida somente se todas as regras foram cumpridas.
 */
@Service
public class ServicoValidacaoSenha {

    private final List<ValidadorSenha> validadores;

    public ServicoValidacaoSenha(List<ValidadorSenha> validadores) {
        this.validadores = validadores;
    }

    /**
     * @param senha a senha a ser avaliada
     * @return {@code true} se todas as regras forem satisfeitas
     */
    public boolean eValida(String senha) {
        if (senha == null) return false;
        return validadores.stream().allMatch(v -> v.validar(senha));
    }
}
