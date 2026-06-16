package br.com.desafio.validadorsenha.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/** Resultado da validação da senha. */
@Schema(description = "Resultado da validação da senha")
public class RespostaValidacao {

    @Schema(description = "Indica se a senha é válida", example = "true")
    private boolean valida;

    public RespostaValidacao() {}

    public RespostaValidacao(boolean valida) {
        this.valida = valida;
    }

    public boolean isValida() {
        return valida;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }
}
