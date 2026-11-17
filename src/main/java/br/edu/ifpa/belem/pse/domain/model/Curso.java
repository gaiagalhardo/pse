package br.edu.ifpa.belem.pse.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Curso {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // Valor gerado de forma automatica e embaralhado para utilizar nos endpoints
    @Column(name = "valor_gerado", nullable = false, updatable = false)
    private UUID valorGerado;

    private String nome;

    @Enumerated(EnumType.STRING)
    private NivelEscolar nivelEscolar;

    //Duracao do curso - em anos
    private BigDecimal duracaoAnos;

    // Atributo para ativar ou nao o curso
    private Boolean ativo;

    // Apenas para registrar data que o curso foi cadastrado
    @CreationTimestamp
    private OffsetDateTime dataCadastroCurso;

    // Apenas para verificar se houver alguma mudança e registrado a data
    @UpdateTimestamp
    private OffsetDateTime dataAtualizacaoCurso;

    // Metodo para gerar o valor gerado UUID
    @PrePersist
    public void gerarCodigo(){
        if (this.valorGerado == null){
            this.valorGerado = UUID.randomUUID();
        }
    }

    //Metodos para ativar e desativar o curso
    public void ativar(){
        setAtivo(true);
    }

    public void inativar(){
        setAtivo(false);
    }
}
