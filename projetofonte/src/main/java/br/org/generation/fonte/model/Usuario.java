package br.org.generation.fonte.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "É necessário cadastrar um email válido.")
	@Email(message = "É necessário cadastrar um email válido.")
	private String usuario;
	
	@NotBlank(message = "É necessário cadastrar uma senha válida.")
	@Size(min = 8,  message = "É necessário que informe uma senha válida.")
	private String senha;
	
	@NotBlank(message = "É necessário cadastrar um nome válido.")
	@Size(min = 4, max = 255, message = "É necessário que seu nome tenha no minimo 4 caracteres.")
	private String nome;
	
	private String foto;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;
	
	public Usuario(Long id,  String nome,String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario() { }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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