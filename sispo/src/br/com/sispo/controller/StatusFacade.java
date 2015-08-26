package br.com.sispo.controller;

import br.com.sispo.model.entity.Status;

public interface StatusFacade extends BaseFacade<Status>{
	
	public void salva(Status s);
}