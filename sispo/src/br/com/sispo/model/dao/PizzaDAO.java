package br.com.sispo.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sispo.model.entity.Empresa;
import br.com.sispo.model.entity.Pizza;

public class PizzaDAO extends DAO<Pizza> {

	private Logger logger = Logger.getLogger(PizzaDAO.class);

	public PizzaDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	@SuppressWarnings("unchecked")
	public Pizza pesquisaPizzaById(Long codigo) {		
		Criteria c = session.createCriteria(Pizza.class);
		c.add(Restrictions.idEq(codigo));
		
		return (Pizza) c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public Pizza pesquisaPizzas(String nome) {
		Criteria c = session.createCriteria(Pizza.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		

		return (Pizza) c.uniqueResult();
	}
	
	public List<Pizza> pesquisaPizzasDaEmpresa(Long codigo) 
	{
		
		Query query = session
		.createQuery(" select p from Pizza p" +
				" where cod_empresa = "+codigo+" and cod_status = 1 ");

		return query.list();
	}
	
	public void inativaPizza(Long codigo)
	{
		Query query = session
		.createQuery(" update Pizza p " +
				" set status = 2 " +
				" where codigo = "+codigo+"");
		
		query.executeUpdate();
	}
}