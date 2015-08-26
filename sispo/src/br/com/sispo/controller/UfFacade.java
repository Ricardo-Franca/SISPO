package br.com.sispo.controller;

import br.com.sispo.model.entity.Uf;

public interface UfFacade extends BaseFacade<Uf>{
	
	public void salva(Uf u);
}