package br.edu.ifpa.belem.pse.api.v1.assembler.curso;

import br.edu.ifpa.belem.pse.api.v1.model.curso.input.CursoInput;
import br.edu.ifpa.belem.pse.domain.model.Curso;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CursoInputDisassembler {

    private final ModelMapper modelMapper;

    public CursoInputDisassembler(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public Curso toDomainObject(CursoInput input){
        return modelMapper.map(input, Curso.class);
    }

    public void copyToDomainObject(CursoInput input, Curso curso){
        modelMapper.map(input, curso);
    }
}
