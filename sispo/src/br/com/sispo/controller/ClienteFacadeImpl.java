package br.com.sispo.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.sispo.model.dao.ClienteDAO;
import br.com.sispo.model.dao.PizzaDAO;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Pizza;

public class ClienteFacadeImpl implements ClienteFacade {

	private static final long serialVersionUID = -3674992325248842220L;
	
	private ClienteDAO clienteDAO;
	private SessionFactory sf; 
	private	Session session;
	private Transaction tx;
	
	public void salva(Cliente p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
		
		this.clienteDAO.save(p);
				
		tx.commit();
		session.close();
	}

	public void atualiza(Cliente p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
		
		this.clienteDAO.update(p);
		
		tx.commit();
		session.close();
	}

	public Cliente procura(Long id) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
				
		Cliente p = this.clienteDAO.load(id);
		
		tx.commit();
		session.close();
		
		return p;
	}

	public void remove(Cliente p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);		
		
		this.clienteDAO.delete(p);
		
		tx.commit();
		session.close();
	}

	public List<Cliente> lista() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
				
		List<Cliente> lista = this.clienteDAO.list();
		
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public Cliente pesquisaClientesByNome(String nome) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
		
		Cliente lista = this.clienteDAO.pesquisaClientes(nome);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public Cliente procuraById(Long id){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
				
		Cliente p = this.clienteDAO.pesquisaClienteById(id);
		
		tx.commit();
		session.close();
		
		return p;
	}
	
	public Cliente procuraByUsuarioId(Long id){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
				
		Cliente p = this.clienteDAO.pesquisaClienteByUsuarioId(id);
		
		tx.commit();
		session.close();
		
		return p;
	}	
	
	public Cliente procuraByCpf(String cpf){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);

		Cliente p = this.clienteDAO.pesquisaClienteByCpf(cpf);
		
		tx.commit();
		session.close();
		
		return p; 
	}
	
	public Cliente procuraByEmail(String email){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);

		Cliente p = this.clienteDAO.pesquisaClienteByEmail(email);
		
		tx.commit();
		session.close();
		
		return p; 
	}
	
	public void inativaCliente(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
				
		this.clienteDAO.inativaCliente(codigo);
		
		tx.commit();
		session.close();
	}
	
	public List<Cliente> pesquisaClientesAtivos() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		clienteDAO = new ClienteDAO(session, Cliente.class);
		
		List<Cliente> lista = this.clienteDAO.pesquisaClientesAtivos();
		tx.commit();
		session.close();
		
		return lista;
	}
}