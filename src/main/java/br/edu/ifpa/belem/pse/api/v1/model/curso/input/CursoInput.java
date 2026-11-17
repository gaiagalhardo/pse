package br.edu.ifpa.belem.pse.api.v1.model.curso.input;

import br.edu.ifpa.belem.pse.domain.model.NivelEscolar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CursoInput {

    @NotBlank
    private String nome;

    private UUID valorGerado;

    @NotNull
    private NivelEscolar nivelEscolar;

    @NotNull
    private BigDecimal duracaoAnos;

    private Boolean ativo;

}
