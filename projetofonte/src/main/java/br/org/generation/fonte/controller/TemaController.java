package br.org.generation.fonte.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.fonte.model.TemaModel;
import br.org.generation.fonte.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins="*",allowedHeaders="*")
public class TemaController 
{
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	private ResponseEntity <List<TemaModel>> getAll()
	{
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <TemaModel> getById(@PathVariable Long id)
	{
		return temaRepository.findById(id)
				.map(res -> ResponseEntity.ok(res))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity <List<TemaModel>> getByCategoria(@PathVariable String categoria)
	{
		return ResponseEntity.ok(temaRepository
				.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity <List<TemaModel>> getByDescricao(@PathVariable String descricao)
	{
		return ResponseEntity.ok(temaRepository
				.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity <TemaModel> postTemaModel(@Valid @RequestBody TemaModel tema)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(temaRepository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity <TemaModel> putTemaModel(@Valid @RequestBody TemaModel tema )
	{
		return temaRepository.findById(tema.getId())
				.map(res -> ResponseEntity.ok(temaRepository.save(tema)))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <?> deleteTema(@PathVariable Long id)
	{
		return temaRepository.findById(id)
				.map(res -> {
					temaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
					.orElse(ResponseEntity
							.notFound().build());
				
	}
}

