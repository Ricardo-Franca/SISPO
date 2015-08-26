package br.com.sispo.util;

import java.io.IOException;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.transaction.Transaction;

import org.apache.catalina.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.sispo.controller.ClienteFacade;
import br.com.sispo.controller.ClienteFacadeImpl;
import br.com.sispo.model.dao.DAO;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Situacao;
import br.com.sispo.model.entity.Status;
import br.com.sispo.model.entity.TipoUsuario;
import br.com.sispo.model.entity.Uf;
import br.com.sispo.model.entity.Usuario;
import br.com.sispo.view.ClienteMB;
import br.com.sispo.view.SituacaoMB;
import br.com.sispo.view.StatusMB;
import br.com.sispo.view.TipoUsuarioMB;
import br.com.sispo.view.UfMB;
import br.com.sispo.view.UsuarioMB;


public class GerarTabelas {
	
	public static void main(String[] args) throws IOException {
		
	
		Configuration cfg = new AnnotationConfiguration();
		cfg.configure();

		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
		
		Status status = new Status();
		StatusMB statusMB = new StatusMB();
		
		
		status.setCodigo(1l);
		status.setDescricao("Ativo");
		statusMB.setStatus(status);
		statusMB.save();
		
		status.setCodigo(2l);
		status.setDescricao("Inativo");
		statusMB.setStatus(status);
		statusMB.save();
		
		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setCodigo(1);
		tipoUsuario.setDescricao("Administrador");
		TipoUsuarioMB tipoUsuarioMB = new TipoUsuarioMB();
		tipoUsuarioMB.setTipoUsuario(tipoUsuario);
		tipoUsuarioMB.save();
		
		tipoUsuario.setCodigo(2);
		tipoUsuario.setDescricao("Empresa");
		tipoUsuarioMB.setTipoUsuario(tipoUsuario);
		tipoUsuarioMB.save();
		
		tipoUsuario.setCodigo(3);
		tipoUsuario.setDescricao("Cliente");
		tipoUsuarioMB.setTipoUsuario(tipoUsuario);
		tipoUsuarioMB.save();
		
		Usuario usuario = new Usuario();
		usuario.setLogin("administrador");
		usuario.setSenha("91f5167c34c400758115c2a6826ec2e3");
		usuario.getTipoUsuario().setCodigo(1);
		usuario.getStatus().setCodigo(1l);
		UsuarioMB usuarioMB = new UsuarioMB();		
		usuarioMB.setUsuario(usuario);
		usuarioMB.save();
		
		Uf uf = new Uf();
		UfMB ufMB = new UfMB();
		
		uf.setCodigo(1l);
		uf.setDescricao("AC");
		ufMB.setUf(uf);
		ufMB.save();		
		
		uf.setCodigo(2l);
		uf.setDescricao("AL");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(3l);
		uf.setDescricao("AP");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(4l);
		uf.setDescricao("AM");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(5l);
		uf.setDescricao("BA");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(6l);
		uf.setDescricao("CE");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(7l);
		uf.setDescricao("DF");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(8l);
		uf.setDescricao("ES");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(9l);
		uf.setDescricao("GO");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(10l);
		uf.setDescricao("MA");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(11l);
		uf.setDescricao("MT");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(12l);
		uf.setDescricao("MS");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(13l);
		uf.setDescricao("MG");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(14l);
		uf.setDescricao("PA");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(15l);
		uf.setDescricao("PB");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(16l);
		uf.setDescricao("PR");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(17l);
		uf.setDescricao("PE");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(18l);
		uf.setDescricao("PI");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(19l);
		uf.setDescricao("RJ");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(20l);
		uf.setDescricao("RN");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(21l);
		uf.setDescricao("RS");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(22l);
		uf.setDescricao("RO");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(23l);
		uf.setDescricao("RR");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(24l);
		uf.setDescricao("SC");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(25l);
		uf.setDescricao("SP");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(26l);
		uf.setDescricao("SE");
		ufMB.setUf(uf);
		ufMB.save();
		
		uf.setCodigo(27l);
		uf.setDescricao("TO");
		ufMB.setUf(uf);
		ufMB.save();
		
		Situacao situacao = new Situacao();
		SituacaoMB situacaoMB = new SituacaoMB();
		
		situacao.setCodigo(1l);
		situacao.setDescricao("Aguardando confirmação");
		situacaoMB.setSituacao(situacao);
		situacaoMB.save();		
		
		situacao.setCodigo(2l);
		situacao.setDescricao("Confirmado");
		situacaoMB.setSituacao(situacao);
		situacaoMB.save();
		
		situacao.setCodigo(3l);
		situacao.setDescricao("Preparando");
		situacaoMB.setSituacao(situacao);
		situacaoMB.save();
		
		situacao.setCodigo(4l);
		situacao.setDescricao("Saiu para entrega");
		situacaoMB.setSituacao(situacao);
		situacaoMB.save();
		
		situacao.setCodigo(5l);
		situacao.setDescricao("Concluído");
		situacaoMB.setSituacao(situacao);
		situacaoMB.save();
		
		situacao.setCodigo(6l);
		situacao.setDescricao("Recusado");
		situacaoMB.setSituacao(situacao);
		situacaoMB.save();
		
		situacao.setCodigo(7l);
		situacao.setDescricao("Cancelado");
		situacaoMB.setSituacao(situacao);
		situacaoMB.save();
	}
}