package br.org.generation.fonte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.fonte.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>
{
	List<Tema> findAllByCategoriaContainingIgnoreCase(String categoria);
	List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}
