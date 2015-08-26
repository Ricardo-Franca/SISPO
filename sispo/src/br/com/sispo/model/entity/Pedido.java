package br.com.sispo.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 3076099532882712710L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_pedido")
	private Long codigo;

	@Column(name = "data")
	private String data;
	
	@Column(name="hora")
	private String hora;

	@Column(name = "pagamento")
	private Float pagamento;
	
	@Column(name = "valor")
	private Float valor;
	
	@Column(name = "troco")
	private Float troco;

	@ManyToOne()
	@JoinColumn(name = "cod_empresa")
	private Empresa empresa = new Empresa();

	@ManyToOne()
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente = new Cliente();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_situacao")
	private Situacao situacao = new Situacao();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Float getPagamento() {
		return pagamento;
	}

	public void setPagamento(Float pagamento) {
		this.pagamento = pagamento;
	}
	
	
	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	public Float getTroco() {
		return troco;
	}

	public void setTroco(Float troco) {
		this.troco = troco;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getHora() {
		return hora;
	}
}