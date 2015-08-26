package br.com.sispo.controller;

import java.util.List;

import br.com.sispo.model.entity.Pedido;

public interface PedidoFacade extends BaseFacade<Pedido>{
	public void salva(Pedido c);

	public void remove(Pedido c);
	
	public Pedido procura(Long id);

	public void atualiza(Pedido c);
	
	public List<Pedido> pesquisaPedidosByData(String data);
	
	public List<Pedido> pesquisaByCodigoCliente(Long codigo);
	
	public List<Pedido> pesquisaByCodigoEmpresa(Long codigo);
	
	public List<Pedido> pesquisaByUltimosPedidos(Long codigo);
	
	public List<Pedido> pesquisaRelatorioDiario(Long codigo,String data);
	
	public List<Pedido> pesquisaRelatorioPorPeriodo(Long codigo,String dataInicial,String dataFinal);
	
	public List<Pedido> pesquisaRelatorioAnual(Long codigo,String ano);
	
	public List<String> pesquisaAnos(Long codigo);
	
	public Long procuraUltimo(Long codigo);
	
	public void atualizaSituacao(Long codigo,Integer situacao);
	
	public Pedido procuraById(Long codigo);
	
	public Long consultaSituacao(Long codigo);
}