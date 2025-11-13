package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.service;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.dto.AlunoDTO;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.dto.RecompensaDTO;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository.AlunoRepository;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Aluno;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Recompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.TipoRecompensa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional(readOnly = true)
    public List<AlunoDTO> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoDTO criar(AlunoDTO dto) {
        Aluno entity = toEntity(dto);
        Aluno salvo = alunoRepository.save(entity);
        return toDTO(salvo);
    }

    @Transactional(readOnly = true)
    public AlunoDTO buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    @Transactional
    public void remover(Long id) {
        alunoRepository.deleteById(id);
    }

    private AlunoDTO toDTO(Aluno aluno) {
        List<RecompensaDTO> recompensasDTO = Collections.emptyList();
        if (aluno.getRecompensas() != null) {
            recompensasDTO = aluno.getRecompensas().stream()
                    .map(r -> new RecompensaDTO(
                            r.getId(),
                            r.getTipo() != null ? r.getTipo().name() : null,
                            r.getCurso()
                    ))
                    .collect(Collectors.toList());
        }

        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                recompensasDTO
        );
    }

    private Aluno toEntity(AlunoDTO dto) {
        List<Recompensa> recompensas = Collections.emptyList();
        if (dto.getRecompensas() != null) {
            recompensas = dto.getRecompensas().stream()
                    .map(r -> {
                        TipoRecompensa tipo = null;
                        if (r.getTipo() != null) {
                            try {
                                tipo = TipoRecompensa.valueOf(r.getTipo());
                            } catch (IllegalArgumentException ex) {
                                // tipo inválido, mantém null
                            }
                        }
                        Recompensa recompensa = new Recompensa(tipo, r.getCurso());
                        recompensa.setId(r.getId());
                        return recompensa;
                    })
                    .collect(Collectors.toList());
        }

        Aluno aluno = new Aluno(dto.getNome(), recompensas);
        aluno.setId(dto.getId());
        return aluno;
    }
}
