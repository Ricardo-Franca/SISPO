<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!--  < %@ taglib prefix="ui" uri="http://java.sun.com/jsf/facelets"%>-->
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SISPO | Sistema de Pizzaria Online</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" type="text/css" href="/sispo/style/estilos.css"
	media="screen" />
<link rel="StyleSheet" type="text/css"
	href="/sispo/style/homeEstilo.css" media="screen" />

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
				<h:messages />
				<fieldset>
					<legend>Home Cliente</legend>

				<rich:panelMenu mode="ajax" styleClass="rf-pm-top-itm-lbl"
					style=" width : 180px;">


					<rich:panelMenuGroup label="Cadastro">
						<rich:panelMenuItem icon="../../images/atualizar.png">
							<h:commandLink value="Alterar Cadastro"
								action="toAtualizaCadastroCliente" />
						</rich:panelMenuItem>
						<rich:panelMenuItem icon="../../images/chave.png">
							<h:commandLink value="Alterar Senha" action="toAlterarSenha" />
						</rich:panelMenuItem>
						<rich:panelMenuItem icon="../../images/delete.png">
							<a4j:commandLink value="Excluir"
								onclick="Richfaces.showModalPanel('confirmacao');return false"
								status="sts" reRender="fisica" />
							<a4j:jsFunction name="submit" action="#{clienteMB.delete}" />
						</rich:panelMenuItem>
					</rich:panelMenuGroup>

					<rich:panelMenuGroup label="Pedido">
						<rich:panelMenuItem icon="../../images/pedido.png">
							<h:commandLink value="Consultar" action="toConsultaPedido" />
						</rich:panelMenuItem>
					</rich:panelMenuGroup>

					<rich:panelMenuGroup label="Pizzarias">
						<rich:panelMenuItem icon="../../images/pizzaria2.png">
							<h:commandLink value="Consultar" action="toConsultarPizzaria" />
						</rich:panelMenuItem>
					</rich:panelMenuGroup>
					<rich:panelMenuGroup label="Fale Conosco">
						<rich:panelMenuItem icon="../../images/mensagem.png">
							<h:commandLink value="Enviar Mensagem" action="toFaleConoscoCliente"/>
						</rich:panelMenuItem>
					</rich:panelMenuGroup>
				</rich:panelMenu>
				</fieldset>
				<br><br><br><br><br><br><br><br><br><br><br><br>
				<fieldset style="background-color:#D9FFD9;border: 0;">
					<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
				</fieldset>
			</h:form>
		</div>


		<div id="bemvindo">
			<h:form>
				<h:panelGrid columns="2">

					<h:outputLabel for="bemvindo" value="Bem Vindo (a) :" style="color:white;" />
					<h:outputLabel for="nome" value="#{clienteMB.cadastroCliente.nome}" style="color:white;" />
				</h:panelGrid>

			</h:form>
		</div>


		<div id="sair" >
			<h:form>
				<h:panelGrid>
					<h:commandLink style="text-decoration: none;color: white" value="Sair" action="#{usuarioMB.logout}" />
				</h:panelGrid>
			</h:form>
		</div>



		<rich:modalPanel id="confirmacao" width="240" height="90">
			<h:panelGrid>
				<h:panelGrid columns="2">
					<h:outputText value="Deseja realmente excluir seu cadastro?"
						style="FONT-SIZE: large;" />
				</h:panelGrid>
				<h:panelGroup>
					<input type="button" value="OK"
						onclick="Richfaces.hideModalPanel('confirmacao');submit();return false" />
					<input type="button" value="Cancel"
						onclick="Richfaces.hideModalPanel('confirmacao');return false" />
				</h:panelGroup>
			</h:panelGrid>
		</rich:modalPanel>
		
		<div id="pedidosSendoEntregues">
			<rich:messages style="color:Red;" />
			<h:form>
			<rich:dataTable value="#{pedidoMB.meusPedidosCliente}" var="pessoal" rows="3" id="pessoais" width="100%" cellspacing="0" cellpadding="0" border="1" style="text-align:center;">	
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
					<h:outputText value="#{pessoal.data}"/>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="HORA" />
					</f:facet>
					<h:outputText value="#{pessoal.hora}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="PIZZARIA" />
					</f:facet>
					<h:outputText value="#{pessoal.empresa.nome_fantasia}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="SITUAÇÃO" />
					</f:facet>
					<h:outputText value="#{pessoal.situacao.descricao}" />
				</h:column>	
			<f:facet name="footer">
					<rich:datascroller />
				</f:facet>	
			</rich:dataTable>
			</h:form>
			</div>
			 <a4j:region id="regiaoAjax">
			 	<a4j:status id="sts">
	            	<f:facet name="start">
	            		<h:graphicImage value="/images/ajax-loader.gif" />
	               	</f:facet>
	            </a4j:status>
	         </a4j:region>
	</f:view>
</body>
</html>