package br.ufscar.dc.dsw.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.dao.SalasDeTeatroDAO;
import br.ufscar.dc.dsw.domain.SalasDeTeatro;
import br.ufscar.dc.dsw.service.spec.ISalasDeTeatroService;

@EnableJpaRepositories("br.ufscar.dc.dsw.dao")
@Service
@Transactional(readOnly = false)
public class SalasDeTeatroService implements ISalasDeTeatroService {

	@Autowired
	SalasDeTeatroDAO dao;
	
	public void salvar(SalasDeTeatro salasdeteatro) {
		dao.save(salasdeteatro);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public SalasDeTeatro buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<SalasDeTeatro> buscarTodos() {
		return dao.findAll();
	}
	

}
