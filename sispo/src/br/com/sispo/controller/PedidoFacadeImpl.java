package br.com.sispo.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sispo.model.dao.PedidoDAO;
import br.com.sispo.model.dao.UsuarioDAO;
import br.com.sispo.model.entity.Pedido;
import br.com.sispo.model.entity.Usuario;

public class PedidoFacadeImpl implements PedidoFacade {
	
	private static final long serialVersionUID = -4230977397949665286L;
	
	private PedidoDAO pedidoDAO;
	private SessionFactory sf; 
	private	Session session;
	private Transaction tx;
	
	public void salva(Pedido p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		//session.flush();
		this.pedidoDAO.save(p);
		
		tx.commit();
		session.close();
	}

	public void atualiza(Pedido p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		this.pedidoDAO.update(p);
		
		tx.commit();
		session.close();
	}
	
	public Pedido procura(Long id) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
				
		Pedido p = this.pedidoDAO.load(id);
		
		tx.commit();
		session.close();
		
		return p;
	}
	
	public void atualizaSituacao(Long codigo, Integer situacao) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
				
		this.pedidoDAO.atualizaSituacao(codigo,situacao);
		
		tx.commit();
		session.close();
	}
	
	public Long consultaSituacao(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
				
		Long c = this.pedidoDAO.consultaSituacao(codigo);
		
		tx.commit();
		session.close();
		
		return c;
	}

	public void remove(Pedido p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		this.pedidoDAO.delete(p);
		
		tx.commit();
		session.close();
	}

	public List<Pedido> lista() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
				
		List<Pedido> lista = this.pedidoDAO.list();
		
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public Pedido procuraById(Long id){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
				
		Pedido p = this.pedidoDAO.pesquisaPedidoById(id);
		
		tx.commit();
		session.close();
		
		return p;
	}	
		
	public List<Pedido> pesquisaPedidosByData(String data) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		List<Pedido> lista = this.pedidoDAO.pesquisaPedidos(data);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<Pedido> pesquisaByCodigoCliente(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		List<Pedido> lista = this.pedidoDAO.pesquisaPedidoByCliente(codigo);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<Pedido> pesquisaByCodigoEmpresa(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		List<Pedido> lista = this.pedidoDAO.pesquisaPedidoByEmpresa(codigo);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<Pedido> pesquisaByUltimosPedidos(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		List<Pedido> lista = this.pedidoDAO.pesquisaUltimosPedidos(codigo);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public Long procuraUltimo(Long codigo){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
				
		Long p = this.pedidoDAO.pesquisaUltimoPedido(codigo);
		
		tx.commit();
		session.close();
		
		return p;
	}	
	
	public List<Pedido> pesquisaRelatorioDiario(Long codigo,String data) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		List<Pedido> lista = this.pedidoDAO.pesquisaRelatorioDiario(codigo,data);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<Pedido> pesquisaRelatorioPorPeriodo(Long codigo,String dataInicial,String dataFinal) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		List<Pedido> lista = this.pedidoDAO.pesquisaRelatorioPorPeriodo(codigo,dataInicial,dataFinal);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<Pedido> pesquisaRelatorioAnual(Long codigo,String ano) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		List<Pedido> lista = this.pedidoDAO.pesquisaRelatorioAnual(codigo,ano);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<String> pesquisaAnos(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		pedidoDAO = new PedidoDAO(session, Pedido.class);
		
		List<String> lista = this.pedidoDAO.pesquisaAnos(codigo);
		tx.commit();
		session.close();
		
		return lista;
	}
}