package br.com.sispo.controller;

import java.util.List;

import br.com.sispo.model.entity.Cliente;
import br.com.sispo.model.entity.Empresa;

public interface EmpresaFacade extends BaseFacade<Empresa>{
	public void salva(Empresa e);

	public void remove(Empresa e);
	
	public Empresa procura(Long id);

	public void atualiza(Empresa e);
	
	public List<Empresa> pesquisaEmpresasByNome(String nome);
	
	public Empresa procuraByUsuarioId(Long codigo);
	
	public Empresa procuraByCodigo(Long codigo);
	
	public Empresa procuraByEmail(String email);
	
	public Empresa procuraByCnpj(String cnpj);
	
	public void inativaEmpresa(Long codigo);
	
	public List<Empresa> pesquisaEmpresasAtivas();
}