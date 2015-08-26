package br.com.sispo.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Pedido_has_Pizza")
public class PedidoDePizza implements Serializable {

	private static final long serialVersionUID = 3076099532882712710L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_Pedido_has_Pizza")
	private Long codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_Pedido")
	private Pedido pedido = new Pedido();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_Pizza")
	private Pizza pizza = new Pizza();
	
	@Column(name="quantidade")
	private Integer quantidade;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}	
}