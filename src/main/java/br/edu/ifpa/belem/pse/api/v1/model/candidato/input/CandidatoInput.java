package br.edu.ifpa.belem.pse.api.v1.model.candidato.input;

import br.edu.ifpa.belem.pse.domain.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CandidatoInput {

    private UUID registro;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    private String rg;

    @NotBlank
    private String email;

    @NotBlank
    private String fone;

    private String nomePai;

    @NotBlank
    private String nomeMae;

    private LocalDate dataNascimento;

    @NotBlank
    private String naturalidade;

    private Endereco endereco;


    private Boolean ativo;

}
