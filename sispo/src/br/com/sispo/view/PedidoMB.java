package br.com.sispo.view;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sispo.controller.EmpresaFacade;
import br.com.sispo.controller.EmpresaFacadeImpl;
import br.com.sispo.controller.PedidoDePizzaFacade;
import br.com.sispo.controller.PedidoDePizzaFacadeImpl;
import br.com.sispo.controller.PedidoFacade;
import br.com.sispo.controller.PedidoFacadeImpl;
import br.com.sispo.controller.PizzaFacade;
import br.com.sispo.controller.PizzaFacadeImpl;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;
import br.com.sispo.model.entity.Pedido;
import br.com.sispo.model.entity.PedidoDePizza;
import br.com.sispo.model.entity.Pizza;
import br.com.sispo.model.entity.Situacao;
import javax.faces.model.SelectItem;
public class PedidoMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;

	private Pedido pedido = new Pedido();
    
	private Long codigoPedido;
	private Long id;
	private Long codigoEmpresa;
	private Long codigoPizza;
	private Integer quantidade=1;
	private Integer codigoSituacao;
	private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	private ArrayList<PedidoDePizza> pedidosDePizza = new ArrayList<PedidoDePizza>();


	private  List<SelectItem> anos;

	public PedidoMB(){
		System.out.println(" >>>>>>>>>>>>>>>>>>>> Contrutor do PEDIDO_MB <<<<<<<<<<<<<<<<<<");
		
		if(this.pedido == null){
			this.pedido = new Pedido(); 			
		}
	}	
	
	public void escolhePizzaria() throws IOException
	{	
		Empresa empresa = new Empresa();
		EmpresaFacade empresaService = new EmpresaFacadeImpl();
		empresa = empresaService.procuraByCodigo(codigoEmpresa);
		this.pedido.setEmpresa(empresa);
		Situacao situacao = new Situacao();
		situacao.setCodigo(1l);
		situacao.setDescricao("Aguardando Confirmação");
		this.pedido.setSituacao(situacao);
						
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
        this.pedidosDePizza = null;
        session.setAttribute("pedidosDePizza", this.pedidosDePizza);
        
        Cliente cliente = (Cliente)session.getAttribute("clienteLogado");
        this.pedido.setCliente(cliente);
        
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        this.pedido.setData(formatador.format(data));
        this.pedido.setPagamento(null);
        this.pedido.setTroco(null);
        
        session.setAttribute("pedido", this.pedido);
        session.setAttribute("tipoPagina", 1);
        rp.sendRedirect(rq.getContextPath() + "/pages/cliente/pedido.jsf");		
	}
	
	public void escolheAlterarPedido() throws IOException
	{		
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		Long situacao = pedidoService.consultaSituacao(this.codigoPedido);
		
		if(situacao!=1)
		{
			FacesMessage facesMessage = new FacesMessage();
			
			if(situacao==2)
			{
				facesMessage.setSummary("O pedido já foi confirmado e não pode ser alterado.");
			}else
			 {
				if(situacao==3)
				{
					facesMessage.setSummary("O pedido está sendo preparado e não pode ser alterado.");
				}else
				 {
					if(situacao==4)
					{
						facesMessage.setSummary("O pedido saiu para entrega e não pode ser alterado.");
					}else
					 {
						if(situacao==5)
						{
							facesMessage.setSummary("O pedido já foi concluído e não pode ser alterado.");
						}else
						 {
							if(situacao==6)
							{
								facesMessage.setSummary("O pedido já foi recusado e não pode ser alterado.");
							}else
							 {
								if(situacao==7)
								{
									facesMessage.setSummary("O pedido já foi cancelado e não pode ser alterado.");
								}
							 }
						 }
					 }
				 }			 	
			 }
			
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			
		}else
		{
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        
			Pedido pedido = (Pedido)pedidoService.procuraById(this.codigoPedido);
			PedidoDePizzaFacade pedidoDePizzaService = new PedidoDePizzaFacadeImpl();
			
			ArrayList<PedidoDePizza> pedidosDePizza = (ArrayList<PedidoDePizza>)pedidoDePizzaService.procuraByCodigoPedido(pedido.getCodigo());
			ArrayList<PedidoDePizza> pedidosDePizzaAntigo = (ArrayList<PedidoDePizza>)pedidoDePizzaService.procuraByCodigoPedido(pedido.getCodigo());
			
			//(ArrayList<PedidoDePizza>)session.getAttribute("pedidosDePizza");
			
			session.setAttribute("pedidosDePizza",pedidosDePizza);
			session.setAttribute("pedidosDePizzaAntigo",pedidosDePizzaAntigo);
			session.setAttribute("pedido", pedido);
			session.setAttribute("tipoPagina", 2);
			
	        rp.sendRedirect(rq.getContextPath() + "/pages/cliente/alteraPedido.jsf");
		}
		
	}
	
	public void escolheConsultarPedido() throws IOException
	{		
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		Long situacao = pedidoService.consultaSituacao(this.codigoPedido);
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        
		Pedido pedido = (Pedido)pedidoService.procuraById(this.codigoPedido);
		PedidoDePizzaFacade pedidoDePizzaService = new PedidoDePizzaFacadeImpl();
			
		ArrayList<PedidoDePizza> pedidosDePizza = (ArrayList<PedidoDePizza>)pedidoDePizzaService.procuraByCodigoPedido(pedido.getCodigo());
			
		session.setAttribute("pedidosDePizza",pedidosDePizza);
		session.setAttribute("pedido", pedido);
			
	    rp.sendRedirect(rq.getContextPath() + "/pages/empresa/pedido.jsf");
	
	}
	
	public void clienteEscolheConsultarPedido() throws IOException
	{		
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		Long situacao = pedidoService.consultaSituacao(this.codigoPedido);
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        
		Pedido pedido = (Pedido)pedidoService.procuraById(this.codigoPedido);
		PedidoDePizzaFacade pedidoDePizzaService = new PedidoDePizzaFacadeImpl();
			
		ArrayList<PedidoDePizza> pedidosDePizza = (ArrayList<PedidoDePizza>)pedidoDePizzaService.procuraByCodigoPedido(pedido.getCodigo());
			
		session.setAttribute("pedidosDePizza",pedidosDePizza);
		session.setAttribute("pedido", pedido);
			
	    rp.sendRedirect(rq.getContextPath() + "/pages/cliente/pedidoConsulta.jsf");
	
	}
	
	
	public String save() throws IOException
	{				
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(session.getAttribute("pedidosDePizza")!=null)
		{
			ArrayList<PedidoDePizza> pedidosDePizza = (ArrayList<PedidoDePizza>)session.getAttribute("pedidosDePizza");
			
							
			PedidoFacade pedidoService = new PedidoFacadeImpl();
			this.pedido = (Pedido)session.getAttribute("pedido");

			Date data = new Date(System.currentTimeMillis());
	        SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm");
	        this.pedido.setHora((formataHora.format(data)));
			
			pedidoService.salva(this.pedido);	
			Long codigo = pedidoService.procuraUltimo(this.pedido.getCliente().getCodigo());
			this.pedido.setCodigo(codigo);
				
			PedidoDePizza pedidoDePizza = new PedidoDePizza();		
				
			PedidoDePizzaFacade pedidoDePizzaService = new PedidoDePizzaFacadeImpl();
				
			int tamanho = pedidosDePizza.size();			 
				
			for(int i = 0;i<tamanho;i++)
			{
			   pedidoDePizza = pedidosDePizza.get(i);
			   pedidoDePizza.setPedido(this.pedido);
			   pedidoDePizzaService.salva(pedidoDePizza);				
			}
				
			ArrayList<PedidoDePizza> novoPedidosDePizza = new ArrayList<PedidoDePizza>();
			Pedido pedido = new Pedido();
			session.setAttribute("pedidosDePizza", novoPedidosDePizza);
			session.setAttribute("pedido", pedido);
				
			HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		        
		    rp.sendRedirect(rq.getContextPath() + "/pages/cliente/consultaPedido.jsf");
			
		}else
		 {
			FacesMessage facesMessage = new FacesMessage("Inclua uma pizza ao pedido.");
			context.addMessage(null, facesMessage);
			return null;
		 }
		
			
		return "cadastraSucesso";
	}
	
	public String update() throws IOException
	{				
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(session.getAttribute("pedidosDePizza")!=null)
		{
			ArrayList<PedidoDePizza> pedidosDePizza = (ArrayList<PedidoDePizza>)session.getAttribute("pedidosDePizza");
			ArrayList<PedidoDePizza> pedidosDePizzaAntigo = (ArrayList<PedidoDePizza>)session.getAttribute("pedidosDePizzaAntigo");
							
			PedidoFacade pedidoService = new PedidoFacadeImpl();
			this.pedido = (Pedido)session.getAttribute("pedido");
			
			Date data = new Date(System.currentTimeMillis());
	        SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm");
	        this.pedido.setHora((formataHora.format(data)));			
			
			pedidoService.atualiza(this.pedido);	
				
			PedidoDePizza pedidoDePizza = new PedidoDePizza();		
				
			PedidoDePizzaFacade pedidoDePizzaService = new PedidoDePizzaFacadeImpl();
				
			int tamanho = pedidosDePizzaAntigo.size();			 
				
			for(int i = 0;i<tamanho;i++)
			{
			   pedidoDePizzaService.remove(pedidosDePizzaAntigo.get(i));				
			}
			
			tamanho = pedidosDePizza.size();			 
			
			for(int i = 0;i<tamanho;i++)
			{
			   pedidoDePizza = pedidosDePizza.get(i);
			   pedidoDePizza.setPedido(this.pedido);
			   pedidoDePizzaService.salva(pedidoDePizza);				
			}
				
			ArrayList<PedidoDePizza> novoPedidosDePizza = new ArrayList<PedidoDePizza>();
			Pedido pedido = new Pedido();
			session.setAttribute("pedidosDePizza", novoPedidosDePizza);
			session.setAttribute("pedido", pedido);
				
			HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		        
		    rp.sendRedirect(rq.getContextPath() + "/pages/cliente/consultaPedido.jsf");
			
		}else
		 {
			FacesMessage facesMessage = new FacesMessage("Inclua uma pizza ao pedido.");
			context.addMessage(null, facesMessage);
			return null;
		 }		
			
		return "atualizaSucesso";
	}
	

	public String delete(){
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		this.pedido.setCodigo(id);
		this.pedido.setCliente(null);
		this.pedido.setEmpresa(null);
		pedidoService.remove(this.pedido);
		this.pedido = new Pedido(); 
		
		return "removeSucesso";
	}
	
	public String merge(){
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		pedidoService.atualiza(this.pedido);
		this.pedido = new Pedido(); 

		return "atualizaSucesso";
	}
	
	public String load(){
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		this.pedido = pedidoService.procura(this.id);
		
		return "pesquisaSucesso";
	}
	
	public List<Pedido> getPedidos(){
		PedidoFacade pedidoService = new PedidoFacadeImpl();		
		
		return pedidoService.lista();
	}
	
	public ArrayList<Pedido> getMeusPedidosCliente(){
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		
		Cliente cliente = (Cliente)session.getAttribute("clienteLogado");
		
		return (ArrayList<Pedido>)pedidoService.pesquisaByCodigoCliente(cliente.getCodigo());
	}
	
	public ArrayList<Pedido> getMeusPedidosEmpresa(){
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		
		Empresa empresa = (Empresa)session.getAttribute("empresaLogado");
		
		return (ArrayList<Pedido>)pedidoService.pesquisaByCodigoEmpresa(empresa.getCodigo());
	}
	
public ArrayList<Pedido> getMeusUltimosPedidos(){
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		
		Empresa empresa = (Empresa)session.getAttribute("empresaLogado");
		
		return (ArrayList<Pedido>)pedidoService.pesquisaByUltimosPedidos(empresa.getCodigo());
	}
	
	public static void main(String[] args)
	{
	
		
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		
		ArrayList<Pedido> pedidos = (ArrayList<Pedido>) pedidoService.pesquisaByUltimosPedidos(1l);
		
		int tamanho = pedidos.size();
		
		for(int i=0;i<tamanho;i++)
		{
			System.out.println("Código: "+pedidos.get(i).getCodigo());
			System.out.println("Valor: "+pedidos.get(i).getValor());
			System.out.println("Cliente: "+pedidos.get(i).getCliente().getNome());
			System.out.println("Pizzaria: "+pedidos.get(i).getEmpresa().getNome_fantasia());
			
		}
		
	}
	
	public Pedido getPedidoAtual()
	{
		
   	   HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	   Pedido pedidoAtual = new Pedido();
	   pedidoAtual = (Pedido)session.getAttribute("pedido");
		
		return pedidoAtual;
	}
	
	public List<Pizza> getPizzasDaPizzaria()
	{
		PizzaFacade pizzaService = new PizzaFacadeImpl();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.pedido = (Pedido)session.getAttribute("pedido");
		
		return (List)pizzaService.pesquisaPizzasByCodigoEmpresa(this.pedido.getEmpresa().getCodigo());		
	}
	
	@SuppressWarnings("unchecked")
	public void escolhePizza() throws IOException
	{	
		Pizza pizza = new Pizza();
		PizzaFacade pizzaService = new PizzaFacadeImpl();
		pizza = pizzaService.procuraById(codigoPizza);
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		if(session.getAttribute("pedidosDePizza")!=null)
		{				
					
			ArrayList<PedidoDePizza> pedidoDePizza = (ArrayList<PedidoDePizza>)session.getAttribute("pedidosDePizza");
			
			int tamanho = pedidoDePizza.size();
			
			for(int i = 0;i<tamanho;i++)
			{
				this.pedidosDePizza.add(pedidoDePizza.get(i));
			}
		}	
		PedidoDePizza pedidoDePizza = new PedidoDePizza();
		pedidoDePizza.setPizza(pizza);
		pedidoDePizza.setQuantidade(this.quantidade);
		this.pedidosDePizza.add(pedidoDePizza);
		
		session.setAttribute("pedidosDePizza", this.pedidosDePizza);
		
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        Integer tipoPagina = (Integer)session.getAttribute("tipoPagina");
        
        if(tipoPagina==1)
        {
        	rp.sendRedirect(rq.getContextPath() + "/pages/cliente/pedido.jsf");
        }else
         if(tipoPagina==2)
         {
        	 rp.sendRedirect(rq.getContextPath() + "/pages/cliente/alteraPedido.jsf"); 
         }	
	}
	
	public Float getCalcularPreco() throws IOException
	{	
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		Float soma = new Float(0);
		
		if(session.getAttribute("pedidosDePizza")!=null)
		{				
					
			ArrayList<PedidoDePizza> pedidosDePizza = (ArrayList<PedidoDePizza>)session.getAttribute("pedidosDePizza");
			
			int tamanho = pedidosDePizza.size();			 
			Float valor = new Float(0);
			
			for(int i = 0; i < tamanho; i++)
			{
				Pizza pizza = (Pizza) pedidosDePizza.get(i).getPizza();
				Integer quantidade = 0; 
				quantidade = (Integer) pedidosDePizza.get(i).getQuantidade();
				valor = pizza.getValor();
				soma = soma + (valor * quantidade);
			}			
		}	
		
		this.pedido = (Pedido)session.getAttribute("pedido");
		this.pedido.setValor(soma);
		
		return this.pedido.getValor();
	}
	
	public void calcularTroco() throws IOException
	{
		FacesMessage facesMessage = new FacesMessage();		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		this.pedido = (Pedido)session.getAttribute("pedido");
		
		if((this.pedido.getValor()==null)||(this.pedido.getValor()==0f))
		{
			facesMessage.setSummary("Inclua uma pizza ao pedido.");
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
		}else
		{
			if(this.pedido.getPagamento()!=null)
			{
			
				if(this.pedido.getPagamento()<0f)
				{
					facesMessage.setSummary("Informe um valor maior que zero.");
				
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, facesMessage);
				}else
				 {
					if(this.pedido.getPagamento()==0f)
					{
						facesMessage.setSummary("Informe o valor à pagar.");
					
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, facesMessage);
					}else
				     {
						Float troco = this.pedido.getPagamento()-this.pedido.getValor();
					
						if(troco<0f)
						{
							facesMessage.setSummary("O pagamento deve ser maior que o valor do pedido.");
						
							FacesContext context = FacesContext.getCurrentInstance();
							context.addMessage(null, facesMessage);
						}else
						 {
							this.pedido.setTroco(troco);
							this.pedido.setPagamento(this.pedido.getPagamento());
							session.setAttribute("pedido", this.pedido);
							HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
							HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
						        
							 Integer tipoPagina = (Integer)session.getAttribute("tipoPagina");
						        
						        if(tipoPagina==1)
						        {
						        	rp.sendRedirect(rq.getContextPath() + "/pages/cliente/pedido.jsf");
						        }else
						         if(tipoPagina==2)
						         {
						        	 rp.sendRedirect(rq.getContextPath() + "/pages/cliente/alteraPedido.jsf"); 
						         }
						 }
				      }
				  }			
				
			
			 }else
			  {
				 if(this.pedido.getPagamento()==null)
				 {
					 facesMessage.setSummary("Informe o valor à pagar.");
			
					 FacesContext context = FacesContext.getCurrentInstance();
					 context.addMessage(null, facesMessage);
				 }
			  }
		}
	}
	
	@SuppressWarnings("unchecked")
	public void removePizza() throws IOException
	{
				
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.pedido = (Pedido)session.getAttribute("pedido");
		this.pedido.setPagamento(null);
        this.pedido.setTroco(null);
        session.setAttribute("pedido", this.pedido);
		
		if(session.getAttribute("pedidosDePizza")!=null)
		{				
			
			PedidoDePizza pedidoDePizza = new PedidoDePizza();
			ArrayList<PedidoDePizza> pedidosDePizza= (ArrayList<PedidoDePizza>)session.getAttribute("pedidosDePizza");
			
			int tamanho = pedidosDePizza.size();
			
			for(int i = 0;i<tamanho;i++)
			{
				if(!pedidosDePizza.get(i).getPizza().getCodigo().equals(codigoPizza))
				{
					this.pedidosDePizza.add(pedidosDePizza.get(i));
				}				
			}
			
			if(tamanho==1)
			{
				this.pedidosDePizza = null;
			}
		}	
		
		session.setAttribute("pedidosDePizza", this.pedidosDePizza);
		
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        Integer tipoPagina = (Integer)session.getAttribute("tipoPagina");
        
        if(tipoPagina==1)
        {
        	rp.sendRedirect(rq.getContextPath() + "/pages/cliente/pedido.jsf");
        }else
         if(tipoPagina==2)
         {
        	 rp.sendRedirect(rq.getContextPath() + "/pages/cliente/alteraPedido.jsf"); 
         }
        	
	}
	
	public void alteraSituacaoDoPedido()
	{
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		Long situacao = pedidoService.consultaSituacao(this.codigoPedido);
		
		//CONFIRMA PEDIDO
		if(this.codigoSituacao==2)
		{
			if(situacao!=1)
			{
				FacesMessage facesMessage = new FacesMessage();
				
				if(situacao==2)
				{
					facesMessage.setSummary("O pedido já foi confirmado.");
				}else
				 {
					if(situacao==3)
					{
						facesMessage.setSummary("O pedido já foi confirmado e está sendo preparado.");
					}else
					 {
						if(situacao==4)
						{
							facesMessage.setSummary("O pedido já foi confirmado e saiu para entrega.");
						}else
						 {
							if(situacao==5)
							{
								facesMessage.setSummary("O pedido já foi concluído.");
							}else
							 {
								if(situacao==6)
								{
									facesMessage.setSummary("O pedido já foi recusado e não pode ser confirmado.");
								}else
								 {
									if(situacao==7)
									{
										facesMessage.setSummary("O pedido já foi cancelado e não pode ser confirmado.");
									}
								 }
							 }
						 }
					 }			 	
				 }
				
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, facesMessage);
				
			}else
			{
				pedidoService.atualizaSituacao(this.codigoPedido,this.codigoSituacao);
			}
		}else
		 {
			//PREPARA PEDIDO
			if(this.codigoSituacao==3)
			{
				if(situacao!=2)
				{
					FacesMessage facesMessage = new FacesMessage();
					
					if(situacao==1)
					{
						facesMessage.setSummary("O pedido precisa ser confirmado.");
					}else
					 {
						if(situacao==3)
						{
							facesMessage.setSummary("O pedido já está sendo preparado.");
						}else
						 {
							if(situacao==4)
							{
								facesMessage.setSummary("O pedido foi preparado e já saiu para entrega.");
							}else
							 {
								if(situacao==5)
								{
									facesMessage.setSummary("O pedido já foi concluído.");
								}else
								 {
									if(situacao==6)
									{
										facesMessage.setSummary("O pedido foi recusado e não pode ser preparado.");
									}else
									 {
										if(situacao==7)
										{
											facesMessage.setSummary("O pedido foi cancelado e não pode ser preparado.");
										}
									 }
								 }
							 }
						 }			 	
					 }
					
					
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, facesMessage);
					
				}else
				{
					pedidoService.atualizaSituacao(this.codigoPedido,this.codigoSituacao);
				}
				
			}else
			 {
				//ENTREGA PEDIDO
				if(this.codigoSituacao==4)
				{
					if(situacao!=3)
					{
						FacesMessage facesMessage = new FacesMessage();
						
						if(situacao==1)
						{
							facesMessage.setSummary("O pedido precisa ser confirmado.");
						}else
						 {
							if(situacao==2)
							{
								facesMessage.setSummary("O pedido precisa ser preparado para ser entregue.");
							}else
							 {
								if(situacao==4)
								{
									facesMessage.setSummary("O pedido já saiu para entrega.");
								}else
								 {
									if(situacao==5)
									{
										facesMessage.setSummary("O pedido já foi concluído.");
									}else
									 {
										if(situacao==6)
										{
											facesMessage.setSummary("O pedido foi recusado e não pode ser entregue.");
										}else
										 {
											if(situacao==7)
											{
												facesMessage.setSummary("O pedido foi cancelado e não pode ser entregue.");
											}
										 }
									 }
								 }
							 }			 	
						 }
						
						
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, facesMessage);
						
					}else
					{
						pedidoService.atualizaSituacao(this.codigoPedido,this.codigoSituacao);
					}					
				}else
				 {
					//PEDIDO CONCLUÍDO
					if(this.codigoSituacao==5)
					{
						if(situacao!=4)
						{
							FacesMessage facesMessage = new FacesMessage();
							
							if(situacao==1)
							{
								facesMessage.setSummary("O pedido precisa ser confirmado.");
							}else
							 {
								if(situacao==2)
								{
									facesMessage.setSummary("O pedido precisa ser preparado..");
								}else
								 {
									if(situacao==3)
									{
										facesMessage.setSummary("O pedido precisa ser entregue.");
									}else
									 {
										if(situacao==5)
										{
											facesMessage.setSummary("O pedido já foi concluído.");
										}else
										 {
											if(situacao==6)
											{
												facesMessage.setSummary("O pedido foi recusado e não pode ser concluído.");
											}else
											 {
												if(situacao==7)
												{
													facesMessage.setSummary("O pedido foi cancelado e não pode ser concluído.");
												}
											 }
										 }
									 }
								 }			 	
							 }
							
							
							FacesContext context = FacesContext.getCurrentInstance();
							context.addMessage(null, facesMessage);
							
						}else
						{
							pedidoService.atualizaSituacao(this.codigoPedido,this.codigoSituacao);
						}
						
					}else
					 {
						//PEDIDO RECUSADO
						if(this.codigoSituacao==6)
						{
							if(situacao!=1)
							{
								FacesMessage facesMessage = new FacesMessage();
								
								if(situacao==2)
								{
									facesMessage.setSummary("O pedido já foi confirmado e não pode ser recusado.");
								}else
								 {
									if(situacao==3)
									{
										facesMessage.setSummary("O pedido está sendo preparado e não pode ser recusado.");
									}else
									 {
										if(situacao==4)
										{
											facesMessage.setSummary("O pedido saiu para entrega e não pode ser cancelado.");
										}else
										 {
											if(situacao==5)
											{
												facesMessage.setSummary("O pedido já foi concluído e não pode ser cancelado.");
											}else
											 {
												if(situacao==6)
												{
													facesMessage.setSummary("O pedido já foi recusado.");
												}else
												 {
													if(situacao==7)
													{
														facesMessage.setSummary("O pedido foi cancelado e não pode ser recusado.");
													}
												 }
											 }
										 }
									 }			 	
								 }
								
								
								FacesContext context = FacesContext.getCurrentInstance();
								context.addMessage(null, facesMessage);
								
							}else
							{
								pedidoService.atualizaSituacao(this.codigoPedido,this.codigoSituacao);
							}
							
						}else
						 {
							//PEDIDO CANCELADO
							if(this.codigoSituacao==7)
							{
								if(situacao!=1)
								{
									FacesMessage facesMessage = new FacesMessage();
									
									if(situacao==2)
									{
										facesMessage.setSummary("O pedido já foi confirmado e não pode ser cancelado.");
									}else
									 {
										if(situacao==3)
										{
											facesMessage.setSummary("O pedido está sendo preparado e não pode ser cancelado.");
										}else
										 {
											if(situacao==4)
											{
												facesMessage.setSummary("O pedido saiu para entrega e não pode ser cancelado.");
											}else
											 {
												if(situacao==5)
												{
													facesMessage.setSummary("O pedido já foi concluído e não pode ser cancelado.");
												}else
												 {
													if(situacao==6)
													{
														facesMessage.setSummary("O pedido já foi recusado e não pode ser cancelado.");
													}else
													 {
														if(situacao==7)
														{
															facesMessage.setSummary("O pedido já foi cancelado e não pode ser cancelado.");
														}
													 }
												 }
											 }
										 }			 	
									 }
									
									
									FacesContext context = FacesContext.getCurrentInstance();
									context.addMessage(null, facesMessage);
									
								}else
								{
									pedidoService.atualizaSituacao(this.codigoPedido,this.codigoSituacao);
								}								
							}
						 }
					 }
					
				 }
				
			 }
			
		 }
	}
	
	public List<PedidoDePizza> getListaDePizza()
	{			
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
		ArrayList<PedidoDePizza> pedidosDePizza= (ArrayList<PedidoDePizza>)session.getAttribute("pedidosDePizza");
		
        return pedidosDePizza;
	}	
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Long getCodigo() {
		return id;
	}

	public void setCodigo(Long id) {
		this.id = id;
	}
	
	public Long getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Long codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public ArrayList<Pizza> getPizza() {
		return pizzas;
	}

	public void setPizza(ArrayList<Pizza> pizza) {
		this.pizzas = pizza;
	}

	public Long getCodigoPizza() {
		return codigoPizza;
	}

	public void setCodigoPizza(Long codigoPizza) {
		this.codigoPizza = codigoPizza;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public Integer getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Integer codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}
	public List<SelectItem> getAnos() {
		if(this.anos == null)
		{
			this.anos = new ArrayList<SelectItem>();
			this.anos.add(new SelectItem(2011,"2011"));
			this.anos.add(new SelectItem(2012,"2012"));
			this.anos.add(new SelectItem(2013,"2013"));
			this.anos.add(new SelectItem(2014,"2014"));			
			this.anos.add(new SelectItem(2015,"2015"));
		}
		return anos;
	}

	public void setAnos(List<SelectItem> anos) {
		this.anos = anos;
	
}
	}