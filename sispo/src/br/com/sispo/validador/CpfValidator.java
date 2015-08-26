package br.com.sispo.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.sispo.controller.ClienteFacade;
import br.com.sispo.controller.ClienteFacadeImpl;
import br.com.sispo.model.entity.Cliente;

public class CpfValidator implements Validator {

	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String cpf = (String) object;

		ClienteFacade clienteService = new ClienteFacadeImpl();
		Cliente cliente = new Cliente();
		cliente = clienteService.procuraByCpf(cpf);

		if (cliente!=null) {
			FacesMessage message = new FacesMessage();
			message.setDetail("CPF "+cpf+" já cadastrado!");
			message.setSummary("CPF "+cpf+" já cadastrado!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}