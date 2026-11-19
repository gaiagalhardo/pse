package br.edu.ifpa.belem.pse.domain.service;

import br.edu.ifpa.belem.pse.domain.exception.EntidadeEmUsoException;
import br.edu.ifpa.belem.pse.domain.exception.candidato.CandidatoNaoEncontradoException;
import br.edu.ifpa.belem.pse.domain.exception.candidato.CpfCandidatoJaExistenteException;
import br.edu.ifpa.belem.pse.domain.model.Candidato;
import br.edu.ifpa.belem.pse.domain.repository.CandidatoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CandidatoService {

    private static final String MSG_CANDIDATO_EM_USO = "Candidato de registro %d não pode ser removido. O mesmo está em uso";
    private static final String MSG_CANDIDATO_CPF_EXISTENTE = "Já existe um candidato registrado com o CPF %s";


    private final CandidatoRepository candidatoRepository;
    private final EntityManager manager;

    public CandidatoService(CandidatoRepository candidatoRepository, EntityManager manager){
        this.candidatoRepository = candidatoRepository;
        this.manager = manager;
    }


    //Salvar o candidato no banco
    @Transactional
    public Candidato salvar(Candidato candidato){
        manager.detach(candidato);

        Optional<Candidato> cpfCandidatoExistente = candidatoRepository.findByCpf(candidato.getCpf());

        // Valida se o candidato já tem cadastrado o CPF
        if (cpfCandidatoExistente.isPresent() && !cpfCandidatoExistente.get().equals(candidato)){
            throw new CpfCandidatoJaExistenteException(String.format(MSG_CANDIDATO_CPF_EXISTENTE, candidato.getCpf()));
        }

        return candidatoRepository.save(candidato);
    }

    // Excluir o candidato
    @Transactional
    public void excluir(UUID registro){
        try{
            candidatoRepository.deleteByRegistro(registro);
            candidatoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CandidatoNaoEncontradoException(registro);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(MSG_CANDIDATO_EM_USO, registro));
        }
    }

    // Realiza uma busca para verificar se o candidato já foi cadastro no sistema
    @Transactional
    public Candidato buscarOuFalhar(UUID registro){
        return candidatoRepository.findByRegistro(registro).orElseThrow(() -> new CandidatoNaoEncontradoException(registro));
    }

    // Ativar o candidato
    @Transactional
    public void ativar(UUID registro){
        Candidato candidato = buscarOuFalhar(registro);
        candidato.ativar();
    }

    // Desativar o candidato
    @Transactional
    public void inativar(UUID registro){
        Candidato candidato = buscarOuFalhar(registro);
        candidato.inativar();
    }
}
