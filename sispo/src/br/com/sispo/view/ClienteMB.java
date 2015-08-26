package br.com.sispo.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.apache.catalina.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;

import br.com.sispo.controller.ClienteFacade;
import br.com.sispo.controller.ClienteFacadeImpl;
import br.com.sispo.model.dao.DAO;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Usuario;
import br.com.sispo.util.CriptografaSenha;

public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;

	private Cliente cliente = new Cliente();

	private Long id;
	private String confirmarSenha;
	private String senhaAtual;

	public ClienteMB(){
		System.out.println(" >>>>>>>>>>>>>>>>>>>> Contrutor do CLIENTE_MB <<<<<<<<<<<<<<<<<<");
		
		if(this.cliente == null){
			this.cliente = new Cliente(); 			
		}
	}	
	
	public String save() throws IOException
	{				
		FacesContext context = FacesContext.getCurrentInstance();
		String senha = this.cliente.getUsuario().getSenha();
		
		if(!senha.equals(this.confirmarSenha))
		{
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}
		
		ClienteFacade clienteService = new ClienteFacadeImpl();		
		this.cliente.getUsuario().setSenha(CriptografaSenha.md5(senha));
		clienteService.salva(this.cliente);		
		this.cliente = new Cliente();
		
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        rp.sendRedirect(rq.getContextPath() + "/pages/novoUsuario/usuarioCadastrado.jsf");
		return "cadastraSucesso";
	}

	public String delete() throws IOException{
		ClienteFacade clienteService = new ClienteFacadeImpl();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.cliente = (Cliente)session.getAttribute("clienteLogado");
		clienteService.inativaCliente(this.cliente.getUsuario().getCodigo());
		this.cliente = new Cliente();
		
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     
		session.invalidate();
        rp.sendRedirect(rq.getContextPath() + "/pages/usuario/login.jsf");
		
		return "removeSucesso";
	}
	
	public String deleteClientes() throws IOException{
		ClienteFacade clienteService = new ClienteFacadeImpl();
		this.cliente.setCodigo(id);
		clienteService.inativaCliente(this.cliente.getUsuario().getCodigo());
		this.cliente = new Cliente();
		
		
		return "removeSucesso";
	}
	
	public String update() throws IOException{
		ClienteFacade clienteService = new ClienteFacadeImpl();
		clienteService.atualiza(this.cliente);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("clienteLogado", this.cliente);
		this.cliente = new Cliente();

		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        rp.sendRedirect(rq.getContextPath() + "/pages/cliente/atualizaCliente.jsf");
		return "atualizaSucesso";
	}
	public String updateCadastro() throws IOException{
		ClienteFacade clienteService = new ClienteFacadeImpl();
		clienteService.atualiza(this.getCadastroCliente());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("clienteLogado", this.getCadastroCliente());
		this.cliente = new Cliente();

		HttpServletResponse rp = (HttpServletResponse) FacesContext
		.getCurrentInstance().getExternalContext().getResponse();
		HttpServletRequest rq = (HttpServletRequest) FacesContext
		.getCurrentInstance().getExternalContext().getRequest();
		rp.sendRedirect(rq.getContextPath() + "/pages/cliente/atualizaCadastroCliente.jsf");
		
		
		return "atualizaSucesso";
	}
	
	
	public String alterarSenha() throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		String senha = this.cliente.getUsuario().getSenha();
				
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Cliente cadastroCliente = (Cliente)session.getAttribute("clienteLogado");
		
		String senhaCadastrada = cadastroCliente.getUsuario().getSenha(); 
		
		if(!senha.equals(this.confirmarSenha)||(!senhaCadastrada.equals(this.senhaAtual)))
		{
			
			FacesMessage facesMessage = new FacesMessage();
			
			if(!senha.equals(this.confirmarSenha))
			{
				facesMessage.setSummary("Nova senha não corresponde a confirmação.");
			}else
			 {
				if(!senhaCadastrada.equals(this.senhaAtual))
				{
					facesMessage.setSummary("Senha atual não foi confirmada corretamente.");
				}
			 }
			
			context.addMessage(null, facesMessage);
			return null;
		}
			
		cadastroCliente.getUsuario().setSenha(CriptografaSenha.md5(this.cliente.getUsuario().getSenha()));
		this.cliente=cadastroCliente;
		
		ClienteFacade clienteService = new ClienteFacadeImpl();
		clienteService.atualiza(this.cliente);
		this.cliente = new Cliente();

		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        rp.sendRedirect(rq.getContextPath() + "/pages/cliente/alterarSenha.jsf");
		
		return "atualizaSucesso";
	}
	
	public String load(){
		ClienteFacade clienteService = new ClienteFacadeImpl();
		this.cliente = clienteService.procura(this.id);
		
		return "pesquisaSucesso";
	}
	
	public List<Cliente> getClientes(){
		ClienteFacade clienteService = new ClienteFacadeImpl();		
		
		return (List)clienteService.pesquisaClientesAtivos();
	}
	
	public Cliente getCadastroCliente(){
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Cliente cadastroCliente = new Cliente();
		cadastroCliente = (Cliente)session.getAttribute("clienteLogado");
		
		return cadastroCliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getCodigo() {
		return id;
	}

	public void setCodigo(Long id) {
		this.id = id;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual =CriptografaSenha.md5(senhaAtual);
	}
	
	
	
}