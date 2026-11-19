package br.edu.ifpa.belem.pse.api.v1.model.candidato;

import br.edu.ifpa.belem.pse.domain.model.Endereco;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.util.UUID;

@Relation(collectionRelation = "candidatos")
@Getter
@Setter
public class CandidatoDetails extends RepresentationModel<CandidatoDetails> {

    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String fone;
    private String nomePai;
    private String nomeMae;
    private LocalDate dataNascimento;
    private String naturalidade;

    private Endereco endereco;

   private Boolean ativo;
}
