package br.edu.ifpa.belem.pse.domain.exception.curso;

import br.edu.ifpa.belem.pse.domain.exception.NegocioException;

public class NomeCursoJaExistenteException extends NegocioException {

    public NomeCursoJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
