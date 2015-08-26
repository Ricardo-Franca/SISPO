<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.ajax4jsf.org/rich"%>
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

<script type="text/javascript" src="/sispo/resources/jquery.maskedinput-1.2.1.js"></script>	
<script type="text/javascript" src="/sispo/script/script.js"></script>

<style type='text/css'>
#rodape01
{
	position: absolute;
	bottom: 0;
	width: 100%;
	left: 0%;
	top:205%;
	font-weight: bold;
    font-family: arial;
    color: #000000;
}
</style> 

</head>

<body>
	<f:view>
		<div id="menuCliente">
			<h:form>
				<fieldset>
					<legend style="font-size: 20px;">Cadastro de Pizzarias</legend>
					<a4j:region id="regiaoAjax">
						<a4j:status id="sts">
							<f:facet name="start">
								<h:graphicImage value="/images/ajax-loader.gif" />
							</f:facet>
						</a4j:status>
					</a4j:region>

					
					<h:outputLabel for="campos" value="* Campos Obrigatórios " />
					
					<h:panelGrid columns="1" id="erros">
						<h:messages globalOnly="true" style="color:Red;" />
					</h:panelGrid>
					<fieldset>
							<legend>Dados Pessoais</legend>
					<h:panelGrid columns="3" id="pessoais">						

						<h:inputHidden value="" />
						<h:selectOneMenu id="tpusuario" value="#{empresaMB.empresa.usuario.tipoUsuario.codigo}" style="display:none;">
							<f:selectItem itemValue="2" itemLabel="Empresa" />
						</h:selectOneMenu>
						<h:inputHidden value="" />
						
						<h:inputHidden value="" />
						<h:selectOneMenu id="status"
							value="#{empresaMB.empresa.usuario.status.codigo}"
							style="display:none;">
							<f:selectItem itemValue="1" itemLabel="Empresa" />
						</h:selectOneMenu>
						<h:inputHidden value="" />

						<h:outputLabel for="razaoSocial" value="Razão Social *" />
						<h:inputText id="razaoSocial"
							value="#{empresaMB.empresa.razao_social}" required="true"
							requiredMessage="Campo razão social obrigatório!"
							styleClass="edit" size="30" />
						<h:message for="razaoSocial" errorClass="campoError"
							style="color:Red;" />

						<h:outputLabel for="nomeFantasia" value="Nome Fantasia *" />
						<h:inputText id="nomeFantasia"
							value="#{empresaMB.empresa.nome_fantasia}" required="true"
							requiredMessage="Campo nome fantasia obrigatório!"
							styleClass="edit" size="30" />
						<h:message for="nomeFantasia" errorClass="campoError"
							style="color:Red;" />

						<h:outputLabel for="cnpj" value="CNPJ *" />
						<h:inputText id="cnpj" value="#{empresaMB.empresa.cnpj}" required="true" requiredMessage="Campo cnpj obrigatório!" styleClass="edit" size="11">
							<rich:jQuery selector="#cnpj" query="mask('99.999.999/9999-99')" timing="onload" />
							<stella:validateCNPJ formatted="true"/>
							<f:validator validatorId="cnpjValidator"/>
						</h:inputText>
						<h:message for="cnpj" errorClass="campoError" style="color:Red;" />

						<h:outputLabel for="telefone" value="Telefone " />
						<h:inputText id="telefone" value="#{empresaMB.empresa.telefone}" required="true" requiredMessage="Campo telefone obrigatório!" styleClass="edit" size="10">
							<rich:jQuery selector="#telefone" query="mask('(99) 9999-9999')" timing="onload" />
							</h:inputText>
						<h:message for="telefone" errorClass="campoError" style="color:Red;" />

						<h:outputLabel for="horarioFuncionamento" value="Horário de Funcionamento" />
						<h:inputTextarea  id="horarioFuncionamento" value="#{empresaMB.empresa.horario_funcionamento}" styleClass="edit" cols="30" rows="5" onkeypress="return validarQtdeCaracteres(this.value, 80);"  />
						<h:inputHidden value="" />
						
						<h:outputLabel for="Logo" value="Logo" />
						<rich:fileUpload fileUploadListener="#{empresaMB.uploadImage}" listWidth="200" listHeight="50" id="Logo" maxFilesQuantity="1" acceptedTypes="jpg, gif, png, bmp"/>
                             <h:message for="Logo" errorClass="campoError"/>
						</h:panelGrid>
						</fieldset>						
						
						<fieldset>							
							<legend>Informações de Endereço</legend>
							
						<h:panelGrid columns="3" id="InfoEndereco">
						
						<h:outputLabel for="cep" value="CEP" />
						<h:inputText id="cep" value="#{empresaMB.empresa.endereco.cep}"
							required="true" requiredMessage="Campo cep obrigatório!"
							styleClass="edit" size="15">
							<rich:jQuery selector="#cep" query="mask('99999-999')"
								timing="onload" />
						</h:inputText>
						<h:message for="cep" errorClass="campoError" style="color:Red;" />
							
						<h:outputLabel for="endereco" value="Endereço *" />
						<h:inputText id="endereco"
							value="#{empresaMB.empresa.endereco.endereco}" required="true"
							requiredMessage="Campo endereço obrigatório!" styleClass="edit"
							size="30" />
						<h:message for="endereco" errorClass="campoError"
							style="color:Red;" />

						<h:outputLabel for="numero" value="Número" />
						<h:inputText id="numero"
							value="#{empresaMB.empresa.endereco.numero}" required="true"
							requiredMessage="Campo número obrigatório!" styleClass="edit"
							size="10" />
						<h:message for="numero" errorClass="campoError" style="color:Red;" />

						<h:outputLabel for="complemento" value="Complemento " />
						<h:inputText id="complemento"
							value="#{empresaMB.empresa.endereco.complemento}" required="true"
							requiredMessage="Campo complemento obrigatório!"
							styleClass="edit" size="20" />
						<h:message for="complemento" errorClass="campoError"
							style="color:Red;" />

						<h:outputLabel for="cidade" value="Cidade *" />
						<h:inputText id="cidade"
							value="#{empresaMB.empresa.endereco.cidade}" required="true"
							requiredMessage="Campo cidade obrigatório!" styleClass="edit"
							size="15" />
						<h:message for="cidade" errorClass="campoError" style="color:Red;" />

						<h:outputLabel for="uf" value="UF *" />
						<h:selectOneMenu id="uf"
							value="#{empresaMB.empresa.endereco.uf.codigo }" required="true"
							requiredMessage="Campo uf obrigatório!">
							<f:selectItem itemValue="" itemLabel="UF" />
							<f:selectItems value="#{ufMB.ufs}" />
						</h:selectOneMenu>
						<h:message for="uf" errorClass="campoError" style="color:Red;" />
						</h:panelGrid>	
						</fieldset>	
							
							
						<fieldset>							
						<legend>Acesso ao Sistema</legend>	
						
						<h:panelGrid columns="3" id="acesso">
						
						<h:outputLabel for="email" value="E-mail *" />
						<h:inputText id="email" value="#{empresaMB.empresa.email}"
							required="true" requiredMessage="Campo e-mail obrigatório!" styleClass="edit" size="30">
							<f:validator validatorId="emailEmpresaValidator" />
							<f:validator validatorId="emailClienteValidator" />
						</h:inputText>
						<h:message for="email" errorClass="campoError" style="color:Red;" />

						<h:outputLabel for="login" value="login *" />
						<h:inputText id="login" value="#{empresaMB.empresa.usuario.login}" required="true" requiredMessage="Campo login obrigatório!" styleClass="edit" size="30">
							<f:validator validatorId="loginValidator"/>
						</h:inputText>
						<h:message for="login" errorClass="campoError" style="color:Red;" />

						<h:outputLabel for="senha" value="Senha *" />
						<h:inputSecret id="senha"
							value="#{empresaMB.empresa.usuario.senha}" required="true"
							requiredMessage="Campo senha obrigatório!" styleClass="edit"
							size="30" />
						<h:message for="senha" errorClass="campoError" style="color:Red;" />

						<h:outputLabel for="confirmaSenha" value=" Confirme a Senha " />
						<h:inputSecret id="confirmaSenha"
							value="#{empresaMB.confirmarSenha}" required="true"
							requiredMessage="Campo confirme a senha obrigatório!"
							styleClass="edit" size="30" />
						<h:message for="confirmaSenha" errorClass="campoError" style="color:Red;" />					
					</h:panelGrid>
					
					</fieldset>
					
					<a4j:commandButton value="Inserir" action="#{empresaMB.save}" status="sts" reRender="pessoais,InfoEndereco,acesso,erros" styleClass="botoes"  />
						<a4j:commandButton value="Voltar" immediate="true"
							action="toEscolheUsuario" styleClass="botoes" />
							
				</fieldset>
				<fieldset style="background-color:#D9FFD9;border: 0;">
					<h:outputText>© SISPO - 2012 | O SISPO é um sistema para controle de pedidos de empresas do ramo de pizzaria, com código fonte aberto, com características da web 2.0, para possibilitar o aumento de vendas, a elevação da abrangência e a concentração de empresas desse ramo de forma econômica e prática para os clientes e pizzarias. </h:outputText>
				</fieldset>
			</h:form>
		</div>
		<div id="testa" >
			<h:outputText><a style="text-decoration: none;color: white"  href="/sispo/pages/usuario/paginaInicial.jsf">HOME</a> |</h:outputText>
		</div>	
	</f:view>
</body>
</html>