package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.SitesDeVenda;


@SuppressWarnings("unchecked")
public interface SitesDeVendaDAO extends CrudRepository<SitesDeVenda, Long>{

	SitesDeVenda findById(long id);
	List<SitesDeVenda> findAll();
	SitesDeVenda save(SitesDeVenda sitesdevenda);
	void deleteById(Long id);
	

}
