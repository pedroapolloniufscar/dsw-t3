package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Promo;
import br.ufscar.dc.dsw.domain.SitesDeVenda;

@EnableJpaRepositories("br.ufscar.dc.dsw")
@SuppressWarnings("unchecked")
public interface PromoDAO extends CrudRepository<Promo, Long>{

	Promo findById(long id);
	List<Promo> findAll();
	Promo save(Promo promo);
	void deleteById(Long id);
	
	public List<Promo> findByNomeLikeIgnoreCase(String nome);
	
	@Query("select c from Promo c where sitesdevenda = :sitesdevenda")
	public List<Promo> findBySitesDeVenda(@Param("sitesdevenda") SitesDeVenda sitesdevenda);
}
