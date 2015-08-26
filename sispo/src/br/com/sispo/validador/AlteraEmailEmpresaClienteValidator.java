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

public class AlteraEmailEmpresaClienteValidator implements Validator {

	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String enteredEmail = (String) object;
		
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			Empresa empresa = (Empresa)session.getAttribute("empresaLogado");
			
			if(empresa.getEmail().equals(enteredEmail))
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