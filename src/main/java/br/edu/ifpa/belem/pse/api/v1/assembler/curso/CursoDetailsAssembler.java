package br.edu.ifpa.belem.pse.api.v1.assembler.curso;

import br.edu.ifpa.belem.pse.api.v1.controller.CursoController;
import br.edu.ifpa.belem.pse.api.v1.model.curso.CursoDetails;
import br.edu.ifpa.belem.pse.domain.model.Curso;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CursoDetailsAssembler extends RepresentationModelAssemblerSupport<Curso, CursoDetails> {

    private final ModelMapper modelMapper;

    public CursoDetailsAssembler(){
        super(CursoController.class, CursoDetails.class);
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CursoDetails toModel(Curso curso){
        CursoDetails cursoDetails = createModelWithId(curso.getId(), curso);
        modelMapper.map(curso, cursoDetails);

        return cursoDetails;
    }

    @Override
    public CollectionModel<CursoDetails> toCollectionModel(Iterable<? extends Curso> entities){
        return super.toCollectionModel(entities);
    }

}
