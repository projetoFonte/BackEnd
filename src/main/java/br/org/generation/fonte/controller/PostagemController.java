package br.org.generation.fonte.controller;

import java.time.LocalDateTime;
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

import br.org.generation.fonte.model.Postagem;
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
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@GetMapping("/data_inicial/{start}/data_final/{end}")
	public ResponseEntity<List<Postagem>> getByDataEntre(@PathVariable String start, @PathVariable String end){
		
		int anoInicial = Integer.valueOf(start.substring(0, 4));
		int mesIncial = Integer.valueOf(start.substring(5, 7));
		int diaInicial = Integer.valueOf(start.substring(8, 10));
		LocalDateTime inicio = LocalDateTime.of(anoInicial, mesIncial, diaInicial, 00, 00, 00);
		
		int anoFinal = Integer.valueOf(end.substring(0, 4));
		int mesFinal = Integer.valueOf(end.substring(5, 7));
		int diaFinal = Integer.valueOf(end.substring(8, 10));
		LocalDateTime fim = LocalDateTime.of(anoFinal, mesFinal, diaFinal, 00, 00, 00);
		
		return ResponseEntity.ok(postagemRepository.findByDataDePostagemBetween(inicio, fim));
	}
	
	@PostMapping
	public  ResponseEntity <Postagem> postPostagem(@Valid @RequestBody Postagem postagem){
		return temaRepository.findById(postagem.getTema().getId())
				.map(resposta -> {
					return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
				})
				.orElse(ResponseEntity.badRequest().build());
	}
	
	@PutMapping
	public  ResponseEntity <Postagem> putPostagem(@Valid @RequestBody Postagem postagem){
		
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
