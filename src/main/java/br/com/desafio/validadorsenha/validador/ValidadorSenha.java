package br.com.desafio.validadorsenha.validador;

/**
 * Regra de validação de senha.
 * Cada implementação encapsula um único critério (padrão Strategy).
 */
public interface ValidadorSenha {

    /**
     * @param senha a senha a ser validada
     * @return {@code true} se a regra for satisfeita; {@code false} caso contrário
     */
    boolean validar(String senha);
}
