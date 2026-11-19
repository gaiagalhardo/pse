package br.edu.ifpa.belem.pse.api.v1.controller;

import br.edu.ifpa.belem.pse.api.v1.assembler.candidato.CandidatoDetailsAssembler;
import br.edu.ifpa.belem.pse.api.v1.assembler.candidato.CandidatoInputDisassembler;
import br.edu.ifpa.belem.pse.api.v1.assembler.candidato.CandidatoMinimalAssembler;
import br.edu.ifpa.belem.pse.api.v1.assembler.candidato.CandidatoSummaryAssembler;
import br.edu.ifpa.belem.pse.api.v1.model.candidato.CandidatoDetails;
import br.edu.ifpa.belem.pse.api.v1.model.candidato.CandidatoMinimal;
import br.edu.ifpa.belem.pse.api.v1.model.candidato.CandidatoSummary;
import br.edu.ifpa.belem.pse.api.v1.model.candidato.input.CandidatoComSenhaInput;
import br.edu.ifpa.belem.pse.api.v1.model.candidato.input.CandidatoInput;
import br.edu.ifpa.belem.pse.domain.exception.EntidadeNaoEncontradaException;
import br.edu.ifpa.belem.pse.domain.exception.NegocioException;
import br.edu.ifpa.belem.pse.domain.model.Candidato;
import br.edu.ifpa.belem.pse.domain.repository.CandidatoRepository;
import br.edu.ifpa.belem.pse.domain.service.CandidatoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/candidatos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CandidatoController {

    private final CandidatoService candidatoService;
    private final CandidatoRepository candidatoRepository;

    private final CandidatoMinimalAssembler candidatoMinimalAssembler;
    private final CandidatoSummaryAssembler candidatoSummaryAssembler;
    private final CandidatoDetailsAssembler candidatoDetailsAssembler;

    private final CandidatoInputDisassembler candidatoInputDisassembler;

    public CandidatoController(CandidatoService candidatoService, CandidatoRepository candidatoRepository, CandidatoMinimalAssembler candidatoMinimalAssembler, CandidatoSummaryAssembler candidatoSummaryAssembler, CandidatoDetailsAssembler candidatoDetailsAssembler, CandidatoInputDisassembler candidatoInputDisassembler){
        this.candidatoService = candidatoService;
        this.candidatoRepository = candidatoRepository;
        this.candidatoMinimalAssembler = candidatoMinimalAssembler;
        this.candidatoSummaryAssembler = candidatoSummaryAssembler;
        this.candidatoDetailsAssembler = candidatoDetailsAssembler;
        this.candidatoInputDisassembler = candidatoInputDisassembler;
    }

    // Listar todos os candidatos
    @GetMapping
    public CollectionModel<CandidatoMinimal> listar(){
        return candidatoMinimalAssembler.toCollectionModel(candidatoRepository.findAll());
    }

    // Listar todos os candidatos - resumo
    @GetMapping("/resumo")
    public CollectionModel<CandidatoSummary> listarResumo(){
        return candidatoSummaryAssembler.toCollectionModel(candidatoRepository.findAll());
    }

    // Listar todos os candidatos - detalhes
    @GetMapping("/detalhes")
    public CollectionModel<CandidatoDetails> listarDetalhes(){
        return candidatoDetailsAssembler.toCollectionModel(candidatoRepository.findAll());
    }

    // Buscar um cadidato pelo registro e trazendo todos os dados.
    @GetMapping("/{id}")
    public CandidatoDetails buscar(@PathVariable UUID id){
        return candidatoDetailsAssembler.toModel(candidatoService.buscarOuFalhar(id));
    }

    // Cadastrar o candidato
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidatoDetails adicionar(@RequestBody @Valid CandidatoComSenhaInput candidatoComSenhaInput){
        Candidato candidato = candidatoInputDisassembler.toDomainObject(candidatoComSenhaInput);
        candidato = candidatoService.salvar(candidato);

        return candidatoDetailsAssembler.toModel(candidato);
    }

    // Atualizar os dados do candidato
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidatoDetails atualizar(@PathVariable UUID id, @RequestBody @Valid CandidatoInput candidatoInput){
        try{
            Candidato candidatoAtual = candidatoService.buscarOuFalhar(id);
            candidatoInputDisassembler.copyToDomainObject(candidatoInput, candidatoAtual);
            return  candidatoDetailsAssembler.toModel(candidatoService.buscarOuFalhar(id));
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id){
        candidatoService.excluir(id);
    }

    // Ativar curso
    @PutMapping(path = "/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable UUID id){
        candidatoService.ativar(id);
    }

    // Inativar curso
    @DeleteMapping(path = "/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inatyivar(@PathVariable UUID id){
        candidatoService.inativar(id);
    }



}
