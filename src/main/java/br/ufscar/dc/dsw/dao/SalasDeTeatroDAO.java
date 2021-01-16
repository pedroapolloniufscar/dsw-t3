package br.ufscar.dc.dsw.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.SalasDeTeatro;
import org.springframework.data.repository.query.Param;
	
	@EnableJpaRepositories("br.ufscar.dc.dsw")
	@SuppressWarnings("unchecked")
	public interface SalasDeTeatroDAO extends CrudRepository<SalasDeTeatro, Long>{
		SalasDeTeatro findById(long id);
		List<SalasDeTeatro> findAll();
		SalasDeTeatro save(SalasDeTeatro salasdeteatro);
		void deleteById(Long id);
	
	
}
