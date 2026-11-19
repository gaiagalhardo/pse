package br.edu.ifpa.belem.pse.domain.exception.candidato;

import br.edu.ifpa.belem.pse.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CandidatoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CandidatoNaoEncontradoException(String message) {
        super(message);
    }

    public CandidatoNaoEncontradoException(UUID registro){
        this(String.format("Não foi possível encontrar o candidato com o código %d", registro));
    }
}
