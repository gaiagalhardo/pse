package br.edu.ifpa.belem.pse.api.v1.model.curso;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "cursos")
@Getter
@Setter
public class CursoDetails extends RepresentationModel<CursoDetails> {

    private Long id;
    private String nome;
    private String nivelEscolar;
    private BigDecimal duracaoAnos;
    private Boolean ativo;

}
