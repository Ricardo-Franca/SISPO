package br.com.sispo.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import br.com.sispo.controller.ClienteFacade;
import br.com.sispo.controller.ClienteFacadeImpl;
import br.com.sispo.controller.EmpresaFacade;
import br.com.sispo.controller.EmpresaFacadeImpl;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;

public class AlteraEmailClienteValidator implements Validator {

	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String enteredEmail = (String) object;
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given string with the pattern
		Matcher m = p.matcher(enteredEmail);

		// Check whether match is found
		boolean matchFound = m.matches();

		if (!matchFound) {
			FacesMessage message = new FacesMessage();
			message.setDetail("E-mail incorreto!");
			message.setSummary("E-mail incorreto!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}else
		 {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			Cliente cliente = (Cliente)session.getAttribute("clienteLogado");
			
			if(cliente.getEmail().equals(enteredEmail))
			{				
			}else
			 {
			
			    ClienteFacade clienteService = new ClienteFacadeImpl();
			    Cliente testaCliente = clienteService.procuraByEmail(enteredEmail);			
			
			    if (testaCliente!=null) 
			    {
				   FacesMessage message = new FacesMessage();
				   message.setDetail("E-mail "+enteredEmail+" já cadastrado!");
				   message.setSummary("E-mail "+enteredEmail+" já cadastrado!");
				   message.setSeverity(FacesMessage.SEVERITY_ERROR);
				   throw new ValidatorException(message);
			    }
			}
		 }
	}
}