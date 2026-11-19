package br.edu.ifpa.belem.pse.api.v1.model.candidato;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Relation(collectionRelation = "candidatos")
@Getter
@Setter
public class CandidatoSummary extends RepresentationModel<CandidatoSummary> {

    private Long id;
    private String nome;
    private String email;
    private String fone;

}
