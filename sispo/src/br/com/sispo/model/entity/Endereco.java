package br.com.sispo.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 3076099532882712710L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_endereco")
	private Long codigo;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "numero")
	private Integer numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "cep")
	private String cep;

	@Column(name = "cidade")
	private String cidade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_uf")
	private Uf uf = new Uf();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}
}