<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="stella" uri="http://stella.caelum.com.br/faces"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SISPO | Sistema de Pizzaria Online</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" type="text/css" href="/jsf/style/estilos.css"
	media="screen" />
<link rel="StyleSheet" type="text/css"
	href="/sispo/style/homeEstilo.css" media="screen" />

<script type="text/javascript"
	src="/sispo/resources/jquery.maskedinput-1.2.1.js">
	
</script>
<style type='text/css'>
#rodape01 {
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top: 150%;
	font-weight: bold;
	font-family: arial;
	color: #000000;
}
</style>
</head>

<body>
	<f:view>
		<div id="menuEmpresa">
			<h:form>
				<rich:messages style="color:red;" />
				<fieldset>
					<legend style="font-size: 20px;">Fale Conosco</legend>

					<a4j:region id="regiaoAjax">
						<a4j:status id="sts">
							<f:facet name="start">
								<h:graphicImage value="/images/ajax-loader.gif" />
							</f:facet>
						</a4j:status>
					</a4j:region>

					<h:messages globalOnly="true" style="color:Red;" />
					
					<h:panelGrid columns="2" id="obrigatorios">

						<h:outputLabel for="obrigatorio" value="* Campos Obrigatórios." />
						<h:inputHidden value="" />
					</h:panelGrid>	

					<h:panelGrid columns="2" id="inputs">

						<h:outputLabel for="remetente" value="Remetente*"  style="font-weight:bold;"/>
						<h:outputLabel for="remetente" value="#{empresaMB.cadastroEmpresa.nome_fantasia}" />
						
						
						<h:outputLabel for="email" value="E-mail*" style="font-weight:bold;" />
						<h:outputLabel for="email" value="#{empresaMB.cadastroEmpresa.email} " />

					</h:panelGrid>
					
					<h:outputLabel for="mensagem" value="Mensagem*" style="font-weight:bold;" />
					<h:inputHidden value="" />
					
					<h:panelGrid columns="2" id="inputMensagem">
					<h:inputTextarea id="msg" value="#{emailMB.mensagem}" title="Informe a mensagem." required="true" cols="43" rows="10" requiredMessage="Campo Mensagem obrigatório!" styleClass="edit">
						</h:inputTextarea>
						<h:message for="obs" errorClass="campoError" style="color:Red;" />
						<h:inputHidden value="" />
					</h:panelGrid>

					<a4j:commandButton value="Enviar Mensagem"
						action="#{emailMB.faleconosco}" status="sts" reRender="inputs,inputMensagem" styleClass="botoes" />

					<a4j:commandButton value="Voltar" immediate="true"
						action="toHomeEmpresa" styleClass="botoes" />

				</fieldset>



				<fieldset style="background-color: #D9FFD9; border: 0;">
					<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
				</fieldset>
			</h:form>
		</div>
		<div id="testa">
			<h:outputText>
				<a style="text-decoration: none; color: white"
					href="/sispo/pages/empresa/homeEmpresa.jsf">HOME EMPRESA</a> |</h:outputText>
		</div>
		<div id="sair">
			<h:form>
				<h:panelGrid>
					<h:commandLink style="text-decoration: none;color: white"
						value="Sair" action="#{usuarioMB.logout}" />
				</h:panelGrid>
			</h:form>
		</div>
	</f:view>
</body>
</html>