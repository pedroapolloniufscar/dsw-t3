package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.SalasDeTeatro;
import br.ufscar.dc.dsw.domain.SitesDeVenda;
import br.ufscar.dc.dsw.service.spec.ISalasDeTeatroService;
import br.ufscar.dc.dsw.service.spec.ISitesDeVendaService;



@Controller
@RequestMapping("/sitesdevenda")
public class SitesDeVendaController {

	@Autowired
	private ISitesDeVendaService sitesdevendaService;

	@Autowired
	private ISalasDeTeatroService salasdeteatroService;

	@GetMapping("/cadastrar")
	public String cadastrar(SitesDeVenda sitesdevenda) {
		return "sitesdevenda/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("sitesdevenda", sitesdevendaService.buscarTodos());
		return "sitesdevenda/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid SitesDeVenda sitesdevenda, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "sitesdevenda/cadastro";
		}

		sitesdevendaService.salvar(sitesdevenda);
		attr.addFlashAttribute("sucess", "Site de Venda inserido com sucesso");
		return "redirect:/sitesdevenda/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("sitesdevenda", sitesdevendaService.buscarPorId(id));
		return "sitesdevenda/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid SitesDeVenda sitesdevenda, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "sitesdevenda/cadastro";
		}

		sitesdevendaService.salvar(sitesdevenda);
		attr.addFlashAttribute("sucess", "Site de venda editado com sucesso.");
		return "redirect:/sitesdevenda/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		sitesdevendaService.excluir(id);
		attr.addFlashAttribute("sucess", "Site de venda excluído com sucesso.");
		return "redirect:/sitesdevenda/listar";
	}

	@ModelAttribute("salasdeteatro")
	public List<SalasDeTeatro> listaSalasDeTeatro() {
		return salasdeteatroService.buscarTodos();
	}
	
}
