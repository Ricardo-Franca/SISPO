package br.com.sispo.view;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sispo.controller.EmpresaFacade;
import br.com.sispo.controller.EmpresaFacadeImpl;
import br.com.sispo.controller.PedidoDePizzaFacade;
import br.com.sispo.controller.PedidoDePizzaFacadeImpl;
import br.com.sispo.controller.PedidoFacade;
import br.com.sispo.controller.PedidoFacadeImpl;
import br.com.sispo.controller.PizzaFacade;
import br.com.sispo.controller.PizzaFacadeImpl;
import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;
import br.com.sispo.model.entity.Pedido;
import br.com.sispo.model.entity.PedidoDePizza;
import br.com.sispo.model.entity.Pizza;
import br.com.sispo.model.entity.Situacao;
import javax.faces.model.SelectItem;
public class RelatorioMB implements Serializable {

	private static final long serialVersionUID = 2273212693618648253L;
    
	private String dataEspecifica;
	private String dataInicial;
	private String dataFinal;
	private Long ano;
	private  List<SelectItem> anos;
	
	public String getHoje()
	{
		Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = (String)formatador.format(data);
        return dataFormatada;
	}
	
	public void escolheRelatorioDiario() throws IOException
	{							
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
        Integer tipoRelatorio = 1;
        session.setAttribute("tipoRelatorio", tipoRelatorio);        
        rp.sendRedirect(rq.getContextPath() + "/pages/empresa/relatorioGerado.jsf");		
	}
	
	public void escolheRelatorioEspecifico() throws IOException, ParseException
	{							
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
        Integer tipoRelatorio = 2;
        String data = this.dataEspecifica;
           
        String dia = data.substring(8,10);
        String mes = data.substring(4,7);
        String ano = data.substring(24,28);        
        mes = validaMes(mes);
        
        this.dataEspecifica = dia+"/"+mes+"/"+ano;
        
        session.setAttribute("dataEspecifica", this.dataEspecifica);
        session.setAttribute("tipoRelatorio", tipoRelatorio);        
        rp.sendRedirect(rq.getContextPath() + "/pages/empresa/relatorioGerado.jsf");
	}
	
	public void escolheRelatorioPorPeriodo() throws IOException, ParseException
	{							
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
        Integer tipoRelatorio = 3;
        String dataInicial = this.dataInicial;
        String dataFinal = this.dataFinal;
           
        String diaInicial = dataInicial.substring(8,10);
        String mesInicial = dataInicial.substring(4,7);
        String anoInicial = dataInicial.substring(24,28);        
        mesInicial = validaMes(mesInicial);
        
        this.dataInicial = diaInicial+"/"+mesInicial+"/"+anoInicial;
        
        String diaFinal = dataFinal.substring(8,10);
        String mesFinal = dataFinal.substring(4,7);
        String anoFinal = dataFinal.substring(24,28);        
        mesFinal = validaMes(mesFinal);
        
        this.dataFinal = diaFinal+"/"+mesFinal+"/"+anoFinal;
        
        session.setAttribute("dataInicial", this.dataInicial);
        session.setAttribute("dataFinal", this.dataFinal);
        session.setAttribute("tipoRelatorio", tipoRelatorio);        
        rp.sendRedirect(rq.getContextPath() + "/pages/empresa/relatorioGerado.jsf");

	}
	
	public void escolheRelatorioAnual() throws IOException, ParseException
	{							
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
        Integer tipoRelatorio = 4;
        
        session.setAttribute("ano", this.ano);
        session.setAttribute("tipoRelatorio", tipoRelatorio);        
        rp.sendRedirect(rq.getContextPath() + "/pages/empresa/relatorioGerado.jsf");
	}
	
	public ArrayList<Pedido> getGerarRelatorio() throws IOException
	{	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		PedidoFacade pedidoService = new PedidoFacadeImpl();
		
		Empresa empresa = (Empresa)session.getAttribute("empresaLogado");
		Integer tipoRelatorio = (Integer)session.getAttribute("tipoRelatorio");
		
		if(tipoRelatorio==1)
		{
			return (ArrayList<Pedido>)pedidoService.pesquisaRelatorioDiario(empresa.getCodigo(),getHoje());
		}else
		 {
			if(tipoRelatorio==2)
			{
				this.dataEspecifica = (String)session.getAttribute("dataEspecifica");
				return (ArrayList<Pedido>)pedidoService.pesquisaRelatorioDiario(empresa.getCodigo(),this.dataEspecifica);
			}else
			 {
				if(tipoRelatorio==3)
				{
					this.dataInicial = (String)session.getAttribute("dataInicial");
					this.dataFinal = (String)session.getAttribute("dataFinal");
					return (ArrayList<Pedido>)pedidoService.pesquisaRelatorioPorPeriodo(empresa.getCodigo(),this.dataInicial,this.dataFinal);
				}else
				 {
					if(tipoRelatorio==4)
					{
						this.ano = (Long)session.getAttribute("ano");
						return (ArrayList<Pedido>)pedidoService.pesquisaRelatorioAnual(empresa.getCodigo(),String.valueOf(this.ano));
					}
				 }
			 }
		 }
		
		return null;			
	}
	
	public String validaMes(String mes)
	{
		if(mes.equals("Jan"))
        {
        	mes = "01";
        }else
         {
        	if(mes.equals("Feb"))
            {
            	mes = "02";
            }else
             {
            	if(mes.equals("Mar"))
                {
                	mes = "03";
                }else
                 {
                	if(mes.equals("Apr"))
                    {
                    	mes = "04";
                    }else
                     {
                    	if(mes.equals("May"))
                        {
                        	mes = "05";
                        }else
                         {
                        	if(mes.equals("Jun"))
                            {
                            	mes = "06";
                            }else
                             {
                            	if(mes.equals("Jul"))
                                {
                                	mes = "07";
                                }else
                                 {
                                	if(mes.equals("Aug"))
                                    {
                                    	mes = "08";
                                    }else
                                     {
                                    	if(mes.equals("Sep"))
                                        {
                                        	mes = "09";
                                        }else
                                         {
                                        	if(mes.equals("Oct"))
                                            {
                                            	mes = "10";
                                            }else
                                             {
                                            	if(mes.equals("Nov"))
                                                {
                                                	mes = "11";
                                                }else
                                                 {
                                                	if(mes.equals("Dec"))
                                                    {
                                                    	mes = "12";
                                                    }
                                                 }
                                             }
                                         }
                                     }
                                 }
                             }
                         }
                     }
                 }	
             }
         } 
		
		return mes;		
	}
	
	public static void main(String[] args)
	{
		PedidoFacade pedidoService = new PedidoFacadeImpl();		
		//Empresa empresa = (Empresa)session.getAttribute("empresaLogado");
		ArrayList<String> anos = (ArrayList<String>) pedidoService.pesquisaAnos(1l);

		Integer tamanho = anos.size();
		
		for(int i=0;i<tamanho;i++)
		{
			System.out.println("Ano:"+anos.get(i));
		}
	}	
	
	public List<SelectItem> getAnos() 
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		PedidoFacade pedidoService = new PedidoFacadeImpl();		
		Empresa empresa = (Empresa)session.getAttribute("empresaLogado");
		ArrayList<String> listaAnos = (ArrayList<String>) pedidoService.pesquisaAnos(empresa.getCodigo());

		Integer tamanho = listaAnos.size();
		
		this.anos = new ArrayList<SelectItem>();
		for(int i=0;i<tamanho;i++)
		{			
			this.anos.add(new SelectItem(Long.parseLong(listaAnos.get(i)),listaAnos.get(i)));
		}
		return anos;
	}

	public void setAnos(List<SelectItem> anos) {
		this.anos = anos;
	
}
	

	public String getDataEspecifica() {
		return dataEspecifica;
	}

	public void setDataEspecifica(String dataEspecifica) {
		this.dataEspecifica = dataEspecifica;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}	
}

/*
   ORGANIZAR ORDEM DO RESULTADO DE CONSULTA.
   CRIAR VALIDAÇÃO DE DATA INICIAL MAIOR QUE DATA FINAL.

 */