package br.edu.ifpa.belem.pse.api.v1.assembler.candidato;

import br.edu.ifpa.belem.pse.api.v1.model.candidato.input.CandidatoInput;
import br.edu.ifpa.belem.pse.domain.model.Candidato;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CandidatoInputDisassembler {

    private final ModelMapper modelMapper;

    public CandidatoInputDisassembler(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public Candidato toDomainObject(CandidatoInput input){
        return modelMapper.map(input, Candidato.class);
    }

    public void copyToDomainObject(CandidatoInput input, Candidato candidato){
        modelMapper.map(input, candidato);
    }
}
