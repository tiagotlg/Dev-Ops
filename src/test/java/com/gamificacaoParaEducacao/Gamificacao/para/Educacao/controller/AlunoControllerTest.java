package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.dto.AlunoDTO;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlunoService alunoService;

    @Test
    void listarDeveRetornarListaDeAlunos() throws Exception {
        List<AlunoDTO> resposta = List.of(new AlunoDTO(1L, "Joao", Collections.emptyList()));

        when(alunoService.listarTodos()).thenReturn(resposta);

        mockMvc.perform(get("/api/alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Joao"));
    }

    @Test
    void buscarPorIdDeveRetornarAlunoQuandoEncontrado() throws Exception {
        AlunoDTO dto = new AlunoDTO(1L, "Joao", Collections.emptyList());
        when(alunoService.buscarPorId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/alunos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Joao"));
    }

    @Test
    void buscarPorIdDeveRetornarNotFoundQuandoNaoExistir() throws Exception {
        when(alunoService.buscarPorId(999L)).thenThrow(new IllegalArgumentException("Aluno nao encontrado"));

        mockMvc.perform(get("/api/alunos/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void criarDeveRetornarAlunoCriado() throws Exception {
        AlunoDTO entrada = new AlunoDTO(null, "Novo", Collections.emptyList());
        AlunoDTO saida = new AlunoDTO(10L, "Novo", Collections.emptyList());

        when(alunoService.criar(any(AlunoDTO.class))).thenReturn(saida);

        mockMvc.perform(post("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nome").value("Novo"));
    }

    @Test
    void removerDeveRetornarNoContent() throws Exception {
        doNothing().when(alunoService).remover(1L);

        mockMvc.perform(delete("/api/alunos/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
