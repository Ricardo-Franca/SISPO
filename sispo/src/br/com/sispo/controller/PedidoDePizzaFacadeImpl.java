package br.com.sispo.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.sispo.model.dao.PedidoDePizzaDAO;
import br.com.sispo.model.entity.PedidoDePizza;

public class PedidoDePizzaFacadeImpl implements PedidoDePizzaFacade {
	
	private static final long serialVersionUID = -4230977397949665286L;
	
	private PedidoDePizzaDAO pedidoDePizzaDAO;
	private SessionFactory sf; 
	private	Session session;
	private Transaction tx;
	
	public void salva(PedidoDePizza p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDePizzaDAO = new PedidoDePizzaDAO(session, PedidoDePizza.class);
		//session.flush();
		this.pedidoDePizzaDAO.save(p);
		
		tx.commit();
		session.close();
	}

	public void atualiza(PedidoDePizza p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDePizzaDAO = new PedidoDePizzaDAO(session, PedidoDePizza.class);
		
		this.pedidoDePizzaDAO.update(p);
		
		tx.commit();
		session.close();
	}
	
	public PedidoDePizza procura(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDePizzaDAO = new PedidoDePizzaDAO(session, PedidoDePizza.class);
				
		PedidoDePizza p = this.pedidoDePizzaDAO.load(codigo);
		
		tx.commit();
		session.close();
		
		return p;
	}

	public void remove(PedidoDePizza p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDePizzaDAO = new PedidoDePizzaDAO(session, PedidoDePizza.class);
		
		this.pedidoDePizzaDAO.delete(p);
		
		tx.commit();
		session.close();
	}

	public List<PedidoDePizza> lista() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDePizzaDAO = new PedidoDePizzaDAO(session, PedidoDePizza.class);
				
		List<PedidoDePizza> lista = this.pedidoDePizzaDAO.list();
		
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<PedidoDePizza> procuraByCodigoPedido(Long codigo){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDePizzaDAO = new PedidoDePizzaDAO(session, PedidoDePizza.class);
				
		List<PedidoDePizza> pedidosDePizza = this.pedidoDePizzaDAO.pesquisaPedidoByCodigoPedido(codigo);
		
		tx.commit();
		session.close();
		
		return pedidosDePizza;
	}	
	
	public Long procuraUltimo(Long codigo){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDePizzaDAO = new PedidoDePizzaDAO(session, PedidoDePizza.class);
				
		Long p = this.pedidoDePizzaDAO.pesquisaUltimoPedido(codigo);
		
		tx.commit();
		session.close();
		
		return p;
	}	
}