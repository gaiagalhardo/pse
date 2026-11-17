package br.edu.ifpa.belem.pse.api.v1.model.curso;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cursos")
@Getter
@Setter
public class CursoMinimal  extends RepresentationModel<CursoMinimal> {

    private Long id;
    private String valorGerado;
    private String nome;

}
