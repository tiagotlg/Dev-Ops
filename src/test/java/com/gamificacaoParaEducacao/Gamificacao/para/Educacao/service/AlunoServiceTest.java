package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.service;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Aluno;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Recompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.TipoRecompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.dto.AlunoDTO;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.dto.RecompensaDTO;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void listarTodosDeveRetornarDtosMapeados() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");
        Recompensa recompensa = new Recompensa(TipoRecompensa.CURSO_EXTRA, "Spring Boot");
        recompensa.setId(10L);
        aluno.setRecompensas(List.of(recompensa));

        when(alunoRepository.findAll()).thenReturn(List.of(aluno));

        List<AlunoDTO> dtos = alunoService.listarTodos();

        assertEquals(1, dtos.size());
        AlunoDTO dto = dtos.get(0);
        assertEquals(1L, dto.getId());
        assertEquals("João", dto.getNome());
        assertEquals(1, dto.getRecompensas().size());
        RecompensaDTO rDto = dto.getRecompensas().get(0);
        assertEquals(10L, rDto.getId());
        assertEquals("CURSO_EXTRA", rDto.getTipo());
        assertEquals("Spring Boot", rDto.getCurso());
    }

    @Test
    void criarDeveSalvarEConverterParaDto() {
        RecompensaDTO rDto = new RecompensaDTO(null, "CRIPTOMOEDA", "Java");
        AlunoDTO entrada = new AlunoDTO(null, "Novo", List.of(rDto));

        when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation -> {
            Aluno a = invocation.getArgument(0);
            a.setId(42L);
            if (a.getRecompensas() != null && !a.getRecompensas().isEmpty()) {
                a.getRecompensas().get(0).setId(100L);
            }
            return a;
        });

        AlunoDTO resultado = alunoService.criar(entrada);

        assertNotNull(resultado.getId());
        assertEquals("Novo", resultado.getNome());
        assertEquals(1, resultado.getRecompensas().size());
        RecompensaDTO rResultado = resultado.getRecompensas().get(0);
        assertEquals("CRIPTOMOEDA", rResultado.getTipo());
        assertEquals("Java", rResultado.getCurso());
    }

    @Test
    void criarAlunoSemRecompensasDeveFuncionar() {
        AlunoDTO entrada = new AlunoDTO(null, "Sem recompensas", null);

        when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation -> {
            Aluno a = invocation.getArgument(0);
            a.setId(50L);
            return a;
        });

        AlunoDTO salvo = alunoService.criar(entrada);

        assertEquals(50L, salvo.getId());
        assertNotNull(salvo.getRecompensas());
        assertTrue(salvo.getRecompensas().isEmpty());
    }

    @Test
    void buscarPorIdDeveRetornarDtoQuandoEncontrado() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");
        aluno.setRecompensas(Collections.emptyList());

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        AlunoDTO dto = alunoService.buscarPorId(1L);

        assertEquals(1L, dto.getId());
        assertEquals("João", dto.getNome());
        assertNotNull(dto.getRecompensas());
        assertTrue(dto.getRecompensas().isEmpty());
    }

    @Test
    void buscarPorIdNaoEncontradoDeveLancarExcecao() {
        when(alunoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> alunoService.buscarPorId(99L));
    }

    @Test
    void removerDeveChamarDeleteNoRepositorio() {
        alunoService.remover(1L);

        verify(alunoRepository).deleteById(1L);
    }

    @Test
    void criarDevePermitirTiposDeRecompensaNulosOuInvalidos() {
        RecompensaDTO dtoSemTipo = new RecompensaDTO(null, null, "Curso 1");
        RecompensaDTO dtoTipoInvalido = new RecompensaDTO(null, "TIPO_QUE_NAO_EXISTE", "Curso 2");
        AlunoDTO dto = new AlunoDTO(null, "Teste", List.of(dtoSemTipo, dtoTipoInvalido));

        when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AlunoDTO resultado = alunoService.criar(dto);

        assertEquals("Teste", resultado.getNome());

        ArgumentCaptor<Aluno> captor = ArgumentCaptor.forClass(Aluno.class);
        verify(alunoRepository).save(captor.capture());
        Aluno salvo = captor.getValue();
        assertEquals(2, salvo.getRecompensas().size());
        assertNull(salvo.getRecompensas().get(0).getTipo());
        assertNull(salvo.getRecompensas().get(1).getTipo());
    }
    
    @Test
    void listarTodosComAlunoSemRecompensasDeveRetornarListaVaziaDeRecompensas() {
        Aluno aluno = new Aluno();
        aluno.setId(99L);
        aluno.setNome("Sem recompensas");
        // NÃO seta recompensas -> continua null

        when(alunoRepository.findAll()).thenReturn(List.of(aluno));

        List<AlunoDTO> dtos = alunoService.listarTodos();

        assertEquals(1, dtos.size());
        AlunoDTO dto = dtos.get(0);
        assertEquals(99L, dto.getId());
        assertEquals("Sem recompensas", dto.getNome());

        // toDTO deve devolver lista vazia, não null
        assertNotNull(dto.getRecompensas());
        assertTrue(dto.getRecompensas().isEmpty());
    }

}
