package br.edu.ifpa.belem.pse.domain.exception.candidato;

import br.edu.ifpa.belem.pse.domain.exception.NegocioException;

public class NomeCandidatoJaExistenteException extends NegocioException {
    public NomeCandidatoJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
