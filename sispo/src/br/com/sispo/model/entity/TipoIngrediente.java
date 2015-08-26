package br.com.sispo.model.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TipoIngrediente")
public class TipoIngrediente implements Serializable {

	private static final long serialVersionUID = -6601990742085831728L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_tipoIngrediente")
	private Long codigo;

	@Column(name = "descricao")
	private String descricao;

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
}