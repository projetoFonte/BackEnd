package br.org.generation.fonte.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.fonte.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> 
{	
	List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	List<Postagem> findByDataDePostagemBetween(LocalDateTime start, LocalDateTime end);

}


