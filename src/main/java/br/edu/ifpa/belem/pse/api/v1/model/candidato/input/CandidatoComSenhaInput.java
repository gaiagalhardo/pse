package br.edu.ifpa.belem.pse.api.v1.model.candidato.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatoComSenhaInput extends CandidatoInput {

    @NotBlank
    private String senha;

}
