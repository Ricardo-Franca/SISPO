package br.com.sispo.controller;

import java.util.List;

import br.com.sispo.model.entity.PedidoDePizza;

public interface PedidoDePizzaFacade extends BaseFacade<PedidoDePizza>{
	public void salva(PedidoDePizza p);

	public void remove(PedidoDePizza p);
	
	public List<PedidoDePizza> procuraByCodigoPedido(Long codigo);

	public void atualiza(PedidoDePizza p);
	
	public Long procuraUltimo(Long codigo);
}