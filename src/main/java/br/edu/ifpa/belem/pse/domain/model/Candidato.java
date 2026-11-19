package br.edu.ifpa.belem.pse.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Candidato {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID registro;
    private String nome;
    private String cpf;
    private String senha;
    private String rg;
    private String email;
    private String fone;
    private String nomePai;
    private String nomeMae;
    private LocalDate dataNascimento;

    @CreationTimestamp
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    private String naturalidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    //Metodos para ativar e desativar o candidato
    public void ativar(){
        setAtivo(true);
    }

    public void inativar(){
        setAtivo(false);
    }

    // Metodo para gerar o valor gerado UUID
    @PrePersist
    public void gerarRegistro(){
        if (this.registro == null){
            this.registro = UUID.randomUUID();
        }
    }
}
