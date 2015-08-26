package br.com.sispo.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.sispo.controller.UsuarioFacade;
import br.com.sispo.controller.UsuarioFacadeImpl;
import br.com.sispo.model.entity.Usuario;

public class LoginValidator implements Validator {

	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String login = (String) object;

		UsuarioFacade usuarioService = new UsuarioFacadeImpl();
		Usuario usuario = new Usuario();
		usuario = usuarioService.pesquisaUsuarioByLogin(login);

		if (usuario!=null) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Login "+login+" já cadastrado!");
			message.setSummary("Login "+login+" já cadastrado!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}