<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>SISPO | Sistema de Pizzaria Online</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="StyleSheet" type="text/css" href="/jsf/style/estilos.css" media="screen" />
	<link rel="StyleSheet" type="text/css" href="/sispo/style/homeEstilo.css" media="screen" />
	<script type="text/javascript" src="/jsf/resources/jquery.maskedinput-1.2.1.js"></script>
<style type='text/css'>
#rodape01
{
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top:100%;
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
			<fieldset><legend>Pedidos</legend> 
			<rich:messages style="color:Red;" />
			<rich:dataTable value="#{pedidoMB.meusPedidosCliente}" var="pessoal" rows="10" id="pessoais" width="100%" cellspacing="0" cellpadding="0" border="1" style="text-align:center;">	
				<h:column>
					<f:facet name="header">
						<h:outputText value="CÓDIGO" />
					</f:facet>
					<h:outputText value="#{pessoal.codigo}" />
				</h:column>
							
				<h:column>
					<f:facet name="header">
						<h:outputText value="DATA" />
					</f:facet>
					<h:outputText value="#{pessoal.data}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="HORA" />
					</f:facet>
					<h:outputText value="#{pessoal.hora }" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="PIZZARIA" />
					</f:facet>
					<h:outputText value="#{pessoal.empresa.nome_fantasia}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="CONSULTAR"/>
					</f:facet>
			
					<a4j:commandLink  action="#{pedidoMB.clienteEscolheConsultarPedido}" status="sts" reRender="pessoais">
						<h:outputText value="Informações do Pedido"/>
						<f:setPropertyActionListener value="#{pessoal.codigo}" target="#{pedidoMB.codigoPedido}" />			
					</a4j:commandLink>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="SITUAÇÃO" />
					</f:facet>
					<h:outputText value="#{pessoal.situacao.descricao}" />
				</h:column>				
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="Alterar pedido"/>
					</f:facet>
			
					<a4j:commandLink  action="#{pedidoMB.escolheAlterarPedido}" status="sts" reRender="pessoais">
						<h:outputText value="Alterar"/>
						<f:setPropertyActionListener value="#{pessoal.codigo}" target="#{pedidoMB.codigoPedido}" />			
					</a4j:commandLink>
				</h:column>
	 			
	 			<h:column>
					<f:facet name="header">
						<h:outputText value="Cancelar pedido"/>
					</f:facet>
			
					<a4j:commandLink  action="#{pedidoMB.alteraSituacaoDoPedido}" status="sts" reRender="pessoais">
						<h:outputText value="Cancelar"/>
						<f:setPropertyActionListener value="#{pessoal.codigo}" target="#{pedidoMB.codigoPedido}" />
						<f:setPropertyActionListener value="7" target="#{pedidoMB.codigoSituacao}" />			
					</a4j:commandLink>
				</h:column>
	 		
	 			
		 		<f:facet name="footer">
					<rich:datascroller />
				</f:facet>
			</rich:dataTable>
			
			 <a4j:region id="regiaoAjax">
			 	<a4j:status id="sts">
	            	<f:facet name="start">
	            		<h:graphicImage value="/images/ajax-loader.gif" />
	               	</f:facet>
	            </a4j:status>
	         </a4j:region>
			
			<h:commandButton value="Voltar" immediate="true" action="toHomeCliente" styleClass="botoes"/>
		</fieldset>
		<br><br><br><br><br><br><br><br><br><br><br><br>
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