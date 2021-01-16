package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.SalasDeTeatro;

public interface ISalasDeTeatroService {
	SalasDeTeatro buscarPorId(Long id);
	List<SalasDeTeatro> buscarTodos();
	void salvar(SalasDeTeatro salasdeteatro);
	void excluir(Long id);
}
