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

<script type="text/javascript"
	src="/jsf/resources/jquery.maskedinput-1.2.1.js"></script>

<style type='text/css'>
#rodape01
{
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top:190%;
	font-weight: bold;
    font-family: arial;
    color: #000000;
}
</style> 

</head>

<body>
	<f:view>
	
	
		<div align="center" id="panel">

			<h:form>
			<fieldset>
			<legend>Escolha o tipo de usuário.</legend>
				
				<table>
					<tr>
						<td colspan="2" align="center">
							<h3>Você é um(a)?</h3></td>
					</tr>
					<tr>
						<td><a href="cadastraPizzaria.jsf"><img alt="" src="/sispo/images/pizzaria.png" width="256" /></a>
						</td>
						<td><a href="cadastraCliente.jsf"><img alt="" src="/sispo/images/user.gif" width="256" /></a>
						</td>

					</tr>
					<tr>
						<td align="center">Pizzaria</td>
						<td align="center">Cliente</td>
					</tr>
				</table>
				</fieldset>
				<table border="0" width="500">
				<tr>
				<td align="right"><a4j:commandButton value="Voltar" immediate="true"	action="toLogin" styleClass="botoes" /></td>
				</tr>
			</table>
			</h:form>
				
		</div>
		<div id="testa" >
			<h:outputText><a style="text-decoration: none;color: white"  href="/sispo/pages/usuario/paginaInicial.jsf">HOME</a> |</h:outputText>
		</div>
		<div>
			<fieldset style="background-color:#D9FFD9;border: 0;">
				<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
			</fieldset>
		</div>	
	</f:view>
</body>
</html>