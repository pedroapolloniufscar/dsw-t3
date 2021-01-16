package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.SitesDeVendaDAO;
import br.ufscar.dc.dsw.domain.SitesDeVenda;
import br.ufscar.dc.dsw.service.spec.ISitesDeVendaService;

@Service
@Transactional(readOnly = false)
public class SitesDeVendaService implements ISitesDeVendaService {
	@Autowired
	SitesDeVendaDAO dao;
	
	public void salvar(SitesDeVenda sitesdevenda) {
		dao.save(sitesdevenda);
	}
	
	public void excluir(Long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public SitesDeVenda buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<SitesDeVenda> buscarTodos() {
		return dao.findAll();
	}
	

}
