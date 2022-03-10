package br.org.generation.fonte.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name = "tb_tema")

public class Tema {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 
	private Long id;
	
	@NotBlank(message = " É necessário definir a categoria! ")
	@Size(max = 255, message = " É necessário que informe a categoria do seu tema, utilizando no mínimo 10 caractéres. ")
	private String categoria;
	
	@NotBlank(message = "É necessário informar a descrição do seu tema! ")
	@Size(max = 255, message = " É necessário que informe a descrição do seu tema, utilizando no mínimo 10 caractéres. ")
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("tema")
	private List<Postagem> postagem; 


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	

}
