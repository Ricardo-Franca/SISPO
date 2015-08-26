package br.com.sispo.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Pedido;
import br.com.sispo.model.entity.PedidoDePizza;

public class PedidoDAO extends DAO<Pedido> {

	private Logger logger = Logger.getLogger(PedidoDAO.class);

	public PedidoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}

	public Pedido pesquisaPedidoById(Long codigo) {
		Query query = session
		.createQuery(" select p from Pedido p " +
				" where p.codigo = "+codigo+"");
		
		return (Pedido)query.uniqueResult();
	}
	
	public Long pesquisaUltimoPedido(Long codigo) {

		System.out.println("DAO metodo pesquisaUltimoPedido...");
		System.out.println("DAO session.isOpen() >>> " + session.isOpen());

		Query query = session
		.createQuery(" select max(p.codigo) from Pedido p " +
				" where p.cliente.cod_cliente = "+codigo+" ");

		return (Long)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> pesquisaPedidos(String data) {
		Criteria c = session.createCriteria(Pedido.class);
		c.add(Restrictions.ilike("data", "%" + data + "%"));
		c.addOrder(Order.asc("data"));

		return c.list();
	}
	
	public List<Pedido> pesquisaPedidoByCliente(Long codigo) {
		
		Query query = session
		.createQuery(" select p from Pedido p " +
				" left join fetch p.cliente " +
				" where p.cliente = "+codigo+" " +
				" order by p.situacao, p.data desc ");

		return (List)query.list();
	}

	public List<Pedido> pesquisaPedidoByEmpresa(Long codigo) {
	
		Query query = session
		.createQuery(" select p from Pedido p " +
				" left join fetch p.empresa " +
				" where p.empresa = "+codigo+" " +
				" order by p.situacao, p.data desc");
	
		return (List)query.list();
	}

	public List<Pedido> pesquisaUltimosPedidos(Long codigo) {
		
		Query query = session
		.createQuery(" select p from Pedido p " +
				" left join fetch p.empresa " +
				" where p.empresa = "+codigo+" and p.situacao = 1 " +
				" order by p.situacao, p.data desc");
	
		return (List)query.list();
	}

	public void atualizaSituacao(Long codigo,Integer situacao)
	{
		Query query = session
		.createQuery(" update Pedido p " +
				" set situacao = "+situacao+" " +
				" where codigo = "+codigo+"");
		
		query.executeUpdate();
	}
	
	public Long consultaSituacao(Long codigo)
	{
		Query query = session
		.createQuery(" select situacao.codigo from Pedido p " +
				" where p.codigo = "+codigo+"");
		
		return (Long)query.uniqueResult();
	}
	
	public List<Pedido> pesquisaRelatorioDiario(Long codigo,String data) {
		
		Query query = session
		.createQuery(" select p from Pedido p " +
				" left join fetch p.empresa " +
				" where p.empresa = "+codigo+" and p.data ='"+data+"'" +
				" order by p.data");

		return (List)query.list();
	}
	
	public List<Pedido> pesquisaRelatorioPorPeriodo(Long codigo,String dataInicial,String dataFinal) {
		
		Query query = session
		.createQuery(" select p from Pedido p " +
				" left join fetch p.empresa " +
				" where p.empresa = "+codigo+" " +
				" and p.data >='"+dataInicial+"' and p.data <='"+dataFinal+"'" +
				" order by p.situacao, p.data desc");

		return (List)query.list();
	}
	
	public List<Pedido> pesquisaRelatorioAnual(Long codigo,String ano) {
		
		Query query = session
		.createQuery(" select p from Pedido p " +
				" left join fetch p.empresa " +
				" where p.empresa = "+codigo+" " +
				" and p.data like'%"+ano+"'" +
				" order by p.situacao, p.data desc");

		return (List)query.list();
	}
	
	public List<String> pesquisaAnos(Long codigo) {
		
		Query query = session
		.createSQLQuery(" select distinct substring(data,7,10) from Pedido " +
				" where cod_empresa = "+codigo+"");

		return (List)query.list();
	}

}