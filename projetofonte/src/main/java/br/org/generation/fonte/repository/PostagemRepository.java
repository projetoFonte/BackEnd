package br.org.generation.fonte.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.fonte.model.PostagemModel;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> 
{	
	List<PostagemModel> findAllByTituloContainingIgnoreCase (String titulo);
}
