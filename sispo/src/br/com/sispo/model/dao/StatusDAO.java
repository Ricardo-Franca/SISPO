package br.com.sispo.model.dao;

import org.hibernate.Session;

import br.com.sispo.model.entity.Status;

public class StatusDAO extends DAO<Status> {

	public StatusDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
}