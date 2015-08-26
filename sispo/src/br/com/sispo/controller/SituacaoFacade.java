package br.com.sispo.controller;

import br.com.sispo.model.entity.Situacao;

public interface SituacaoFacade extends BaseFacade<Situacao>{
	
	public void salva(Situacao s);
}