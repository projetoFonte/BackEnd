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
@Table(name="tb_usuario")
public class Usuario {
	
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="É necessário cadastrar um email válido.")
	@Size(min = 4, max = 255, message = "É necessário cadastrar um email válido...")
	private String email;
	
	@NotBlank(message="É necessário cadastrar um nome válido.")
	@Size(min = 4, max = 255, message = "É necessário que seu nome tenha no minimo 4 caracteres.")
	private String nome;
	
	@NotBlank(message="É necessário cadastrar uma senha válida.")
	@Size(min = 6, max = 255, message = "É necessário que informe uma senha válida.")
	private String senha;
	
	private String foto;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	

}
