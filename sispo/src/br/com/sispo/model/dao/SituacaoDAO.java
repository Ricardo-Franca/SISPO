package br.com.sispo.model.dao;

import org.hibernate.Session;

import br.com.sispo.model.entity.Situacao;

public class SituacaoDAO extends DAO<Situacao> {

	public SituacaoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
}