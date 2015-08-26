package br.com.sispo.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.sispo.controller.UfFacade;
import br.com.sispo.controller.UfFacadeImpl;
import br.com.sispo.controller.UsuarioFacade;
import br.com.sispo.controller.UsuarioFacadeImpl;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Uf;
import br.com.sispo.model.entity.Usuario;

public class UfMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;

	private List<SelectItem> ufs;
	
	private Uf uf = new Uf();
	
	public UfMB(){
		System.out.println(" >>>>>>>>>>>>>>>>>>>> Contrutor da Uf_MB <<<<<<<<<<<<<<<<<<");
		
		if(this.uf == null){
			this.uf = new Uf(); 			
		}
	}

	public String save(){
		UfFacade uFService = new UfFacadeImpl();
			
		uFService.salva(this.uf);
		
		this.uf = new Uf(); 
		return "cadastraSucesso";
	}
	
	public List<SelectItem> getUfs()
	{
		if(this.ufs == null)
		{
			this.ufs = new ArrayList<SelectItem>();
			this.ufs.add(new SelectItem("1","AC"));
			this.ufs.add(new SelectItem("2","AL"));
			this.ufs.add(new SelectItem("3","AP"));
			this.ufs.add(new SelectItem("4","AM"));			
			this.ufs.add(new SelectItem("5","BA"));
			this.ufs.add(new SelectItem("6","CE"));
			this.ufs.add(new SelectItem("7","DF"));
			this.ufs.add(new SelectItem("8","ES"));
			this.ufs.add(new SelectItem("9","GO"));
			this.ufs.add(new SelectItem("10","MA"));
			this.ufs.add(new SelectItem("11","MT"));
			this.ufs.add(new SelectItem("12","MS"));
			this.ufs.add(new SelectItem("13","MG"));
			this.ufs.add(new SelectItem("14","PA"));
			this.ufs.add(new SelectItem("15","PB"));
			this.ufs.add(new SelectItem("16","PR"));
			this.ufs.add(new SelectItem("17","PE"));
			this.ufs.add(new SelectItem("18","PI"));
			this.ufs.add(new SelectItem("19","RJ"));
			this.ufs.add(new SelectItem("20","RN"));
			this.ufs.add(new SelectItem("21","RS"));
			this.ufs.add(new SelectItem("22","RO"));
			this.ufs.add(new SelectItem("23","RR"));
			this.ufs.add(new SelectItem("24","SC"));
			this.ufs.add(new SelectItem("25","SP"));
			this.ufs.add(new SelectItem("26","SE"));
			this.ufs.add(new SelectItem("27","TO"));		
		}
		return ufs;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}		
}