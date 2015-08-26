<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SISPO | Sistema de Pizzaria Online</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" type="text/css" href="/sispo/style/estilos.css"
	media="screen" />
<link rel="StyleSheet" type="text/css"
	href="/sispo/style/homeEstilo.css" media="screen" />
<script type="text/javascript"
	src="/jsf/resources/jquery.maskedinput-1.2.1.js"></script>

<style type='text/css'>
#rodape01
{
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top:92%;
	font-weight: bold;
    font-family: arial;
    color: #000000;
}
</style> 
</head>
<title>Login</title>
<body>
	<f:view>
		
		<div style="left: 50%;position: absolute;top: 45%;" id="linha">
			<hr size="200" width="1" align="left">
		</div>
		
		<div style="left: 10%;position: absolute;top: 55%;" id="links">
			<div style="font-size: 20px;">Esqueceu sua senha?</div>			
			<a href="/sispo/pages/utilitario/lembrarSenha.jsf"><div style="font-size: 11px;">Receba sua senha por E-mail</div></a>
			<br>
			<br>
			<div style="font-size: 20px;">Ainda não é cadastrado?</div>
			<a href="/sispo/pages/novoUsuario/tipoUsuario.jsf"><div style="font-size: 11px;">Faça seu cadastrado.</div></a>
		</div>
		
		<div id="erros" style="left: 65%;top:45%;position: absolute;">
			<h:panelGrid columns="1" border="0" width="230">
					<h:messages style="color:red;"/>
				</h:panelGrid>
		</div>
		
		<div id="menuLogin">
			<h:form>				
				
				<fieldset style="background-color:#D9FFD9;border: 0;">
					<legend style="color:Green;font-size: medium;font-weight: bold;">Entrar</legend>
					<h:panelGrid columns="2" border="0">
						<h:outputText value="Login" styleClass="rotulos" style="FONT-SIZE: small; font-weight:bold;"/>
						<h:inputText id="email" value="#{usuarioMB.usuario.login}" styleClass="edit" size="90" style=" width : 150px;" required="true" requiredMessage="Campo Usuário obrigatório!" >	
						</h:inputText>

						<h:outputLabel for="senha" value="Senha" styleClass="rotulos"  style="FONT-SIZE: small; font-weight:bold;"/>
						<h:inputSecret id="senha" styleClass="edit" validatorMessage="A senha deve ter no mínimo 6 caracteres!" value="#{usuarioMB.usuario.senha}" size="60" style=" width : 150px;" required="true" requiredMessage="Campo Senha obrigatório!" >
							<f:validateLength minimum="6" />
						</h:inputSecret>

						<h:commandButton id="botao" value="Entrar" action="#{usuarioMB.login}" />
						<br></br>												
					</h:panelGrid>
				</fieldset>				
			</h:form>
		</div>
		<div id="testa" >
			<h:outputText><a style="text-decoration: none;color: white"  href="/sispo/pages/usuario/paginaInicial.jsf">HOME</a> |</h:outputText>
		</div>	
		<div id="rodape01">
			<fieldset style="background-color:#D9FFD9;border: 0;">
				<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
			</fieldset>
		</div>
					
	</f:view>
</body>
</html>