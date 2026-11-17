package br.edu.ifpa.belem.pse.api.v1.controller;

import br.edu.ifpa.belem.pse.api.v1.assembler.curso.CursoDetailsAssembler;
import br.edu.ifpa.belem.pse.api.v1.assembler.curso.CursoInputDisassembler;
import br.edu.ifpa.belem.pse.api.v1.assembler.curso.CursoMinimalAssembler;
import br.edu.ifpa.belem.pse.api.v1.model.curso.CursoDetails;
import br.edu.ifpa.belem.pse.api.v1.model.curso.CursoMinimal;
import br.edu.ifpa.belem.pse.api.v1.model.curso.input.CursoInput;
import br.edu.ifpa.belem.pse.domain.exception.EntidadeNaoEncontradaException;
import br.edu.ifpa.belem.pse.domain.exception.NegocioException;
import br.edu.ifpa.belem.pse.domain.model.Curso;
import br.edu.ifpa.belem.pse.domain.repository.CursoRepository;
import br.edu.ifpa.belem.pse.domain.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(path = "/api/v1/cursos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CursoController {

    private final CursoService cursoService;
    private final CursoRepository cursoRepository;

    private final CursoDetailsAssembler cursoDetailsAssembler;
    private final CursoMinimalAssembler cursoMinimalAssembler;

    private final CursoInputDisassembler cursoInputDisassembler;

    public CursoController(CursoService cursoService, CursoRepository cursoRepository, CursoDetailsAssembler cursoDetailsAssembler, CursoMinimalAssembler cursoMinimalAssembler, CursoInputDisassembler cursoInputDisassembler) {
        this.cursoService = cursoService;
        this.cursoRepository = cursoRepository;
        this.cursoDetailsAssembler = cursoDetailsAssembler;
        this.cursoMinimalAssembler = cursoMinimalAssembler;
        this.cursoInputDisassembler = cursoInputDisassembler;
    }

    //Listar todos os cursos de forma minima (id, nome)
    @GetMapping
    public CollectionModel<CursoMinimal> listar(){
        return cursoMinimalAssembler.toCollectionModel(cursoRepository.findAll());
    }

    // Lista todos os curso de forma mais detalhada (id, nome, nivel escolar, tempo de duracao)
    @GetMapping(path = "/detalhes")
    public CollectionModel<CursoDetails> listarDetalhes(){
        return cursoDetailsAssembler.toCollectionModel(cursoRepository.findAll());
    }

    // Busca um curso pelo ID
    @GetMapping("/{id}")
    public CursoDetails buscar(@PathVariable UUID id){
        return cursoDetailsAssembler.toModel(cursoService.buscarOuFalhar(id));
    }

    // Recurso para adicionar um curso
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CursoDetails adicionar(@RequestBody @Valid CursoInput cursoInput){
        Curso cursos = cursoInputDisassembler.toDomainObject(cursoInput);
        cursos = cursoService.salvar(cursos);

        return cursoDetailsAssembler.toModel(cursos);
    }

    // Recurso para atualizar o curso
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CursoDetails atualizar(@PathVariable UUID id, @RequestBody @Valid CursoInput input){
        try{
            Curso cursoAtual = cursoService.buscarOuFalhar(id);
            cursoInputDisassembler.copyToDomainObject(input, cursoAtual);
            return cursoDetailsAssembler.toModel(cursoService.salvar(cursoAtual));
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id){
        cursoService.excluir(id);
    }

    // Ativar curso
    @PutMapping(path = "/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable UUID id){
        cursoService.ativar(id);
    }

    // Inativar curso
    @DeleteMapping(path = "/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inatyivar(@PathVariable UUID id){
        cursoService.inativar(id);
    }
}
