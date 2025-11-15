package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.controller;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.TipoRecompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DashboardService dashboardService;

    @Test
    void contarAlunosComCriptomoedaEndpoint() throws Exception {
        when(dashboardService.contarAlunosComCriptomoeda()).thenReturn(5L);

        mockMvc.perform(get("/api/dashboard/alunos-com-criptomoeda"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void rankingCursosPorTipoEndpoint() throws Exception {
        Map<String, Long> ranking = Map.of("Curso1", 2L, "Curso2", 1L);
        when(dashboardService.rankingCursosPorTipo(TipoRecompensa.CRIPTOMOEDA)).thenReturn(ranking);

        mockMvc.perform(get("/api/dashboard/ranking")
                        .param("tipo", "CRIPTOMOEDA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Curso1").value(2))
                .andExpect(jsonPath("$.Curso2").value(1));
    }
}
