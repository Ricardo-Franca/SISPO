package br.com.sispo.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.sispo.controller.SituacaoFacade;
import br.com.sispo.controller.SituacaoFacadeImpl;
import br.com.sispo.model.entity.Situacao;

public class SituacaoMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;

	private List<SelectItem> situacoes;
	
	private Situacao situacao = new Situacao();
	
	public SituacaoMB(){
		System.out.println(" >>>>>>>>>>>>>>>>>>>> Contrutor da Situacao_MB <<<<<<<<<<<<<<<<<<");
		
		if(this.situacao == null){
			this.situacao = new Situacao(); 			
		}
	}

	public String save(){
		SituacaoFacade situacaoService = new SituacaoFacadeImpl();
			
		situacaoService.salva(this.situacao);
		
		this.situacao = new Situacao(); 
		return "cadastraSucesso";
	}
	
	public List<SelectItem> getSituacoes()
	{
		if(this.situacoes == null)
		{
			this.situacoes = new ArrayList<SelectItem>();
			this.situacoes.add(new SelectItem("2","Preparando"));
			this.situacoes.add(new SelectItem("3","Saiu para entrega"));
			this.situacoes.add(new SelectItem("4","Entregue"));	
		}
		return situacoes;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}	
}