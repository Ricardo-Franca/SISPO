package br.com.sispo.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;

public class EmpresaDAO extends DAO<Empresa> {

	private Logger logger = Logger.getLogger(EmpresaDAO.class);

	public EmpresaDAO(Session session, Class<?> classe) {
		super(session, classe);
	}

	public Empresa pesquisaEmpresaById(Long idEmpresa) {
	   logger.info("pesquisaEmpresaById : " + idEmpresa);
	   return (Empresa) session.load(Empresa.class, idEmpresa);
	}
	
	@SuppressWarnings("unchecked")
	public Empresa pesquisaEmpresaByCodigo(Long codigo) {		
		Criteria c = session.createCriteria(Empresa.class);
		c.add(Restrictions.idEq(codigo));
		
		return (Empresa) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> pesquisaEmpresas(String nome) {
		Criteria c = session.createCriteria(Empresa.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));

		return c.list();
	}
	public Empresa pesquisaEmpresaByUsuarioId(Long codigo) {

		System.out.println("DAO metodo pesquisaClienteByUsuarioId...");
		System.out.println("DAO session.isOpen() >>> " + session.isOpen());

		Query query = session
		.createQuery(" select e from Empresa e " +
				" left join fetch e.usuario " +
				" where e.usuario.cod_usuario = "+codigo+" ");

		return (Empresa) query.uniqueResult();
	}
	
	public void inativaEmpresa(Long codigo)
	{
		Query query = session
		.createQuery(" update Usuario u " +
				" set status = 2 " +
				" where cod_usuario = "+codigo+"");
		
		query.executeUpdate();
	}
	
	public List<Empresa> pesquisaEmpresasAtivas() 
	{
		
		Query query = session
		.createQuery(" select e from Empresa e" +
				" where e.usuario.status = 1 ");

		return query.list();
	}
	
	public Empresa pesquisaEmpresaByCnpj(String cnpj) {
		Query query = session
		.createQuery(" select e from Empresa e " +
				" where e.cnpj = '"+cnpj+"' ");

		return (Empresa) query.uniqueResult();
	}
	
	public Empresa pesquisaEmpresaByEmail(String email) {
		Query query = session
		.createQuery(" select e from Empresa e " +
				" where e.email = '"+email+"' ");

		return (Empresa) query.uniqueResult();
	}
}