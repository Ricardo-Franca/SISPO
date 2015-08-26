package br.com.sispo.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sispo.controller.ClienteFacade;
import br.com.sispo.controller.ClienteFacadeImpl;
import br.com.sispo.controller.EmpresaFacade;
import br.com.sispo.controller.EmpresaFacadeImpl;
import br.com.sispo.controller.UsuarioFacade;
import br.com.sispo.controller.UsuarioFacadeImpl;
import br.com.sispo.email.AutenticaUsuario;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;
import br.com.sispo.model.entity.Usuario;
import br.com.sispo.util.CriptografaSenha;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;

	public static final String SERVIDOR_SMTP = "smtp.gmail.com";
	public static final String PORTA_SERVIDOR_SMTP = "465";
	public static final String CONTA_GMAIL = "sispo.suporte@gmail.com";
	public static final String SENHA_GMAIL = "projetosispo";

	private String de;
	private String para;
	private String assunto;
	private String mensagem;

	public void faleconosco() throws IOException {
		this.setAssunto("Fale Conosco.");
		this.setPara("sispo.suporte@gmail.com");

		HttpSession sessionVerificaUsuario = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Cliente cliente = (Cliente)sessionVerificaUsuario.getAttribute("clienteLogado");
		
		if(cliente!=null)
		{
			
			this.mensagem = "<html><body><table><tr><td><b>Usuario:</b></td><td>"+cliente.getUsuario().getLogin()+""+
							"</td></tr><tr><td><b>Email:</b></td><td>"+cliente.getEmail()+"</td></tr><tr>" +
							"<td><b>Tipo:</b></td><td>Cliente</td></tr><tr><td><b>Mensagem</b></td></tr></table>" +
							"<table border='1' width='500' height='100' cellspacing='0' cellpadding='0'><tr>" +
						    "<td>"+this.mensagem+"</td></tr></table></body></html>";			
			
			this.setDe(cliente.getEmail());			
			
		}else
		 {
			Empresa empresa = (Empresa)sessionVerificaUsuario.getAttribute("empresaLogado");
			
			if(empresa!=null)
			{
				this.mensagem = "<html><body><table><tr><td><b>Usuario:</b></td><td>"+empresa.getUsuario().getLogin()+""+
				"</td></tr><tr><td><b>Email:</b></td><td>"+empresa.getEmail()+"</td></tr><tr>" +
				"<td><b>Tipo:</b></td><td>Empresa</td></tr><tr><td><b>Mensagem</b></td></tr></table>" +
				"<table border='1' width='500' height='100' cellspacing='0' cellpadding='0'><tr>" +
			    "<td>"+this.mensagem+"</td></tr></table></body></html>";
				
				this.setDe(empresa.getEmail());
			}
		 }

		FacesContext context = FacesContext.getCurrentInstance();
		AutenticaUsuario autenticaUsuario = new AutenticaUsuario(
				EmailMB.CONTA_GMAIL, EmailMB.SENHA_GMAIL);
		Session session = Session.getDefaultInstance(this.configuracaoEmail(),
				autenticaUsuario);
		// Habilita o LOG das ações executadas durante o envio de email
		session.setDebug(true);

		try {

			Transport envio = null;

			MimeMessage email = new MimeMessage(session);
			email.setRecipient(Message.RecipientType.TO, new InternetAddress(
					this.para));
			email.setFrom(new InternetAddress(this.de));
			email.setSubject(this.assunto);
			email.setContent(this.mensagem, "text/html");
			email.setSentDate(new Date());

			envio = session.getTransport("smtp");
			envio.connect(EmailMB.SERVIDOR_SMTP, EmailMB.CONTA_GMAIL,
					EmailMB.SENHA_GMAIL);

			email.saveChanges();

			envio.sendMessage(email, email.getAllRecipients());
			envio.close();
			
			this.mensagem = "";
			context.addMessage(null, new FacesMessage(
					"Sua mensagem foi enviado com sucesso."));

		} catch (AddressException e) {
			FacesMessage msg = new FacesMessage(
					"Erro ao montar mensagem de e-mail! Erro:" + e.getMessage());
			context.addMessage(null, msg);
			return;
		} catch (MessagingException e) {
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro:"
					+ e.getMessage());
			context.addMessage(null, msg);
			return;
		}

	}

	public void lembrarSenha() throws IOException {
		
		this.setDe("sispo.suporte@gmail.com");
		this.setAssunto("Lembrete de senha.");
	

		ClienteFacade clienteService = new ClienteFacadeImpl();
		Cliente cliente = new Cliente();
		cliente = clienteService.procuraByEmail(this.para);
		
		String novaSenha;

		if (cliente != null) {
			UUID uuid = UUID.randomUUID();    
		    String senhaAleatoria = uuid.toString();    
		     novaSenha=senhaAleatoria.substring(0,6);   
		    cliente.getUsuario().setSenha(CriptografaSenha.md5(novaSenha));
		    
			clienteService.atualiza(cliente);
			this.setMensagem("Olá " + cliente.getUsuario().getLogin() + "!<br/>"
					+ "Sua senha de acesso ao sispo é "
					+ novaSenha+ ".");
		}
		else {
			EmpresaFacade empresaService = new EmpresaFacadeImpl();
			Empresa empresa = new Empresa();
			empresa = empresaService.procuraByEmail(this.para);

			if (empresa != null) {
				UUID uuid = UUID.randomUUID();    
			    String senhaAleatoria = uuid.toString();    
			     novaSenha=senhaAleatoria.substring(0,6);   
			    empresa.getUsuario().setSenha(CriptografaSenha.md5(novaSenha));
				empresaService.atualiza(empresa);
				this.setMensagem("Olá " + empresa.getUsuario().getLogin() + "!<br/>"
						+ "Sua senha de acesso ao sispo é "
						+novaSenha+ ".");
			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		AutenticaUsuario autenticaUsuario = new AutenticaUsuario(
				EmailMB.CONTA_GMAIL, EmailMB.SENHA_GMAIL);
		Session session = Session.getDefaultInstance(this.configuracaoEmail(),
				autenticaUsuario);
		// Habilita o LOG das ações executadas durante o envio de email
		session.setDebug(true);

		try {

			Transport envio = null;

			MimeMessage email = new MimeMessage(session);
			email.setRecipient(Message.RecipientType.TO, new InternetAddress(
					this.para));
			email.setFrom(new InternetAddress(this.de));
			email.setSubject(this.assunto);
			email.setContent(this.mensagem, "text/html");
			email.setSentDate(new Date());

			envio = session.getTransport("smtp");
			envio.connect(EmailMB.SERVIDOR_SMTP, EmailMB.CONTA_GMAIL,
					EmailMB.SENHA_GMAIL);

			email.saveChanges();

			envio.sendMessage(email, email.getAllRecipients());
			envio.close();

			context.addMessage(null, new FacesMessage(
					"O lembrete de senha foi enviado ao e-mail informado"));

		} catch (AddressException e) {
			FacesMessage msg = new FacesMessage(
					"Erro ao montar mensagem de e-mail! Erro:" + e.getMessage());
			context.addMessage(null, msg);
			return;
		} catch (MessagingException e) {
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro:"
					+ e.getMessage());
			context.addMessage(null, msg);
			return;
		}
	}

	public Properties configuracaoEmail() {
		Properties config = new Properties();

		//******************************
	    //Configuração adicional para servidor proxy.
		//Para ativar basta descomentar...
		
		//config.setProperty("proxySet","true");
		//config.setProperty("socksProxyHost","127.0.0.1"); //IP do Servidor Proxy
		//config.setProperty("socksProxyPort","8080"); // Porta do servidor Proxy		
		
		//******************************
		
		
		config.put("mail.transport.protocol", "smtp");// define protocolo de
														// envio como SMTP
		config.put("mail.smtp.starttls.enable", "true");
		config.put("mail.smtp.host", SERVIDOR_SMTP);// servidor SMTP do GMAIL
		config.put("mail.smtp.auth", "true");// ativa autenticação
		config.put("mail.smtp.user", EmailMB.CONTA_GMAIL);// conta que está
															// enviando o email
		config.put("mail.debug", "true");
		config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);// porta
		config.put("mail.smtp.socketFactory.port", PORTA_SERVIDOR_SMTP);// Porta
																		// para
																		// o
																		// socket
		config.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		config.put("mail.smtp.socketFactory.fallback", "false");
		return config;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}