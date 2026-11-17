package br.edu.ifpa.belem.pse.api.v1.model.curso.input.id;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoIdInput {

    @NotNull
    private Long id;
}
