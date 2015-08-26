<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<link rel="StyleSheet" type="text/css" href="/sispo/style/estilos.css" media="screen" />
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
    <div align="left" id="menuAdministrador">
	<h:form>
		<h:messages />
		<fieldset>
			<legend>Home Administrador</legend>
			
			<rich:panelMenu mode="ajax" styleClass="rf-pm-top-itm-lbl" style=" width : 180px;">			

				<rich:panelMenuGroup label="Cliente">
					<rich:panelMenuItem  icon="../../images/user.gif">
						<h:commandLink value="Consultar" action="toRemoveCliente" />
					</rich:panelMenuItem>	
				</rich:panelMenuGroup>
				
				<rich:panelMenuGroup label="Pizzaria">
					<rich:panelMenuItem icon="../../images/pizzaria2.png" >
						<h:commandLink value="Consultar" action="toRemoveEmpresa" />
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
					<h:outputLabel for="bemvindo" value="Bem Vindo (a) : Administrador." style="color:white;"/>
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
</f:view>
</body>
</html>