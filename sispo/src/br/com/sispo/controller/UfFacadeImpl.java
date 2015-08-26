package br.com.sispo.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.sispo.model.dao.UfDAO;
import br.com.sispo.model.entity.Uf;

public class UfFacadeImpl implements UfFacade {

	private static final long serialVersionUID = 1471852322170590757L;
	
	private UfDAO uFDAO;
	private SessionFactory sf; 
	private	Session session;
	private Transaction tx;
	
	public void salva(Uf u) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		uFDAO = new UfDAO(session, Uf.class);
		
		this.uFDAO.save(u);
		
		tx.commit();
		session.close();
	}

	public void remove(Uf t) {
		// TODO Auto-generated method stub
		
	}

	public Uf procura(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualiza(Uf t) {
		// TODO Auto-generated method stub
		
	}

	public List<Uf> lista() {
		// TODO Auto-generated method stub
		return null;
	}	
}