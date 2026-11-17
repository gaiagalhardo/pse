package br.edu.ifpa.belem.pse.domain.service;

import br.edu.ifpa.belem.pse.domain.exception.EntidadeEmUsoException;
import br.edu.ifpa.belem.pse.domain.exception.curso.CursoNaoEncontradoException;
import br.edu.ifpa.belem.pse.domain.exception.curso.NomeCursoJaExistenteException;
import br.edu.ifpa.belem.pse.domain.model.Curso;
import br.edu.ifpa.belem.pse.domain.repository.CursoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CursoService {

    //Mensagem caso o curso esteja em uso e tentem excluir
    private static final String MSG_CURSO_EM_USO = "Curso de código %d não pode ser removido. Está em uso.";

    private final CursoRepository cursoRepository;
    private final EntityManager manager;


    public CursoService(CursoRepository cursoRepository, EntityManager manager){
        this.cursoRepository = cursoRepository;
        this.manager = manager;
    }


    // salvar o curso
    @Transactional
    public Curso salvar(Curso curso){
        manager.detach(curso);

        Optional<Curso> nomeCursoJaExistente = cursoRepository.findByNomeIgnoreCase(curso.getNome());

        // Valida se o curso já foi cadastrado
        if (nomeCursoJaExistente.isPresent() && !nomeCursoJaExistente.get().equals(curso)){
            throw new NomeCursoJaExistenteException("Curso já cadastrado no sistema");
        }

        return cursoRepository.save(curso);
    }

    // Regra para excluir um curso
    @Transactional
    public void excluir(UUID id){
        try{
            cursoRepository.deleteByValorGerado(id);
            cursoRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new CursoNaoEncontradoException(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException((String.format(MSG_CURSO_EM_USO, id)));
        }
    }

    // Realiza uma busca para verificar se o curso já foi cadastrado no sistema.
    @Transactional
    public Curso buscarOuFalhar(UUID id){
        return cursoRepository.findByValorGerado(id).orElseThrow(() -> new CursoNaoEncontradoException(id));
    }

    // Ativar curso
    @Transactional
    public void ativar(UUID id){
        Curso curso = buscarOuFalhar(id);
        curso.ativar();
    }

    //Desativar curso
    @Transactional
    public void inativar(UUID id){
        Curso curso = buscarOuFalhar(id);
        curso.inativar();
    }

    // Ativa cursos em lotes
    @Transactional
    public void ativarEmLotes(List<UUID> cursosIds){
        cursosIds.forEach(this::ativar);
    }

    // Desativa cursos em lotes
    @Transactional
    public void inativarEmLotes(List<UUID> cursosIds){
        cursosIds.forEach(this::inativar);
    }

}
