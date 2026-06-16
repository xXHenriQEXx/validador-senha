package br.com.desafio.validadorsenha.controller;

import br.com.desafio.validadorsenha.dto.RequisicaoSenha;
import br.com.desafio.validadorsenha.dto.RespostaValidacao;
import br.com.desafio.validadorsenha.servico.ServicoValidacaoSenha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint REST para validação de senhas.
 * Usa POST para evitar exposição da senha em logs e histórico de navegadores.
 */
@RestController
@RequestMapping("/api/v1/senhas")
@Tag(name = "Senhas", description = "Validação de senhas")
public class SenhaController {

    private final ServicoValidacaoSenha servicoValidacaoSenha;

    public SenhaController(ServicoValidacaoSenha servicoValidacaoSenha) {
        this.servicoValidacaoSenha = servicoValidacaoSenha;
    }

    @Operation(summary = "Valida uma senha", description = "Retorna true se a senha atender a todos os critérios de segurança.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validação realizada",
                    content = @Content(schema = @Schema(implementation = RespostaValidacao.class))),
            @ApiResponse(responseCode = "400", description = "Campo 'senha' ausente ou nulo", content = @Content)
    })
    @PostMapping("/validar")
    public ResponseEntity<RespostaValidacao> validar(@Valid @RequestBody RequisicaoSenha requisicao) {
        boolean valida = servicoValidacaoSenha.eValida(requisicao.getSenha());
        return ResponseEntity.ok(new RespostaValidacao(valida));
    }
}
