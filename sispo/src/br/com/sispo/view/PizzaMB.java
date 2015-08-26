package br.com.sispo.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sispo.controller.EmpresaFacade;
import br.com.sispo.controller.EmpresaFacadeImpl;
import br.com.sispo.controller.PizzaFacade;
import br.com.sispo.controller.PizzaFacadeImpl;
import br.com.sispo.model.entity.Empresa;
import br.com.sispo.model.entity.Pizza;

public class PizzaMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;

	private Pizza pizza = new Pizza();

	private Long id;
	private Long codigoPizza;
	private static String imagePath;

	public PizzaMB(){
		System.out.println(" >>>>>>>>>>>>>>>>>>>> Contrutor da PIZZA_MB <<<<<<<<<<<<<<<<<<");
		
		if(this.pizza == null){
			this.pizza = new Pizza(); 			
		}
	}	
	
	public String save() throws IOException
	{				
		
		PizzaFacade pizzaService = new PizzaFacadeImpl();	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Empresa empresa = (Empresa)session.getAttribute("empresaLogado");
		this.pizza.setEmpresa(empresa);
		this.pizza.setPizza_imagem(getImagePath());
		pizzaService.salva(this.pizza);		
		this.pizza = new Pizza();
		
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        rp.sendRedirect(rq.getContextPath() + "/pages/empresa/consultaPizzas.jsf");
		return "cadastraSucesso";
	}
	
	public String deletePizzas() throws IOException{
		PizzaFacade pizzaService = new PizzaFacadeImpl();
		this.pizza.setCodigo(id);
		//this.pizza.setEmpresa(null);
		pizzaService.inativaPizza(this.pizza.getCodigo());
		//pizzaService.remove(this.pizza);
		this.pizza = new Pizza();	
		
		return "removeSucesso";
	}
	
	public String update() throws IOException{
		PizzaFacade pizzaService = new PizzaFacadeImpl();
		pizzaService.atualiza(this.pizza);
		this.pizza = new Pizza();

		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        rp.sendRedirect(rq.getContextPath() + "/pages/empresa/atualizaPizza.jsf");
		return "atualizaSucesso";
	}
	
	public String updateCadastro() throws IOException {
		PizzaFacade pizzaService = new PizzaFacadeImpl();
		this.getCadastroPizza().setPizza_imagem(getImagePath());
		pizzaService.atualiza(this.getCadastroPizza());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("pizzaAtual", this.getCadastroPizza());
		this.pizza = new Pizza();

		return "atualizaSucesso";
	}
	
	public String load(){
		PizzaFacade pizzaService = new PizzaFacadeImpl();
		this.pizza = pizzaService.procura(this.id);
		
		return "pesquisaSucesso";
	}
	
	public void escolhePizza() throws IOException
	{	
		Pizza pizza = new Pizza();
		PizzaFacade pizzaService = new PizzaFacadeImpl();
		pizza = pizzaService.procuraById(codigoPizza);
						
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
        session.setAttribute("pizzaAtual", pizza);	
		
        rp.sendRedirect(rq.getContextPath() + "/pages/empresa/atualizaPizza.jsf");		
	}
	
	public Pizza getCadastroPizza() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Pizza cadastroPizza = new Pizza();
		cadastroPizza = (Pizza) session.getAttribute("pizzaAtual");

		return cadastroPizza;
	}
	
	public List<Pizza> getPizzas(){
		PizzaFacade pizzaService = new PizzaFacadeImpl();		
		
		return pizzaService.lista();
	}
	
	public List<Pizza> getSuasPizzas()
	{
		PizzaFacade pizzaService = new PizzaFacadeImpl();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Empresa empresa = (Empresa)session.getAttribute("empresaLogado");
		
		return (List)pizzaService.pesquisaPizzasByCodigoEmpresa(empresa.getCodigo());
		
	}
	
	public void  uploadImage(UploadEvent evento) throws FileNotFoundException {
		FacesContext fc= FacesContext.getCurrentInstance();
		ServletContext sc= (ServletContext)fc.getExternalContext().getContext();
		String caminhoReal = sc.getRealPath("/");
		String extensao = "";
		UploadItem item = evento.getUploadItem();
		String fileName = item.getFileName();
		String ext[] = fileName.split("\\.");
		int i = ext.length;

		if (i > 1) {
			extensao = ext[i - 1];

		}


		Long tempo = System.currentTimeMillis();
		OutputStream out = new FileOutputStream(
				caminhoReal+"/ImagensPizza/" + "img"
						+ tempo + "." + extensao);
		setImagePath("/ImagensPizza/" + "img"
				+ tempo + "." + extensao);
		
		try {
			out.write(item.getData());
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Long getCodigo() {
		return id;
	}

	public void setCodigo(Long id) {
		this.id = id;
	}

	public Long getCodigoPizza() {
		return codigoPizza;
	}

	public void setCodigoPizza(Long codigoPizza) {
		this.codigoPizza = codigoPizza;
	}

	public static String getImagePath() {
		return imagePath;
	}

	public static void setImagePath(String imagePath) {
		PizzaMB.imagePath = imagePath;
	}	
}