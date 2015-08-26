package br.com.sispo.model.dao;

import org.hibernate.Session;

import br.com.sispo.model.entity.Uf;

public class UfDAO extends DAO<Uf> {

	public UfDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
}