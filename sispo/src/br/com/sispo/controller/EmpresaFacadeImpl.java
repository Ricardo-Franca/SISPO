package br.com.sispo.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.sispo.model.dao.ClienteDAO;
import br.com.sispo.model.dao.EmpresaDAO;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;
import br.com.sispo.model.entity.Pizza;

public class EmpresaFacadeImpl implements EmpresaFacade {

	private static final long serialVersionUID = -3674992325248842220L;
	
	private EmpresaDAO empresaDAO;
	private SessionFactory sf; 
	private	Session session;
	private Transaction tx;
	
	public void salva(Empresa p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
		session.flush();
		this.empresaDAO.save(p);
		
		
		tx.commit();
		session.close();
	}

	public void atualiza(Empresa p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
		
		this.empresaDAO.update(p);
		
		tx.commit();
		session.close();
	}

	public Empresa procura(Long id) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
				
		Empresa p = this.empresaDAO.load(id);
		
		tx.commit();
		session.close();
		
		return p;
	}

	public void remove(Empresa p) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);		
		
		this.empresaDAO.delete(p);
		
		tx.commit();
		session.close();
	}

	public List<Empresa> lista() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
				
		List<Empresa> lista = this.empresaDAO.list();
		
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public List<Empresa> pesquisaEmpresasByNome(String nome) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
		
		List<Empresa> lista = this.empresaDAO.pesquisaEmpresas(nome);
		tx.commit();
		session.close();
		
		return lista;
	}
	
	public Empresa procuraById(Long id){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
				
		Empresa p = this.empresaDAO.pesquisaEmpresaById(id);
		
		tx.commit();
		session.close();
		
		return p;
	}
	
	public Empresa procuraByCodigo(Long id){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
				
		Empresa p = this.empresaDAO.pesquisaEmpresaByCodigo(id);
		
		tx.commit();
		session.close();
		
		return p;
	}

	public Empresa procuraByUsuarioId(Long id) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
				
		Empresa p = this.empresaDAO.pesquisaEmpresaByUsuarioId(id);
		
		tx.commit();
		session.close();
		
		return p;
	}
	
	public Empresa procuraByCnpj(String cnpj){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);

		Empresa p = this.empresaDAO.pesquisaEmpresaByCnpj(cnpj);
		
		tx.commit();
		session.close();
		
		return p; 
	}
	
	public Empresa procuraByEmail(String email){
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);

		Empresa p = this.empresaDAO.pesquisaEmpresaByEmail(email);
		
		tx.commit();
		session.close();
		
		return p; 
	}
	
	public void inativaEmpresa(Long codigo) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
				
		this.empresaDAO.inativaEmpresa(codigo);
		
		tx.commit();
		session.close();
	}
	
	public List<Empresa> pesquisaEmpresasAtivas() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory(); 
		session = sf.openSession();
		tx = session.beginTransaction();
		empresaDAO = new EmpresaDAO(session, Empresa.class);
		
		List<Empresa> lista = this.empresaDAO.pesquisaEmpresasAtivas();
		tx.commit();
		session.close();
		
		return lista;
	}
}