package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Promo;


public interface IPromoService {
	Promo buscarPorId(Long id);
	List<Promo> buscarTodos();
	void salvar(Promo promo);
	void excluir(Long id);
	
	List<Promo> findBySitesDeVenda(Long id);
	List<Promo> findByNome(String nome);
}
