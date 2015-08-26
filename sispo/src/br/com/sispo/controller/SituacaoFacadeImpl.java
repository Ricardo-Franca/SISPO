package br.com.sispo.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.sispo.model.dao.SituacaoDAO;
import br.com.sispo.model.entity.Situacao;

public class SituacaoFacadeImpl implements SituacaoFacade {

	private static final long serialVersionUID = 1471852322170590757L;
	
	private SituacaoDAO situacaoDAO;
	private SessionFactory sf; 
	private	Session session;
	private Transaction tx;
	
	public void salva(Situacao s) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		situacaoDAO = new SituacaoDAO(session, Situacao.class);
		
		this.situacaoDAO.save(s);
		
		tx.commit();
		session.close();
	}

	public void remove(Situacao t) {
		// TODO Auto-generated method stub
		
	}

	public Situacao procura(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualiza(Situacao t) {
		// TODO Auto-generated method stub
		
	}

	public List<Situacao> lista() {
		// TODO Auto-generated method stub
		return null;
	}
	
}