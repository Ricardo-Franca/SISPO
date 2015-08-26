<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="stella" uri="http://stella.caelum.com.br/faces"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>SISPO | Sistema de Pizzaria Online</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="StyleSheet" type="text/css" href="/jsf/style/estilos.css" media="screen" />
	<link rel="StyleSheet" type="text/css" href="/sispo/style/homeEstilo.css" media="screen" />
	<script type="text/javascript" src="/sispo/resources/jquery.maskedinput-1.2.1.js"></script>
<style type='text/css'>
#rodape01 {
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top: 92%;
	font-weight: bold;
	font-family: arial;
	color: #000000;
}
</style>
</head>

<body>
<f:view>
	<div align="left" id="menuEmpresa">
	<h:form>
		<a4j:keepAlive beanName="pizzaMB" />
			<fieldset><legend>Consulta por Pizzas</legend> 
			<rich:dataTable value="#{pizzaMB.suasPizzas}" var="pronta" rows="10" id="prontas" width="100%" cellspacing="0" cellpadding="0" border="1" style="text-align:center;">	
				<h:column>
					<f:facet name="header">
						<h:outputText value="Imagem" />
					</f:facet>					
					<h:graphicImage value="#{pronta.pizza_imagem}" height="100" width="100"/>					
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="NOME" />
					</f:facet>
					<h:outputText value="#{pronta.nome}" />
				</h:column>
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="VALOR" />
					</f:facet>
					R$<h:outputText value="#{pronta.valor}" />
				</h:column>
												
				<h:column>
					<f:facet name="header">
						<h:outputText value="OBSERVACAO" />
					</f:facet>
					<h:outputText value="#{pronta.observacao}" />
				</h:column>

				<h:column >
					<f:facet name="header">
						<h:outputText value="ALTERAR"/>
					</f:facet>
			
					<a4j:commandLink  action="#{pizzaMB.escolhePizza}" status="sts" reRender="prontas">
						<h:outputText value="Alterar"/>
						<f:setPropertyActionListener value="#{pronta.codigo}" target="#{pizzaMB.codigoPizza}" />			
					</a4j:commandLink>
				</h:column>		
								
				<h:column>
					<f:facet name="header">
						<h:outputText value="Exclusão"/>
					</f:facet>
			
					<a4j:commandLink  value="Excluir" action="#{pizzaMB.deletePizzas}"  status="sts" reRender="prontas">
						<f:setPropertyActionListener value="#{pronta.codigo}" target="#{pizzaMB.codigo}" />				
					</a4j:commandLink>
				</h:column>
	 			
		 		<f:facet name="footer">
					<rich:datascroller />
				</f:facet>
			</rich:dataTable>
			
			<rich:messages/>
			
    	    <a4j:region id="regiaoAjax">
			 	<a4j:status id="sts">
	            	<f:facet name="start">
	            		<h:graphicImage value="/images/ajax-loader.gif" />
	               	</f:facet>
	            </a4j:status>
	        </a4j:region>
	        <h:commandButton value="Voltar" immediate="true" action="toHomeEmpresa" styleClass="botoes"/>
	        <h:commandButton value="Cadastrar Novo Sabor" immediate="true" action="toCadastraPizzas" styleClass="botoes"/>
		</fieldset>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<fieldset style="background-color:#D9FFD9;border: 0;">
				<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
		</fieldset>		
	</h:form>
	</div>
	<rich:modalPanel id="confirmacao" width="150" height="150">

			<h:panelGrid>
				<h:panelGrid columns="2">

					<h:outputText value="Deseja realmente excluir essa pizza?" style="FONT-SIZE: large;" />
				</h:panelGrid>
				<h:panelGroup>
					<input type="button" value="OK" onclick="Richfaces.hideModalPanel('confirmacao');submit();return false" />
					<input type="button" value="Cancel" onclick="Richfaces.hideModalPanel('confirmacao');return false" />
				</h:panelGroup>
			</h:panelGrid>
    </rich:modalPanel>
    
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