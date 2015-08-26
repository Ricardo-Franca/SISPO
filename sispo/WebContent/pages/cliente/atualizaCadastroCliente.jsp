<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="StyleSheet" type="text/css" href="/jsf/style/estilos.css"
	media="screen" />
<link rel="StyleSheet" type="text/css"
	href="/sispo/style/homeEstilo.css" media="screen" />
<script type="text/javascript"
	src="/sispo/resources/jquery.maskedinput-1.2.1.js">	
</script>
<style type='text/css'>
#rodape01
{
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top:140%;
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
				
				<fieldset>
					<legend>Atualização de Cadastro</legend>

					<h:panelGrid id="erros">
						<h:messages globalOnly="true" style="color:Red;"/>
					</h:panelGrid>

					<fieldset>
							<legend>Dados Pessoais</legend>
							
							<h:panelGrid columns="3" id="pessoais">
							
							<h:outputLabel for="nome" value="Nome " />
							<h:inputText id="nome" value="#{clienteMB.cadastroCliente.nome}" style=" width : 254px;" required="true" requiredMessage="Campo nome obrigatório!" styleClass="edit" size="30" />
							<h:message for="nome" errorClass="campoError" style="color:Red;"/>
							
							<h:outputLabel for="cpf" value="CPF " />
							<h:outputLabel id="cpf" value="#{clienteMB.cadastroCliente.cpf}" style=" width : 254px;" />
							<h:inputHidden value="" />
							
							<h:outputLabel for="telefone" value="Telefone" />
							<h:inputText id="telefone" value="#{clienteMB.cadastroCliente.telefone}" style=" width : 254px;" required="true" requiredMessage="Campo nome obrigatório!" styleClass="edit" size="11">
							<rich:jQuery selector="#telefone" query="mask('(99) 9999-9999')" timing="onload" />
							</h:inputText>
							<h:message for="telefone" errorClass="campoError" style="color:Red;"/>
							
							</h:panelGrid>							
					</fieldset>
					
					<fieldset>	
					<legend>Informações Endereço</legend>									
							
							<h:panelGrid columns="3" id="InfoEndereco">
							
							<h:outputLabel for="cep" value="CEP" />
							<h:inputText id="cep" value="#{clienteMB.cadastroCliente.endereco.cep}" required="true" requiredMessage="Campo cep obrigatório!" styleClass="edit" size="15">
							<rich:jQuery selector="#cep" query="mask('99999-999')" timing="onload"/>
							</h:inputText>
							<h:message for="cep" errorClass="campoError" style="color:Red;"/>
							
							<h:outputLabel for="endereco" value="Endereço" />
							<h:inputText id="endereco" value="#{clienteMB.cadastroCliente.endereco.endereco}" required="true" requiredMessage="Campo endereço obrigatório!" styleClass="edit" size="30">
							</h:inputText>
							<h:message for="endereco" errorClass="campoError" style="color:Red;"/>
														
							<h:outputLabel for="numero" value="Número" />
							<h:inputText id="numero" value="#{clienteMB.cadastroCliente.endereco.numero}" required="true" requiredMessage="Campo número obrigatório!" styleClass="edit" size="10">
							</h:inputText>
							<h:message for="numero" errorClass="campoError" style="color:Red;"/>
							
							<h:outputLabel for="complemento" value="Complemento" />
							<h:inputText id="complemento" value="#{clienteMB.cadastroCliente.endereco.complemento}" required="true" requiredMessage="Campo complemento obrigatório!" styleClass="edit" size="30">							
							</h:inputText>
							<h:message for="complemento" errorClass="campoError" style="color:Red;"/>
							
							<h:outputLabel for="uf" value="UF" />
							<h:selectOneMenu id="uf" value="#{clienteMB.cadastroCliente.endereco.uf.codigo}" required="true" requiredMessage="Campo uf obrigatório!">
							<f:selectItem itemValue="" itemLabel="UF"/>
							<f:selectItems value="#{ufMB.ufs}" />
							</h:selectOneMenu>
							<h:message for="uf" errorClass="campoError" style="color:Red;"/>
							
							<h:outputLabel for="cidade" value="Cidade" />
							<h:inputText id="cidade" value="#{clienteMB.cadastroCliente.endereco.cidade}" required="true" requiredMessage="Campo cidade obrigatório!" styleClass="edit" size="30">							
							</h:inputText>
							<h:message for="cidade" errorClass="campoError" style="color:Red;"/>
							
							</h:panelGrid>
					</fieldset>
					
					<fieldset>
							
							<legend>Acesso ao Sistema</legend>									
							
							<h:panelGrid columns="3" id="acesso">
						
							<h:outputLabel for="email" value="E-mail " />
							<h:inputText id="email" value="#{clienteMB.cadastroCliente.email}" required="true" requiredMessage="Campo e-mail obrigatório!" styleClass="edit" size="30">							
							<f:validator validatorId="alteraEmailClienteValidator" />
							<f:validator validatorId="alteraEmailClienteEmpresaValidator" />
							</h:inputText>
							<h:message for="email" errorClass="campoError" style="color:Red;"/>
							
							<h:outputLabel for="login" value="Login " />
							<h:outputLabel id="login" value="#{clienteMB.cadastroCliente.usuario.login}" style=" width : 254px;">							
							</h:outputLabel>
							<h:inputHidden value="" />
					
							</h:panelGrid>
					
					</fieldset>
							
					
					
					

						<a4j:commandButton action="#{clienteMB.updateCadastro}" value="Atualizar" styleClass="botoes" status="sts" reRender="pessoais,InfoEndereco,acesso,erros"/>
							 

						<a4j:commandButton value="Voltar" immediate="true" action="toHomeCliente" styleClass="botoes" />
				
				</fieldset>
				<fieldset style="background-color:#D9FFD9;border: 0;">
					<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
				</fieldset>
			</h:form>
		</div>
        <div id="testa" >
	   <h:outputText><a style="text-decoration: none;color: white"  href="/sispo/pages/cliente/homeCliente.jsf">HOME CLIENTE</a> |</h:outputText>
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