package br.com.sispo.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Ingrediente_has_Pizza")
public class IngredienteDePizza implements Serializable {

	private static final long serialVersionUID = 3076099532882712710L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_Ingrediente_has_Pizza")
	private Long codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_Ingrediente")
	private Ingrediente ingrediente = new Ingrediente();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_Pizza")
	private Pizza pizza = new Pizza();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}	
}