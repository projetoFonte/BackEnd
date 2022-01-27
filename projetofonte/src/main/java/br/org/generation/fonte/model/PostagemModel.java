package br.org.generation.fonte.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity 
@Table(name = "tb_postagem")

public class PostagemModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotBlank(message = "É necessário definir o texto da postagem.")
	@Size(min = 0, max = 1000, message = "Informe o que você deseja postar.")
	private String texto;
	
	@NotBlank(message = "É necessário defiinir o título da postagem.")
	@Size(max=500)
	private String titulo;
	
	@Size(min = 0, max = 1000, message = "Escolha uma imagem para a postagem. (Opcional)")
	private String imagem;
	
	@Column(name = "data_lancamento")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDePostagem;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private TemaModel tema;

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

	public LocalDate getDataDePostagem() {
		return dataDePostagem;
	}

	public void setDataDePostagem(LocalDate dataDePostagem) {
		this.dataDePostagem = dataDePostagem;
	}

	public TemaModel getTema() {
		return tema;
	}

	public void setTema(TemaModel tema) {
		this.tema = tema;
	}
	}

