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
	top:120%;
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
		<a4j:keepAlive beanName="clienteMB" />
			<fieldset>
			<legend style="font-weight:bold;">Altera Pedido</legend>   	    
            <rich:messages style="color:Red;" />          
			<h:panelGrid columns="2" id="panelGridInputs">
				<h:outputLabel for="empresa" value="Pizzaria:" style="font-weight:bold;"   />
				<h:outputLabel id="empresa" value="#{pedidoMB.pedidoAtual.empresa.nome_fantasia}" style=" width : 254px;"/>
			</h:panelGrid>	
			
			<h:panelGrid columns="2">
				<h:outputLabel for="data" value="Data:" style="font-weight:bold;"   />
				<h:outputLabel id="data" value="#{pedidoMB.pedidoAtual.data}" style=" width : 254px;"/>
			</h:panelGrid>
			
			<rich:dataTable value="#{pedidoMB.listaDePizza}" var="atuais" rows="10" id="atuais" width="20%" cellspacing="0" cellpadding="0" border="1">	
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Imagem" />
					</f:facet>					
					<h:graphicImage value="#{atuais.pizza.pizza_imagem}" height="100" width="100"/>					
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Pizza" />
					</f:facet>
					<h:outputText value="#{atuais.pizza.nome}" />
				</h:column>			
												
				<h:column>
					<f:facet name="header">
						<h:outputText value="Valor" />
					</f:facet>
					R$<h:outputText value="#{atuais.pizza.valor}" />
				</h:column>
				
				<h:column >
					<f:facet name="header" >
						<h:outputText value="Quantidade" />
					</f:facet>
					<h:outputText value="#{atuais.quantidade}" style="text-align:center;"/>
				</h:column>					
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Remoção"/>
					</f:facet>
			
					<a4j:commandLink  action="#{pedidoMB.removePizza}" status="sts" reRender="atuais">
						<h:outputText value="Remover"/>
						<f:setPropertyActionListener value="#{atuais.pizza.codigo}" target="#{pedidoMB.codigoPizza}" />
					</a4j:commandLink>
				</h:column>
			</rich:dataTable>
			
			<br/>
			<h:panelGrid columns="1" id="panelIncluirPizza">
			   <a4j:commandButton value="IncluirPizza" title="Incluir Pizza" status="sts" immediate="true" action="toConsultaPizza" styleClass="botoes" style="width : 38px; height : 24px;" image="../../images/adicionar.png"/>
			</h:panelGrid>
			<br/>
			
			<h:panelGrid columns="2">
							
							<h:outputLabel for="total" value="Total: R$" style="font-weight:bold;"/>
							<h:outputText id="total" value="#{pedidoMB.calcularPreco}" style=" width : 254px;" />
							<h:outputLabel for="valorPago" value="Valor à pagar: R$" style="font-weight:bold;" title="Valor que será pago ao entregador"/>
							<h:inputText value="#{pedidoMB.pedidoAtual.pagamento}" id="pagamento" style=" width : 80px;" />
							
							<a4j:commandButton value="Calcular" action="#{pedidoMB.calcularTroco}" styleClass="botoes" status="sts"/>
							<h:inputHidden value="" />
							
							<h:outputLabel for="troco" value="Troco: R$" style="font-weight:bold;"/>
							<h:outputText id="troco" value="#{pedidoMB.pedidoAtual.troco}" style=" width : 254px;" />
			</h:panelGrid>
			<br/>
			<h:panelGrid columns="2" id="panelBotoes">
			<a4j:commandButton value="Concluir" action="#{pedidoMB.update}" styleClass="botoes" status="sts" reRender="atuais"/>
				<a4j:commandButton value="Voltar" immediate="true" action="toConsultarPizzaria" styleClass="botoes"/>
			</h:panelGrid>
			<a4j:region id="regiaoAjax">
			 	<a4j:status id="sts">
	            	<f:facet name="start">
	            		<h:graphicImage value="/images/ajax-loader.gif" />
	               	</f:facet>
	            </a4j:status>
	         </a4j:region>
			
		   </fieldset>
		   <br><br><br><br><br><br><br>
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