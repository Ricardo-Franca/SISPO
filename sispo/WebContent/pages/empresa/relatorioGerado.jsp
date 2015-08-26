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
	
<style type='text/css'>
	#imprimi {
	position: absolute;
	bottom: 0;
	width: 50%;
	left: 50%;
	
	font-weight: bold;
	font-family: arial;
	color: #000000;
	}
	#inicio {
	position: absolute;
	bottom: 0;
	width: 50%;
	left: 50%;
	top: 10%;
	font-weight: bold;
	font-family: arial;
	color: #000000;
	}
	#relatorio {
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top: 15%;
	font-weight: bold;
	font-family: arial;
	color: #000000;
	}
</style>
	
	
</head>

<body>
	<f:view>
		
		<div id="inicio">
			<h:outputLabel for="tipo" value="Relatorio de Pedidos" />
		
		</div>
		<div id="relatorio">
		<h:form>
			<fieldset><legend>Pedidos</legend> 
			<rich:messages style="color:Red;" />
			<rich:dataTable value="#{relatorioMB.gerarRelatorio}" var="pessoal" id="pessoais" width="100%" cellspacing="0" cellpadding="0" border="1" style="text-align:center;">	
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
					<h:outputText value="#{pessoal.data}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="HORA" />
					</f:facet>
					<h:outputText value="#{pessoal.hora}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="CLIENTE" />
					</f:facet>
					<h:outputText value="#{pessoal.cliente.nome}" />
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
			
			 <a4j:region id="regiaoAjax">
			 	<a4j:status id="sts">
	            	<f:facet name="start">
	            		<h:graphicImage value="/images/ajax-loader.gif" />
	               	</f:facet>
	            </a4j:status>
	         </a4j:region>						
				</fieldset>
				</h:form>
		</div>
		
		<div id="imprimi">			
			<input src="/sispo/images/imprimi.png" name="btnImprimir" type="image" id="btnImprimir" onclick="window.print();" title="Imprimir" class="noprint" height="12%" width="12%"/>
			<a href="/sispo/pages/empresa/gerarRelatorio.jsf"><img src="/sispo/images/voltar.jpg" height="19%" width="19%"/></a>
		</div>
	</f:view>
</body>
</html>