package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.SalasDeTeatro;
import br.ufscar.dc.dsw.service.spec.ISalasDeTeatroService;


@Controller
@RequestMapping("/salasdeteatro")
public class SalasDeTeatroController {

	@Autowired
	private ISalasDeTeatroService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(SalasDeTeatro salasdeteatro) {
		return "salasdeteatro/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("salasdeteatro",service.buscarTodos());
		return "salasdeteatro/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid SalasDeTeatro salasdeteatro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "salasdeteatro/cadastro";
		}
		
		service.salvar(salasdeteatro);
		attr.addFlashAttribute("success", "Sala de teatro inserida com sucesso.");
		return "redirect:/salasdeteatro/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("salasdeteatro", service.buscarPorId(id));
		return "salasdeteatro/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid SalasDeTeatro salasdeteatro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "salasdeteatro/cadastro";
		}

		service.salvar(salasdeteatro);
		attr.addFlashAttribute("success", "Sala de teatro editada com sucesso.");
		return "redirect:/salasdeteatro/listar";
	}
	
//	@GetMapping("/excluir/{id}")
//	public String excluir(@PathVariable("id") Long id, ModelMap model) {
//		if (service.editoraTemLivros(id)) {
//			model.addAttribute("fail", "Editora não excluída. Possui livro(s) vinculado(s).");
//		} else {
//			service.excluir(id);
//			model.addAttribute("sucess", "Editora excluída com sucesso.");
//		}
//		return listar(model);
//	}
	
	
	
}
