package br.edu.ifpa.belem.pse.domain.model;

import lombok.Getter;

@Getter
public enum NivelEscolar {

    TECNICO_INTEGRADO("Técnico Integrado"),
    TECNICO_SUBSEQUENTE("Técnico Subsequente"),
    GRADUACAO("Graduação"),
    POS_GRADUACAO("Pós Graduação"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado");

    private final String descricao;

    private NivelEscolar(String descricao){
        this.descricao = descricao;
    }

}
