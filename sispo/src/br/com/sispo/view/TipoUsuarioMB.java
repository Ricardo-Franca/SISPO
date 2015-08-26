package br.com.sispo.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sispo.controller.TipoUsuarioFacade;
import br.com.sispo.controller.TipoUsuarioFacadeImpl;
import br.com.sispo.controller.UsuarioFacade;
import br.com.sispo.controller.UsuarioFacadeImpl;
import br.com.sispo.model.entity.TipoUsuario;
import br.com.sispo.model.entity.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class TipoUsuarioMB implements Serializable {

	private static final long serialVersionUID = 9186380132421958551L;

	private TipoUsuario tipoUsuario = new TipoUsuario();

	private Long id;

	public TipoUsuarioMB(){
		System.out.println(" >>>>>>>>>>>>>>>>>>>> Contrutor do TIPOUSUARIO_MB <<<<<<<<<<<<<<<<<<");
		
		if(this.tipoUsuario == null){
			this.tipoUsuario = new TipoUsuario(); 
		}
	}
	
	public String save(){
		TipoUsuarioFacade tipoUsuarioService = new TipoUsuarioFacadeImpl();
			
		tipoUsuarioService.salva(this.tipoUsuario);
		
		this.tipoUsuario = new TipoUsuario(); 
		return "cadastraSucesso";
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}	
}