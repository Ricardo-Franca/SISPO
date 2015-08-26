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

public class AlteraEmailClienteEmpresaValidator implements Validator {

	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String enteredEmail = (String) object;
		
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			Cliente cliente = (Cliente)session.getAttribute("clienteLogado");
			
			if(cliente.getEmail().equals(enteredEmail))
			{				
			}else
			 {
			
			    EmpresaFacade empresaService = new EmpresaFacadeImpl();
			    Empresa testaEmpresa = empresaService.procuraByEmail(enteredEmail);			
			
			    if (testaEmpresa!=null) 
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