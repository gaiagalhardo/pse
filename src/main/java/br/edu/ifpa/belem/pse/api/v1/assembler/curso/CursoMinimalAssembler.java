package br.edu.ifpa.belem.pse.api.v1.assembler.curso;

import br.edu.ifpa.belem.pse.api.v1.controller.CursoController;
import br.edu.ifpa.belem.pse.api.v1.model.curso.CursoMinimal;
import br.edu.ifpa.belem.pse.domain.model.Curso;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CursoMinimalAssembler extends RepresentationModelAssemblerSupport<Curso, CursoMinimal> {

    private final ModelMapper modelMapper;

    public CursoMinimalAssembler(){
        super(CursoController.class, CursoMinimal.class);
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CursoMinimal toModel(Curso curso){
        CursoMinimal cursoMinimal = createModelWithId(curso.getId(), curso);
        modelMapper.map(curso, cursoMinimal);

        return cursoMinimal;
    }

    @Override
    public CollectionModel<CursoMinimal> toCollectionModel(Iterable<? extends Curso> entities){
        return super.toCollectionModel(entities);
    }

}
