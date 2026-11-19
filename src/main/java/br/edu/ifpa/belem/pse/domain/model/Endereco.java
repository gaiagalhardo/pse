package br.edu.ifpa.belem.pse.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    public Endereco(){}

    private String cep;
    private String estado;
    private String cidade;
    private String logradouro;
    private String complemento;
    private String numero;

}
