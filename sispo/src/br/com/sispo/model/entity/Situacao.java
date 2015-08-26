package br.com.sispo.model.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Situacao")
public class Situacao implements Serializable {

	private static final long serialVersionUID = 3076099532882712710L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_situacao")
	private Long codigo;

	@Column(name = "desc_situacao")
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