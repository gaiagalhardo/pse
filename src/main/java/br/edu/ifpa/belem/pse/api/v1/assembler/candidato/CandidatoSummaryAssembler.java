package br.edu.ifpa.belem.pse.api.v1.assembler.candidato;

import br.edu.ifpa.belem.pse.api.v1.controller.CandidatoController;
import br.edu.ifpa.belem.pse.api.v1.model.candidato.CandidatoSummary;
import br.edu.ifpa.belem.pse.domain.model.Candidato;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CandidatoSummaryAssembler extends RepresentationModelAssemblerSupport<Candidato, CandidatoSummary> {

    private final ModelMapper modelMapper;

    public CandidatoSummaryAssembler(){
        super(CandidatoController.class, CandidatoSummary.class);
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CandidatoSummary toModel(Candidato curso){
        CandidatoSummary cursoSummary = createModelWithId(curso.getId(), curso);
        modelMapper.map(curso, cursoSummary);

        return cursoSummary;
    }

    @Override
    public CollectionModel<CandidatoSummary> toCollectionModel(Iterable<? extends Candidato> entities){
        return super.toCollectionModel(entities);
    }
}
