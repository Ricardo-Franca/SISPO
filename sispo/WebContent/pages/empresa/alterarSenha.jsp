<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="stella" uri="http://stella.caelum.com.br/faces"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<title>SISPO | Sistema de Pizzaria Online</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="StyleSheet" type="text/css" href="/jsf/style/estilos.css" media="screen" />
	<link rel="StyleSheet" type="text/css" href="/sispo/style/homeEstilo.css" media="screen" />
	<script type="text/javascript" src="/sispo/resources/jquery.maskedinput-1.2.1.js">
</script>  

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>

</script>
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
<body>

<f:view>
	<div align="left" id="menuCliente">
	<h:form>
	   <rich:messages style="color:Red;" />
		<a4j:keepAlive beanName="empresaMB" />
			<fieldset><legend>Alterar Senha</legend> 
			<h:panelGrid columns="3" id="inputs">			
				
				<h:outputLabel for="us" value="Usuário :"/>										

				<h:outputLabel for="nome" value="#{empresaMB.cadastroEmpresa.nome_fantasia}">				
				</h:outputLabel>
				<h:outputLabel for="us" value=""/>				
				
				<h:outputLabel for="senhaatual" value="Senha Atual *" />
						<h:inputSecret id="senhaatual" value="#{empresaMB.senhaAtual}" required="true" requiredMessage="Campo senha atual obrigatório!" styleClass="edit" size="30">
						</h:inputSecret>
						<h:inputHidden value="" />
						
						<h:outputLabel for="novaSenha" value=" Nova Senha *" />
						<h:inputSecret id="novaSenha" value="#{empresaMB.empresa.usuario.senha}" onblur="validaSenha(novaSenha,confirmarSenha);" required="true" requiredMessage="Campo nova senha obrigatório!" styleClass="edit" size="30">
						</h:inputSecret>
						<h:inputHidden value="" />
					
					<h:outputLabel for="confirmarSenha" value=" Confirme a Senha *" />
						<h:inputSecret id="confirmarSenha" value="#{empresaMB.confirmarSenha}"  required="true" requiredMessage="Campo confirme a senha obrigatório!" styleClass="edit" size="30">
						</h:inputSecret>
					<h:inputHidden value="" />
						
							
			</h:panelGrid>	
		</fieldset>
		
				<a4j:commandButton value="Alterar" action="#{empresaMB.alterarSenha}"  
							status="sts" reRender="inputs" styleClass="botoes" />
				<a4j:commandButton value="Voltar" immediate="true" action="toHomeEmpresa" styleClass="botoes" />		
		<br><br><br><br><br><br><br>
		<fieldset style="background-color:#D9FFD9;border: 0;">
			<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
		</fieldset>
	</h:form>
	</div>
	<div id="testa" >
	   <h:outputText><a style="text-decoration: none;color: white"  href="/sispo/pages/empresa/homeEmpresa.jsf">HOME EMPRESA</a> |</h:outputText>
	</div>
	<div id="sair" >
			<h:form>
				<h:panelGrid>
					<h:commandLink style="text-decoration: none;color: white" value="Sair" action="#{usuarioMB.logout}" />
				</h:panelGrid>
			</h:form>
		</div>
</f:view>
</body>
</html>