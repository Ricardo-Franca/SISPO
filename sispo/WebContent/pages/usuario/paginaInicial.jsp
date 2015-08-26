<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>
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
#rodap {
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top: 150%;
	font-weight: bold;
	font-family: arial;
	color: #000000;
}
</style>


</head>
<title>Pagina Inicial</title>
<body>
	<f:view>
		<div style="left: 50%; position: absolute; top: 50%;" id="linha">
			<hr size="580%" width="1" align="left">
		</div>

		<div style="width: 100%" id="letreiroinicial01">
			<marquee behavior="scroll" direction="left" scrollamount="3"
				width="48%" height="50%" style="FONT-SIZE: medium;">					
				<img alt="Pizza Calabreza" src="/sispo/images/pizzaCalabresa.gif" height="18%" width="11%"/> 
				<img alt="Pizza A Moda"	src="/sispo/images/pizza-a-moda-saudavel-1-1-297.gif" height="18%" width="11%" /> 
				<img alt="Pizza Portuguesa"	src="/sispo/images/pizza2.gif" height="18%" width="11%" /> 
				<img alt="Pizza Atum" src="/sispo/images/pizza-diavolo.gif" height="18%"	width="11%" /> 
				<img alt="Pizza Frango"	src="/sispo/images/pizza-romana.gif" height="18%" width="11%" /> 
				<img alt="Pizza Tradicional" src="/sispo/images/pizza-capricciosa.gif" height="18%" width="11%" />
			</marquee>
		</div>		

		<div style="width: 100%" id="letreiroinicial">
			<marquee behavior="scroll" direction="up" scrollamount="3"
				width="30%" height="80%" style="FONT-SIZE: medium;">
				<img alt="Bem Vindo" src="/sispo/images/bemvindo.gif" height="100%"
					width="100%" /> <br>O © SISPO é o mais novo site para compra
				de pizzas pela Internet.<br> <br>Aqui você conhece várias
				pizzarias e suas diversidades de produtos<br> <br>Através
				da nossa interface, você interage com o sistema<br> <br>Compre
				as melhores pizzas nas melhores pizzarias da sua região<br> <br>Os
				amantes de pizza se encontram aqui!!!

			</marquee>
		</div>
		<div id="botaoToLogin">
			<h:form>
				<h:panelGrid columns="1" id="inputs">
					<a4j:commandButton value="LOGIN" immediate="true" action="toLogin"
						styleClass="botoes" />
				</h:panelGrid>
			</h:form>
		</div>
		<div id="testa">
			<h:outputText>
				<a style="text-decoration: none; color: white"
					href="/sispo/pages/usuario/paginaInicial.jsf">HOME</a> |</h:outputText>
		</div>
		<div id="rodap">
			<fieldset style="background-color: #D9FFD9; border: 0;">
				<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
			</fieldset>
		</div>
		<div id="propraganda">
			<h:form>
				<h:panelGrid columns="1" border="0">

					<img alt="aMelhores" src="/sispo/images/frentepizzaria.png"
						height="20%" width="35%" />
					<h:outputLabel value="Aqui você encontra as melhores pizzarias."
						styleClass="rotulos" style="FONT-SIZE: small; font-weight:bold;" />

					<img alt="oMelhorPreparo" src="/sispo/images/preparo.jpg"
						height="20%" width="35%" />
					<h:outputLabel value="Pizzas feitas com os melhores ingredientes."
						styleClass="rotulos" style="FONT-SIZE: small; font-weight:bold;" />

					<img alt="entregaRapida" src="/sispo/images/entrega.jpg"
						height="20%" width="35%" />
					<h:outputLabel value="A entrega mais rápida." styleClass="rotulos"
						style="FONT-SIZE: small; font-weight:bold;" />

					<img alt="pizzaBrasil" src="/sispo/images/brasilpizza.jpg"
						height="20%" width="35%" />
					<h:outputLabel
						value="Diversas pizzarias oferencendo o seus produtos."
						styleClass="rotulos" style="FONT-SIZE: small; font-weight:bold;" />
				</h:panelGrid>
			</h:form>
		</div>
		<div id="sigame" align="center">
			<h:panelGrid columns="3" border="0">

				<a href="https://www.facebook.com/" target="_blank"><img
					alt="facebook" src="/sispo/images/facebook.jpg" height="20%"
					width="95%" />
				</a>
				<h:outputLabel value="SISPO nas Redes Sociais" styleClass="rotulos"
					style="FONT-SIZE: small; font-weight:bold;" />
				<a href="https://twitter.com/" target="_blank"><img
					alt="twitter" src="/sispo/images/twitter.jpg" height="20%"
					width="35%" />
				</a>

			</h:panelGrid>
		</div>
	</f:view>

</body>
</html>