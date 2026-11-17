package br.edu.ifpa.belem.pse.domain.exception.curso;

import br.edu.ifpa.belem.pse.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CursoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CursoNaoEncontradoException(UUID cursoId){
        this(String.format("Não foi encontrado nenhum curso com o código %d", cursoId));
    }
}
