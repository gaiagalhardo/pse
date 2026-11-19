package br.edu.ifpa.belem.pse.domain.exception.candidato;

import br.edu.ifpa.belem.pse.domain.exception.NegocioException;

public class CpfCandidatoJaExistenteException extends NegocioException {
    public CpfCandidatoJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
