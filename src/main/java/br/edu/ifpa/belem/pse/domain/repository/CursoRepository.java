package br.edu.ifpa.belem.pse.domain.repository;

import br.edu.ifpa.belem.pse.domain.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNomeIgnoreCase(String nome);

    Optional<Curso> findByValorGerado(UUID id);

    void deleteByValorGerado(UUID id);
}
