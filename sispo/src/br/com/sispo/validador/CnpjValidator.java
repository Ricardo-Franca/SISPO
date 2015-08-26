package br.com.sispo.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.sispo.controller.EmpresaFacade;
import br.com.sispo.controller.EmpresaFacadeImpl;
import br.com.sispo.model.entity.Empresa;

public class CnpjValidator implements Validator {

	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String cnpj = (String) object;

		EmpresaFacade empresaService = new EmpresaFacadeImpl();
		Empresa empresa = new Empresa();
		empresa = empresaService.procuraByCnpj(cnpj);

		if (empresa!=null) {
			FacesMessage message = new FacesMessage();
			message.setDetail("CNPJ "+cnpj+" já cadastrado!");
			message.setSummary("CNPJ "+cnpj+" já cadastrado!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}