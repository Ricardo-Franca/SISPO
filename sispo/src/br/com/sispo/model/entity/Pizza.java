package br.com.sispo.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="Pizza")

public class Pizza implements Serializable {
	
	private static final long serialVersionUID = 7303500777612287295L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_pizza")
	private Long codigo;
	
	@Column(name="nome")
	private String nome; 
	
	@Column(name="observacao")
	private String observacao;	
	
	@Column(name="valor")
	private Float valor;
	
	@Column(name="pizza_imagem")
	private String pizza_imagem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_empresa")
	private Empresa empresa = new Empresa();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_status")
	private Status status = new Status();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPizza_imagem() {
		return pizza_imagem;
	}

	public void setPizza_imagem(String pizza_imagem) {
		this.pizza_imagem = pizza_imagem;
	}
}