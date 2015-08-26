package br.com.sispo.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Ingrediente")
public class Ingrediente implements Serializable {

	private static final long serialVersionUID = 3076099532882712710L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_ingrediente")
	private Long codigo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "valor")
	private Float valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tipoIngrediente")
	private TipoIngrediente tipoIngrediente = new TipoIngrediente();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public TipoIngrediente getTipoIngrediente() {
		return tipoIngrediente;
	}

	public void setTipoIngrediente(TipoIngrediente tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
	}	
}