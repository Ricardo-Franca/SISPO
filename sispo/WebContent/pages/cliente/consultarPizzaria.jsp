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
			<fieldset><legend>Consulta por Pizzarias</legend> 
			<rich:dataTable value="#{empresaMB.empresas}" align="center" var="fisica" rows="10" id="fisicas" width="100%" cellspacing="0" cellpadding="0" border="1" style="text-align:center;">	
					<h:column >
					<f:facet name="header">
					<h:outputText value="Pizzarias" style="font-size:16px;" />
					</f:facet>
					<h:panelGrid >
						<h:graphicImage value="#{fisica.pizzaria_logo}" height="100" width="100" />
					</h:panelGrid>
					<a4j:commandLink  action="#{empresaMB.escolheConsultarPizzaria}" status="sts" reRender="fisicas">
						<h:outputText value="#{fisica.nome_fantasia}" style="font-size:16px;" />
						<f:setPropertyActionListener value="#{fisica.codigo}" target="#{empresaMB.codigo}" />			
					</a4j:commandLink>						
				</h:column>
				
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="Localização" style="font-size:16px;"/>
					</f:facet>
					<h:outputText value="#{fisica.endereco.cidade}" style="font-size:16px;" /> -
					<h:outputText value="#{fisica.endereco.uf.descricao}" style="font-size:16px;" />
				</h:column>
												
				<h:column>
					<f:facet name="header">
						<h:outputText value="Telefone" style="font-size:16px;" />
					</f:facet>
					<h:outputText value="#{fisica.telefone}" style="font-size:16px;" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Email" style="font-size:16px;" />
					</f:facet>
					<h:outputText value="#{fisica.email}" style="font-size:16px;" />
				</h:column>		
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Incluir pedido" style="font-size:16px;"/>
					</f:facet>
			
					<a4j:commandLink  action="#{pedidoMB.escolhePizzaria}" status="sts" reRender="fisicas">
						<h:outputText value="Fazer Pedido" style="font-size:16px;"/>
						<f:setPropertyActionListener value="#{fisica.codigo}" target="#{pedidoMB.codigoEmpresa}" />			
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
		<br><br><br><br><br><br><br><br><br><br>
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