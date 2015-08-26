package br.com.sispo.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sispo.controller.ClienteFacade;
import br.com.sispo.controller.ClienteFacadeImpl;
import br.com.sispo.controller.EmpresaFacade;
import br.com.sispo.controller.EmpresaFacadeImpl;
import br.com.sispo.controller.PedidoDePizzaFacade;
import br.com.sispo.controller.PedidoDePizzaFacadeImpl;
import br.com.sispo.controller.PedidoFacade;
import br.com.sispo.controller.PedidoFacadeImpl;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;
import br.com.sispo.model.entity.Pedido;
import br.com.sispo.model.entity.PedidoDePizza;
import br.com.sispo.util.CriptografaSenha;


public class EmpresaMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;

	private Empresa empresa = new Empresa();
	private static String imagePath;
	private Long id;
	private String confirmarSenha;
	private String senhaAtual;

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		
		this.senhaAtual = CriptografaSenha.md5(senhaAtual);
	}

	public EmpresaMB() {
		System.out
				.println(" >>>>>>>>>>>>>>>>>>>> Contrutor do Empresa_MB <<<<<<<<<<<<<<<<<<");

		if (this.empresa == null) {
			this.empresa = new Empresa();
		}
	}

	public String save() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		String senha = this.empresa.getUsuario().getSenha();
		this.empresa.setPizzaria_logo(getImagePath());

		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage(
					"A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}

		EmpresaFacade EmpresaService = new EmpresaFacadeImpl();
		this.empresa.getUsuario().setSenha(CriptografaSenha.md5(senha));
		
		EmpresaService.salva(this.empresa);
		this.empresa = new Empresa();

		HttpServletResponse rp = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		HttpServletRequest rq = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		rp.sendRedirect(rq.getContextPath()
				+ "/pages/novoUsuario/usuarioCadastrado.jsf");
		return "cadastraSucesso";
	}

	public String delete() throws IOException{
		EmpresaFacade empresaService = new EmpresaFacadeImpl();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.empresa = (Empresa)session.getAttribute("empresaLogado");
		empresaService.inativaEmpresa(this.empresa.getUsuario().getCodigo());
		this.empresa = new Empresa();
		
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     
		session.invalidate();
        rp.sendRedirect(rq.getContextPath() + "/pages/usuario/login.jsf");
		
		return "removeSucesso";
	}
	
	public String deleteEmpresas() throws IOException{
		EmpresaFacade empresaService = new EmpresaFacadeImpl();
		this.empresa.setCodigo(id);
		empresaService.inativaEmpresa(this.empresa.getUsuario().getCodigo());
		this.empresa = new Empresa();		
		
		return "removeSucesso";
	}

	public String update() throws IOException {
		EmpresaFacade EmpresaService = new EmpresaFacadeImpl();
		EmpresaService.atualiza(this.empresa);
		this.empresa = new Empresa();

		HttpServletResponse rp = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		HttpServletRequest rq = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		rp.sendRedirect(rq.getContextPath()
				+ "/pages/empresa/atualizaEmpresa.jsf");

		return "atualizaSucesso";
	}

	public String updateCadastro() throws IOException {
		EmpresaFacade empresaService = new EmpresaFacadeImpl();
		if(getImagePath()!=null)
		{
			this.getCadastroEmpresa().setPizzaria_logo(getImagePath());
		}		
		empresaService.atualiza(this.getCadastroEmpresa());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.setAttribute("empresaLogado", this.getCadastroEmpresa());
		this.empresa = new Empresa();
		
		HttpServletResponse rp = (HttpServletResponse) FacesContext
		.getCurrentInstance().getExternalContext().getResponse();
		HttpServletRequest rq = (HttpServletRequest) FacesContext
		.getCurrentInstance().getExternalContext().getRequest();
		rp.sendRedirect(rq.getContextPath() + "/pages/empresa/atualizaCadastroEmpresa.jsf");


		return "atualizaSucesso";
	}

	public String alterarSenha() throws IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		String senha = this.empresa.getUsuario().getSenha();

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		Empresa cadastroEmpresa = (Empresa) session
				.getAttribute("empresaLogado");

		String senhaCadastrada = cadastroEmpresa.getUsuario().getSenha();

		if (!senha.equals(this.confirmarSenha)
				|| (!senhaCadastrada.equals(this.senhaAtual))) {

			FacesMessage facesMessage = new FacesMessage();

			if (!senha.equals(this.confirmarSenha)) {
				facesMessage
						.setSummary("Nova senha não corresponde a confirmação.");
			} else {
				if (!senhaCadastrada.equals(this.senhaAtual)) {
					facesMessage
							.setSummary("Senha atual não foi confirmada corretamente.");
				}
			}

			context.addMessage(null, facesMessage);
			return null;
		}

		cadastroEmpresa.getUsuario().setSenha(CriptografaSenha.md5(
				this.empresa.getUsuario().getSenha()));
		this.empresa = cadastroEmpresa;

		EmpresaFacade empresaService = new EmpresaFacadeImpl();
		empresaService.atualiza(this.empresa);
		this.empresa = new Empresa();

		HttpServletResponse rp = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		HttpServletRequest rq = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		rp.sendRedirect(rq.getContextPath() + "/pages/empresa/alterarSenha.jsf");

		return "atualizaSucesso";
	}

	public String load() {
		EmpresaFacade EmpresaService = new EmpresaFacadeImpl();
		this.empresa = EmpresaService.procura(this.id);

		return "pesquisaSucesso";
	}

	public List<Empresa> getEmpresas() {
		EmpresaFacade empresaService = new EmpresaFacadeImpl();

		return (List)empresaService.pesquisaEmpresasAtivas();
	}

	public Empresa getCadastroEmpresa() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		Empresa cadastroEmpresa = new Empresa();
		cadastroEmpresa = (Empresa) session.getAttribute("empresaLogado");

		return cadastroEmpresa;
	}

	public void  uploadImage(UploadEvent evento) throws FileNotFoundException {
		FacesContext fc= FacesContext.getCurrentInstance();
		ServletContext sc= (ServletContext)fc.getExternalContext().getContext();
		String caminhoReal = sc.getRealPath("/");
		String extensao = "";
		UploadItem item = evento.getUploadItem();
		String fileName = item.getFileName();
		String ext[] = fileName.split("\\.");
		int i = ext.length;

		if (i > 1) {
			extensao = ext[i - 1];

		}
		Long tempo=System.currentTimeMillis();
		OutputStream out = new FileOutputStream(
				caminhoReal+"/ImagensCadastro/" + "img"
						+ tempo + "." + extensao);
		setImagePath("/ImagensCadastro/" + "img"
				+ tempo + "." + extensao);

		try {
			out.write(item.getData());
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void escolheConsultarPizzaria() throws IOException
	{		
		EmpresaFacade empresaService = new EmpresaFacadeImpl();
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        
		Empresa empresa = (Empresa)empresaService.procuraByCodigo(this.id);
			
		session.setAttribute("empresaConsultada",empresa);
			
	    rp.sendRedirect(rq.getContextPath() + "/pages/cliente/pizzaria.jsf");	
	}
	
	public Empresa getEmpresaConsultada()
	{
		
   	   HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	   Empresa empresaConsultada = new Empresa();
	   empresaConsultada = (Empresa)session.getAttribute("empresaConsultada");
		
		return empresaConsultada;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}
}