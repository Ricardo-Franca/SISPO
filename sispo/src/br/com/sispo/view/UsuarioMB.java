package br.com.sispo.view;

import java.io.IOException;
import br.com.sispo.util.CriptografaSenha;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sispo.controller.ClienteFacade;
import br.com.sispo.controller.ClienteFacadeImpl;
import br.com.sispo.controller.EmpresaFacade;
import br.com.sispo.controller.EmpresaFacadeImpl;
import br.com.sispo.controller.UsuarioFacade;
import br.com.sispo.controller.UsuarioFacadeImpl;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;
import br.com.sispo.model.entity.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;

	private Usuario usuario = new Usuario();

	private Long id;

	public UsuarioMB(){
		System.out.println(" >>>>>>>>>>>>>>>>>>>> Contrutor do USUARIO_MB <<<<<<<<<<<<<<<<<<");
		
		if(this.usuario == null){
			this.usuario = new Usuario(); 
	
			
		}
	}
	
	public String login() throws Exception{
        boolean administradorLogado = false;
        boolean empresaLogado = false;
        boolean clienteLogado = false;
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        UsuarioFacade usuarioService = new UsuarioFacadeImpl();
        FacesContext context = FacesContext.getCurrentInstance();
		
        administradorLogado = usuarioService.autentica(this.usuario.getLogin(),CriptografaSenha.md5( this.usuario.getSenha()),1,1);
        empresaLogado = usuarioService.autentica(this.usuario.getLogin(), CriptografaSenha.md5( this.usuario.getSenha()),2,1);
        clienteLogado = usuarioService.autentica(this.usuario.getLogin(), CriptografaSenha.md5( this.usuario.getSenha()),3,1);
           
        
        if(administradorLogado)
        {
            session.setAttribute("user", administradorLogado);
            rp.sendRedirect(rq.getContextPath() + "/pages/administrador/homeAdministrador.jsf");
            return "success";
        }else
         {
        	if(empresaLogado)
            {
                session.setAttribute("user", empresaLogado);
                Usuario usuarioLogado = new Usuario();
                UsuarioFacade uService = new UsuarioFacadeImpl();
                usuarioLogado = uService.pesquisaUsuarioByLogin(this.usuario.getLogin());
               
                Empresa empresaLog = new Empresa();
                EmpresaFacade eService = new EmpresaFacadeImpl();                
                empresaLog=eService.procuraByUsuarioId(usuarioLogado.getCodigo());
                
                session.setAttribute("empresaLogado", empresaLog);
                rp.sendRedirect(rq.getContextPath() + "/pages/empresa/homeEmpresa.jsf");
                return "success";
            }else
             {
            	if(clienteLogado)
                {
                    session.setAttribute("user", clienteLogado);
                    Usuario usuarioLogado = new Usuario();
                    UsuarioFacade uService = new UsuarioFacadeImpl();
                    usuarioLogado = uService.pesquisaUsuarioByLogin(this.usuario.getLogin());
                   
                    Cliente clienteLog = new Cliente();
                    ClienteFacade cService = new ClienteFacadeImpl();
                    clienteLog = cService.procuraByUsuarioId(usuarioLogado.getCodigo());
                    
                    session.setAttribute("clienteLogado", clienteLog);
                    
                    rp.sendRedirect(rq.getContextPath() + "/pages/cliente/homeCliente.jsf");
                    return "success";
                }else
                 {
                	
                 FacesMessage facesMessage = new FacesMessage("Usuário ou senha incorretos!");
           			context.addMessage(null, facesMessage);
                	session.setAttribute("user", null);
                    session.removeAttribute("user");
                   
                 
                 return null;
                  }
             }        	
         }       
    }
	
	public String logout() {
		HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.invalidate();
		try {
			rp.sendRedirect(rq.getContextPath() + "/pages/usuario/paginaInicial.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "logoutOk";
	}
	
	public String save(){
		UsuarioFacade usuarioService = new UsuarioFacadeImpl();
			
		usuarioService.salva(this.usuario);
		
		this.usuario = new Usuario(); 
		return "cadastraSucesso";
	}

	public String delete(){
		UsuarioFacade usuarioService = new UsuarioFacadeImpl();
		this.usuario.setCodigo(id);
		usuarioService.remove(this.usuario);
		this.usuario = new Usuario(); 
		
		return "removeSucesso";
	}
	
	public String merge(){
		UsuarioFacade usuarioService = new UsuarioFacadeImpl();
		usuarioService.atualiza(this.usuario);
		this.usuario = new Usuario(); 

		return "atualizaSucesso";
	}
	
	public String load(){
		UsuarioFacade usuarioService = new UsuarioFacadeImpl();
		this.usuario = usuarioService.procura(this.id);
		
		return "pesquisaSucesso";
	}
	
	public List<Usuario> getUsuarios(){
		UsuarioFacade usuarioService = new UsuarioFacadeImpl();		
		
		return usuarioService.lista();
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getCodigo() {
		return id;
	}

	public void setCodigo(Long id) {
		this.id = id;
	}
	
}