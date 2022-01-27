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


import br.org.generation.fonte.model.PostagemModel;
import br.org.generation.fonte.repository.PostagemRepository;
import br.org.generation.fonte.repository.TemaRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<PostagemModel>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity <PostagemModel> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<PostagemModel>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	@PostMapping
	public  ResponseEntity <PostagemModel> postPostagem(@Valid @RequestBody PostagemModel postagem){
		return temaRepository.findById(postagem.getTema().getId())
				.map(resposta -> {
					return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
				})
				.orElse(ResponseEntity.badRequest().build());
	}
	@PutMapping
	public  ResponseEntity <PostagemModel> putPostagem(@Valid @RequestBody PostagemModel postagem){
		
		if (postagemRepository.existsById(postagem.getId())){

			return temaRepository.findById(postagem.getTema().getId())
					.map(resposta -> {
						return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
					})
					.orElse(ResponseEntity.badRequest().build());
		}		
		
		return ResponseEntity.notFound().build();

	}	
	@DeleteMapping("/{id}")
	public  ResponseEntity <?> DeletePostagem(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta ->{
					postagemRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());	
	}

}
