<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="stella" uri="http://stella.caelum.com.br/faces"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SISPO | Sistema de Pizzaria Online</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" type="text/css" href="/jsf/style/estilos.css"
	media="screen" />
<link rel="StyleSheet" type="text/css"
	href="/sispo/style/homeEstilo.css" media="screen" />

<script type="text/javascript" src="/sispo/resources/jquery.maskedinput-1.2.1.js">
</script>  

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
		<div id="menuCliente">
				<fieldset>
					<legend style="font-size: 20px;">Usuário cadastrado</legend>
					
					<h3>
						Bem vindo ao SISPO!
						<br/>
						Seu Cadastro foi realizado com Sucesso!
						<br/>
						Clique <a href="/sispo/pages/usuario/login.jsf">aqui</a> para efetuar login.
					</h3>
				</fieldset>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
				<fieldset style="background-color:#D9FFD9;border: 0;">
					<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
				</fieldset>
		</div>
	</f:view>
</body>
</html>