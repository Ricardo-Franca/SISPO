package br.com.sispo.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.sispo.model.dao.PedidoDAO;
import br.com.sispo.model.dao.PizzaDAO;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Pedido;
import br.com.sispo.model.entity.Pizza;

public class PizzaFacadeImpl implements PizzaFacade {

	private static final long serialVersionUID = -3674992325248842220L;
	
	private PizzaDAO pizzaDAO;
	private SessionFactory sf; 
	private	Session session;
	private Transaction tx;
	
	public void salva(Pizza p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);
		
		this.pizzaDAO.save(p);
				
		tx.commit();
		session.close();
	}

	public void atualiza(Pizza p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);
		
		this.pizzaDAO.update(p);
		
		tx.commit();
		session.close();
	}

	public Pizza procura(Long id) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);
				
		Pizza p = this.pizzaDAO.load(id);
		
		tx.commit();
		session.close();
		
		return p;
	}

	public void remove(Pizza p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);		
		
		this.pizzaDAO.delete(p);
		
		tx.commit();
		session.close();
	}

	public List<Pizza> lista() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);
				
		List<Pizza> lista = this.pizzaDAO.list();
		
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public Pizza pesquisaPizzasByNome(String nome) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);
		
		Pizza lista = this.pizzaDAO.pesquisaPizzas(nome);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<Pizza> pesquisaPizzasByCodigoEmpresa(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);
		
		List<Pizza> lista = this.pizzaDAO.pesquisaPizzasDaEmpresa(codigo);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public Pizza procuraById(Long id){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);
				
		Pizza p = this.pizzaDAO.pesquisaPizzaById(id);
		
		tx.commit();
		session.close();
		
		return p;
	}
	
	public void inativaPizza(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pizzaDAO = new PizzaDAO(session, Pizza.class);
				
		this.pizzaDAO.inativaPizza(codigo);
		
		tx.commit();
		session.close();
	}
}