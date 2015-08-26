<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="StyleSheet" type="text/css" href="/jsf/style/estilos.css"
	media="screen" />
<link rel="StyleSheet" type="text/css"
	href="/sispo/style/homeEstilo.css" media="screen" />
<script type="text/javascript"
	src="/sispo/resources/jquery.maskedinput-1.2.1.js">
	
</script>
<body>
	<f:view>
		<div id="gerarRelatorio">

			<fieldset>
				<legend><h3>Gerar Relatório</h3></legend>
				<h:form>
					
					<fieldset>
						<legend>Relatório do dia</legend>										
						<h:panelGrid columns="1" id="hoje">
						<h:outputLabel value="#{relatorioMB.hoje}" />
						</h:panelGrid>
						<a4j:commandButton value="Gerar Relatório" action="#{relatorioMB.escolheRelatorioDiario}" status="sts" reRender="hoje" />						
					</fieldset>
				</h:form>
				<h:form>
					<fieldset>
						<legend>Relatório de uma data específica</legend>										
						<h:panelGrid columns="3" id="especifico">
						<rich:calendar id="dataEspecifica" value="#{relatorioMB.dataEspecifica}"  datePattern="dd/MM/yyyy" required="true" requiredMessage="Informe a data especifica."></rich:calendar>
						<h:outputLabel value="Data" />
						<h:message for="dataEspecifica" errorClass="campoError" style="color:Red;"/>
						
						<br>						
						</h:panelGrid>
						<a4j:commandButton value="Gerar Relatório" action="#{relatorioMB.escolheRelatorioEspecifico}" status="sts" reRender="especifico" />						
					</fieldset>
				</h:form>
				<h:form>
					<fieldset>
						<legend>Relatório por Período</legend>
						<h:panelGrid columns="3" id="periodo">																			
						<rich:calendar id="dataInicial" value="#{relatorioMB.dataInicial}"  datePattern="dd/MM/yyyy" required="true" requiredMessage="Informe a data inicial."></rich:calendar>
						<h:outputLabel value="Data Inicial" />
						<h:message for="dataInicial" errorClass="campoError" style="color:Red;"/>
						<rich:calendar id="dataFinal" value="#{relatorioMB.dataFinal}"  datePattern="dd/MM/yyyy" required="true" requiredMessage="Informe a data final."></rich:calendar>
						<h:outputLabel value="Data Final" />
						<h:message for="dataFinal" errorClass="campoError" style="color:Red;"/>
						</h:panelGrid>
						<a4j:commandButton value="Gerar Relatório" action="#{relatorioMB.escolheRelatorioPorPeriodo}" status="sts" reRender="periodo" />			
					</fieldset>
				</h:form>
				<h:form>
					<fieldset>
						<legend>Relatório Anual</legend>
						<h:panelGrid columns="3" id="anual">						
						<rich:comboBox id="uf" value="#{relatorioMB.ano}" selectFirstOnUpdate="false" defaultLabel="Escolha o ano" required="true" requiredMessage="Informe o ano.">
							<f:selectItems value="#{relatorioMB.anos}" />
						</rich:comboBox>
						<h:message for="uf" errorClass="campoError" style="color:Red;" />
						<br>
						<a4j:commandButton value="Gerar Relatório" action="#{relatorioMB.escolheRelatorioAnual}" status="sts" reRender="anual" />
						</h:panelGrid>
					</fieldset>											
					<br>
					<br>
					<br>

					<a4j:commandButton value="Voltar" immediate="true"
						action="toHomeEmpresa" styleClass="botoes" />
				</h:form>

			</fieldset>
		</div>

		<div id="testa">
			<a style="text-decoration: none; color: white;"
				href="/sispo/pages/empresa/homeEmpresa.jsf">HOME EMPRESA</a> |
		</div>
		<div id="sair">
			<h:form>
				<h:panelGrid>
					<h:commandLink style="text-decoration: none;color: white"
						value="Sair" action="#{usuarioMB.logout}" />
				</h:panelGrid>
			</h:form>
		</div>


	</f:view>
</body>
</html>