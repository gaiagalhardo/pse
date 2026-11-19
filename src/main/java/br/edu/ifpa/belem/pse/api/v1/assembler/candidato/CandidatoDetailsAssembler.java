package br.edu.ifpa.belem.pse.api.v1.assembler.candidato;

import br.edu.ifpa.belem.pse.api.v1.controller.CandidatoController;
import br.edu.ifpa.belem.pse.api.v1.model.candidato.CandidatoDetails;
import br.edu.ifpa.belem.pse.domain.model.Candidato;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CandidatoDetailsAssembler extends RepresentationModelAssemblerSupport<Candidato, CandidatoDetails> {

    private final ModelMapper modelMapper;

    public CandidatoDetailsAssembler(){
        super(CandidatoController.class, CandidatoDetails.class);
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CandidatoDetails toModel(Candidato candidato){
        CandidatoDetails candidatoDetails = createModelWithId(candidato.getRegistro(), candidato);
        modelMapper.map(candidato, candidatoDetails);

        return candidatoDetails;
    }

    @Override
    public CollectionModel<CandidatoDetails> toCollectionModel(Iterable<? extends Candidato> entities){
        return super.toCollectionModel(entities);
    }
}
