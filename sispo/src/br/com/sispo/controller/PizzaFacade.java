package br.com.sispo.controller;

import java.util.List;

import br.com.sispo.model.entity.Pizza;

public interface PizzaFacade extends BaseFacade<Pizza>{
	public void salva(Pizza c);

	public void remove(Pizza c);
	
	public Pizza procura(Long id);

	public void atualiza(Pizza c);
	
	public Pizza pesquisaPizzasByNome(String nome);
	
	public Pizza procuraById(Long codigo);
	
	public List<Pizza> pesquisaPizzasByCodigoEmpresa(Long codigo);
	
	public void inativaPizza(Long codigo);
}