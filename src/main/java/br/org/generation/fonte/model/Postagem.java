package br.org.generation.fonte.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity 
@Table(name = "tb_postagem")

public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotBlank(message = "É necessário definir o título da postagem.")
	@Size(max=500)
	private String titulo;
	
	@NotBlank(message = "É necessário definir o texto da postagem.")
	@Size(min = 0, max = 1000, message = "Informe o que você deseja postar.")
	private String texto;
	
	@Size(min = 0, max = 1000, message = "Escolha uma imagem para a postagem. (Opcional)")
	private String imagem;
	
	@UpdateTimestamp
    private LocalDateTime dataDePostagem;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
    @JsonIgnoreProperties("postagem")
    private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public LocalDateTime getDataDePostagem() {
		return dataDePostagem;
	}

	public void setDataDePostagem(LocalDateTime dataDePostagem) {
		this.dataDePostagem = dataDePostagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
}