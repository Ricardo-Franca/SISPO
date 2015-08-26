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

</head>

<body>
	<f:view>
		<div id="menuEmpresa">
			<h:form>
				<fieldset>
					<legend style="font-size: 20px;">Cadastro de Ingredientes</legend>
					<a4j:region id="regiaoAjax">
						<a4j:status id="sts">
							<f:facet name="start">
								<h:graphicImage value="/images/ajax-loader.gif" />
							</f:facet>
						</a4j:status>
					</a4j:region>					
					<h:panelGrid columns="2" id="inputs">

                    <h:outputLabel for="campos" value="* Campos Obrigatórios " />
					<h:inputHidden value=""/>
					
					
					<h:messages globalOnly="true" style="color:Red;"/>
					                   
                                                                     									
					<h:inputHidden value=""/>		
					<h:outputLabel for="nomeingrediente" value="Nome Ingrediente *"/>
					<h:inputHidden value=""/>			
					<h:inputText id="nomeingrediente" value="#{pizzaMB.pizza.nome}" required="true" requiredMessage="Campo nome pizza obrigatório!" styleClass="edit" size="30">
					</h:inputText>						
					<h:message for="nomeingrediente" errorClass="campoError" style="color:Red;"/>
						
					<h:outputLabel for="valor" value="Valor R$ *" />				
					<h:inputHidden value=""/>
					<h:inputText id="valor" value="#{pizzaMB.pizza.valor}" size="4" required="true" requiredMessage="Campo valor obrigatório!">
							<rich:jQuery selector="#valor" query="mask('99.99')" timing="onload"/>
					</h:inputText>
					<h:message for="valor" errorClass="campoError" style="color:Red;"/>
					<h:inputHidden value=""/>
					<h:outputLabel value="" />
						
					<h:outputLabel for="tipoingrediente" value="Tipo Ingredientes *" />
						
				
						
					<h:outputLabel value=""/>					
					<h:selectOneMenu id="uf" value="#{clienteMB.cliente.endereco.uf.codigo}" required="true" requiredMessage="Tipo de Ingrediente obrigatório!">
						<f:selectItem itemValue="" itemLabel="Tipo de Ingrediente"/>
						<f:selectItem itemValue="" itemLabel="Adicional"/>
						<f:selectItem itemValue="" itemLabel="Complemento"/>
						<f:selectItem itemValue="" itemLabel="Tempero"/>
						<f:selectItem itemValue="" itemLabel="Extra"/>						
					</h:selectOneMenu>												
													
											
					</h:panelGrid>
					<a4j:commandButton value="Gravar" action="#{pizzaMB.save}"
						status="sts" reRender="inputs" styleClass="botoes" />											
													
					<a4j:commandButton value="Voltar" immediate="true"
							action="toHomeEmpresa" styleClass="botoes" />				
					<h:commandButton value="Ingredientes Cadastrados" immediate="true" action="toConsultaIngredientes" styleClass="botoes"/>
				</fieldset>
			</h:form>
		</div>
		<div id="testa" >
	   		<h:outputText><a style="text-decoration: none;color: black"  href="/sispo/pages/empresa/homeEmpresa.jsf">HOME EMPRESA</a> |</h:outputText>
		</div>
		<div id="rodape01">
			<fieldset style="background-color:#D9FFD9;border: 0;">
				<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
			</fieldset>
		</div>
	</f:view>
</body>
</html>