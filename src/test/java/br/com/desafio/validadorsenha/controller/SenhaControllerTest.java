package br.com.desafio.validadorsenha.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** Testes de integração HTTP do endpoint de validação de senhas. */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("SenhaController — Testes de Integração")
class SenhaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/api/v1/senhas/validar";

    @Test
    @DisplayName("POST /validar — deve retornar 200 e valida=true para senha válida")
    void deveRetornar200ComValidadeTrueParaSenhaValida() throws Exception {
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senha\": \"AbTp9!fok\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valida").value(true));
    }

    @Test
    @DisplayName("POST /validar — deve retornar 200 e valida=false para senha inválida")
    void deveRetornar200ComValidadeFalseParaSenhaInvalida() throws Exception {
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senha\": \"AbTp9!foA\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valida").value(false));
    }

    @Test
    @DisplayName("POST /validar — deve retornar 400 quando campo 'senha' é nulo")
    void deveRetornar400QuandoSenhaENula() throws Exception {
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senha\": null}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").exists());
    }

    @Test
    @DisplayName("POST /validar — deve retornar 400 quando corpo é inválido")
    void deveRetornar400QuandoCorpoEInvalido() throws Exception {
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /validar — deve retornar 200 e valida=false para senha com espaço")
    void deveRetornar200ComValidadeFalseParaSenhaComEspaco() throws Exception {
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senha\": \"AbTp9 fok\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valida").value(false));
    }
}
