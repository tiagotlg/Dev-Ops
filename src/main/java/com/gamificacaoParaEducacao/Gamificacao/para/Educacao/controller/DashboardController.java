package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.controller;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.TipoRecompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/alunos-com-criptomoeda")
    public long contarAlunosComCriptomoeda() {
        return dashboardService.contarAlunosComCriptomoeda();
    }

    @GetMapping("/ranking")
    public Map<String, Long> rankingCursosPorTipo(@RequestParam("tipo") TipoRecompensa tipo) {
        return dashboardService.rankingCursosPorTipo(tipo);
    }
}
