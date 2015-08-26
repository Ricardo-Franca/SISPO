package br.com.sispo.model.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@Column(name = "razao_social")
	private String razao_social;

	@Column(name = "nome_fantasia")
	private String nome_fantasia;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "horario_funcionamento")
	private String horario_funcionamento;

	@Column(name = "email")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco = new Endereco();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_usuario")
	private Usuario usuario = new Usuario();
	
	@Column(name="pizzaria_logo")
	private String pizzaria_logo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setPizzaria_logo(String pizzaria_logo) {
		this.pizzaria_logo = pizzaria_logo;
	}

	public String getPizzaria_logo() {
		return pizzaria_logo;
	}

	public String getHorario_funcionamento() {
		return horario_funcionamento;
	}

	public void setHorario_funcionamento(String horario_funcionamento) {
		this.horario_funcionamento = horario_funcionamento;
	}
}
