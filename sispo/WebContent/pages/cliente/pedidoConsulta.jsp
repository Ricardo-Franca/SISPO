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
	<div align="left" id="menuEmpresa">
	<h:form>			 
		<a4j:keepAlive beanName="pedidoMB" />
			<fieldset>
			<legend style="font-weight:bold;">Pedido</legend>
			<fieldset>
			<legend style="font-weight:bold;">Pizzaria</legend>   	    
            
            <h:panelGrid columns="2">
				<h:graphicImage value="#{pedidoMB.pedidoAtual.empresa.pizzaria_logo}" height="100" width="100" />
				<h:inputHidden value="" />
			</h:panelGrid>
            
            <h:panelGrid columns="2">
				<h:outputLabel for="usuario" value="Pizzaria:" style="font-weight:bold;"   />
				<h:outputLabel id="usuario" value="#{pedidoMB.pedidoAtual.empresa.nome_fantasia}" style=" width : 254px;"/>
			</h:panelGrid>			
                     		
			</fieldset>
            
            <fieldset>
			<legend style="font-weight:bold;">Contato</legend>   	    
            
            <h:panelGrid columns="2" >
				<h:outputLabel for="telefone" value="Telefone:" style="font-weight:bold;"   />
				<h:outputLabel id="telefone" value="#{pedidoMB.pedidoAtual.empresa.telefone}" style=" width : 254px;"/>
			</h:panelGrid>            
            </fieldset>
			
			<fieldset>
			<legend style="font-weight:bold;">Pizzas</legend>
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
			</rich:dataTable>			
			</fieldset>
			
			<fieldset>
			<legend style="font-weight:bold;">Pagamento</legend>
			<h:panelGrid columns="2">
				<h:outputLabel for="valorpago" value="Valor pago: R$" style="font-weight:bold;"   />
				<h:outputLabel id="valorpago" value="#{pedidoMB.pedidoAtual.pagamento}" style=" width : 254px;"/>
			</h:panelGrid>			
			
			<h:panelGrid columns="2">
				<h:outputLabel for="valor" value="Total do pedido: R$" style="font-weight:bold;"   />
				<h:outputLabel id="valor" value="#{pedidoMB.pedidoAtual.valor}" style=" width : 254px;color:red;"/>
			</h:panelGrid>
			
			<h:panelGrid columns="2">
				<h:outputLabel for="troco" value="Troco: R$" style="font-weight:bold;"   />
				<h:outputLabel id="troco" value="#{pedidoMB.pedidoAtual.troco}" style=" width : 254px;"/>
			</h:panelGrid>
			
			
			</fieldset>
			
			<fieldset>
			<legend style="font-weight:bold;">Data/Hora</legend>
			<h:panelGrid columns="2">
				<h:outputLabel for="data" value="Data:" style="font-weight:bold;"   />
				<h:outputLabel id="data" value="#{pedidoMB.pedidoAtual.data}" style=" width : 254px;"/>
			</h:panelGrid>
			
			<h:panelGrid columns="2">
				<h:outputLabel for="hora" value="Hora:" style="font-weight:bold;"   />
				<h:outputLabel id="hora" value="#{pedidoMB.pedidoAtual.hora}" style=" width : 254px;"/>
			</h:panelGrid>
			</fieldset>
			
			<br/>
			<h:panelGrid columns="2" id="panelBotoes">
			   <a4j:commandButton value="Voltar" immediate="true" action="toConsultaPedido" styleClass="botoes"/>
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