package br.com.sispo.controller;

import br.com.sispo.model.entity.TipoUsuario;

public interface TipoUsuarioFacade extends BaseFacade<TipoUsuario>{
	
	public void salva(TipoUsuario u);
}