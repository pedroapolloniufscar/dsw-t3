package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.SitesDeVenda;

public interface ISitesDeVendaService {
	
	SitesDeVenda buscarPorId(Long id);
	List<SitesDeVenda> buscarTodos();
	void salvar(SitesDeVenda sitesdevenda);
	void excluir(Long id);

}
