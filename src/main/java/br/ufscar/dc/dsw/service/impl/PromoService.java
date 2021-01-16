package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.PromoDAO;
import br.ufscar.dc.dsw.dao.SitesDeVendaDAO;
import br.ufscar.dc.dsw.domain.Promo;
import br.ufscar.dc.dsw.domain.SitesDeVenda;
import br.ufscar.dc.dsw.service.spec.IPromoService;

@Service
@Transactional(readOnly = false)
public class PromoService implements IPromoService {
	@Autowired
	PromoDAO dao;
	
	@Autowired
	SitesDeVendaDAO sitesdevendaDAO;
	
	public void salvar(Promo promo) {
		dao.save(promo);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Promo buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Promo> buscarTodos() {
		return dao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Promo> findBySitesDeVenda(Long id) {
		SitesDeVenda sitesdevenda = sitesdevendaDAO.findById(id.longValue());
		return dao.findBySitesDeVenda(sitesdevenda);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Promo> findByNome(String nome) {
		return dao.findByNomeLikeIgnoreCase(nome);
	}

	

}
