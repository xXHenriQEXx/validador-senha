package br.com.desafio.validadorsenha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;

/** Dados da requisição de validação. */
@Schema(description = "Dados da requisição de validação de senha")
public class RequisicaoSenha {

    @NotNull(message = "O campo 'senha' é obrigatório.")
    @Schema(description = "Senha a ser validada", example = "AbTp9!fok")
    private String senha;

    public RequisicaoSenha() {}

    public RequisicaoSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
