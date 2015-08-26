package br.com.sispo.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sispo.model.entity.PedidoDePizza;

public class PedidoDePizzaDAO extends DAO<PedidoDePizza> {

	private Logger logger = Logger.getLogger(PedidoDePizzaDAO.class);

	public PedidoDePizzaDAO(Session session, Class<?> classe) {
		super(session, classe);
	}

	public List<PedidoDePizza> pesquisaPedidoByCodigoPedido(Long codigo) {
		
		Query query = session
		.createQuery(" select p from PedidoDePizza p " +
				" left join fetch p.pedido " +
				" where p.pedido.codigo = "+codigo+" ");

		return (List)query.list();
	}
	
	public Long pesquisaUltimoPedido(Long codigo) {

		System.out.println("DAO metodo pesquisaUltimoPedidoDePizza...");
		System.out.println("DAO session.isOpen() >>> " + session.isOpen());

		Query query = session
		.createQuery(" select max(p.codigo) from Pedido p " +
				" where p.cliente.cod_cliente = "+codigo+" ");

		return (Long)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<PedidoDePizza> pesquisaPedidosDePizza(String data) {
		Criteria c = session.createCriteria(PedidoDePizza.class);
		c.add(Restrictions.ilike("data", "%" + data + "%"));
		c.addOrder(Order.asc("data"));

		return c.list();
	}

}