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
		<a4j:keepAlive beanName="empresaMB" />
			<fieldset>
			<legend style="font-weight:bold;">Pizzaria</legend>
			<fieldset>
			<legend style="font-weight:bold;">Dados Pessoais</legend>   	    
            
            <h:panelGrid columns="2">
				<h:graphicImage value="#{empresaMB.empresaConsultada.pizzaria_logo}" height="100" width="100" />
				<h:inputHidden value="" />
			</h:panelGrid>
            
            <h:panelGrid columns="2">
				<h:outputLabel for="razaosocial" value="Razão Social:" style="font-weight:bold;"   />
				<h:outputLabel id="razaosocial" value="#{empresaMB.empresaConsultada.razao_social}" style=" width : 254px;"/>
			</h:panelGrid>			
                     
			<h:panelGrid columns="2">
				<h:outputLabel for="nomefantasia" value="Nome fantasia:" style="font-weight:bold;"   />
				<h:outputLabel id="nomefantasia" value="#{empresaMB.empresaConsultada.nome_fantasia}" style=" width : 254px;"/>
			</h:panelGrid>			
			</fieldset>
			
			<fieldset>
			<legend style="font-weight:bold;">Localização</legend>
			
			<h:panelGrid columns="2">
				<h:outputLabel for="endereco" value="Endereco:" style="font-weight:bold;"   />
				<h:outputLabel id="endereco" value="#{empresaMB.empresaConsultada.endereco.endereco}" style=" width : 254px;"/>
			</h:panelGrid>	   	    
            
            <h:panelGrid columns="2" >
				<h:outputLabel for="numero" value="Numero:" style="font-weight:bold;"   />
				<h:outputLabel id="numero" value="#{empresaMB.empresaConsultada.endereco.numero}" style=" width : 254px;"/>
			</h:panelGrid>
			
			<h:panelGrid columns="2" >
				<h:outputLabel for="complemento" value="Complemento:" style="font-weight:bold;"   />
				<h:outputLabel id="complemento" value="#{empresaMB.empresaConsultada.endereco.complemento}" style=" width : 254px;"/>
			</h:panelGrid>
			
			<h:panelGrid columns="2" >
				<h:outputLabel for="cep" value="CEP:" style="font-weight:bold;"   />
				<h:outputLabel id="cep" value="#{empresaMB.empresaConsultada.endereco.cep}" style=" width : 254px;"/>
			</h:panelGrid>
			
			<h:panelGrid columns="2" >
				<h:outputLabel for="cidade" value="Cidade:" style="font-weight:bold;"   />
				<h:outputLabel id="cidade" value="#{empresaMB.empresaConsultada.endereco.cidade}" style=" width : 254px;"/>
			</h:panelGrid>
            
            <h:panelGrid columns="2" >
				<h:outputLabel for="uf" value="Uf:" style="font-weight:bold;"   />
				<h:outputLabel id="uf" value="#{empresaMB.empresaConsultada.endereco.uf.descricao}" style=" width : 254px;"/>
			</h:panelGrid>
            
            </fieldset>
            
            <fieldset>
			<legend style="font-weight:bold;">Contato</legend>   	    
            
            <h:panelGrid columns="2" >
				<h:outputLabel for="telefone" value="Telefone:" style="font-weight:bold;"   />
				<h:outputLabel id="telefone" value="#{empresaMB.empresaConsultada.telefone}" style=" width : 254px;"/>
			</h:panelGrid>
			
			<h:panelGrid columns="2" >
				<h:outputLabel for="email" value="E-mail:" style="font-weight:bold;"   />
				<h:outputLabel id="email" value="#{empresaMB.empresaConsultada.email}" style=" width : 254px;"/>
			</h:panelGrid>      
            </fieldset>
			
			<fieldset>
			<legend style="font-weight:bold;">Funcionamento</legend>
		
		    <h:panelGrid columns="2" >
				<h:outputLabel for="horarioFuncionamento" value="Horário de Funcionamento" style="font-weight:bold;" />
				<h:outputLabel  id="horarioFuncionamento" value="#{empresaMB.empresaConsultada.horario_funcionamento}"  />													
			</h:panelGrid>		
			</fieldset>			
			<br/>
			<h:panelGrid columns="2" id="panelBotoes">
			   <a4j:commandButton value="Voltar" immediate="true" action="toConsultaPizzaria" styleClass="botoes"/>
			</h:panelGrid>
			<a4j:region id="regiaoAjax">
			 	<a4j:status id="sts">
	            	<f:facet name="start">
	            		<h:graphicImage value="/images/ajax-loader.gif" />
	               	</f:facet>
	            </a4j:status>
	         </a4j:region>
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