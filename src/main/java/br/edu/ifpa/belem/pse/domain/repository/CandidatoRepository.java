package br.edu.ifpa.belem.pse.domain.repository;

import br.edu.ifpa.belem.pse.domain.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Optional<Candidato> findByCpf(String cpf);

    void deleteByRegistro(UUID registro);

    Optional<Candidato> findByRegistro(UUID registro);
}
