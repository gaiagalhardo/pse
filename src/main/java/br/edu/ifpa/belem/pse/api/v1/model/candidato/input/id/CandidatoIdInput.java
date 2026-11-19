package br.edu.ifpa.belem.pse.api.v1.model.candidato.input.id;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatoIdInput {

    @NotNull
    private Long id;
}
