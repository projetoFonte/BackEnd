package br.org.generation.fonte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.fonte.model.TemaModel;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long>
{
	List<TemaModel> findAllByCategoriaContainingIgnoreCase(String categoria);
	List<TemaModel> findAllByDescricaoContainingIgnoreCase(String descricao);
}
