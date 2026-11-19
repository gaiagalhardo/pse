package br.edu.ifpa.belem.pse.api.v1.assembler.candidato;

import br.edu.ifpa.belem.pse.api.v1.controller.CandidatoController;
import br.edu.ifpa.belem.pse.api.v1.model.candidato.CandidatoMinimal;
import br.edu.ifpa.belem.pse.domain.model.Candidato;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CandidatoMinimalAssembler extends RepresentationModelAssemblerSupport<Candidato, CandidatoMinimal> {

    private final ModelMapper modelMapper;

    public CandidatoMinimalAssembler(){
        super(CandidatoController.class, CandidatoMinimal.class);
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CandidatoMinimal toModel(Candidato candidato){
        CandidatoMinimal candidatoMinimal = createModelWithId(candidato.getId(), candidato);
        modelMapper.map(candidato, candidatoMinimal);

        return candidatoMinimal;
    }

    @Override
    public CollectionModel<CandidatoMinimal> toCollectionModel(Iterable<? extends Candidato> entities){
        return super.toCollectionModel(entities);
    }
}
