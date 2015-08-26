package br.com.sispo.model.dao;

import org.hibernate.Session;

import br.com.sispo.model.entity.TipoUsuario;

public class TipoUsuarioDAO extends DAO<TipoUsuario> {

	public TipoUsuarioDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
}