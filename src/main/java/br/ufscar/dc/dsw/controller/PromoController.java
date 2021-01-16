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

import br.ufscar.dc.dsw.domain.Promo;
import br.ufscar.dc.dsw.service.spec.IPromoService;
import br.ufscar.dc.dsw.service.spec.ISitesDeVendaService;


@Controller
@RequestMapping("/promo")
public class PromoController {

	@Autowired
	private IPromoService promoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Promo promo) {
		return "promo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("promo", promoService.buscarTodos());
		return "promo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Promo promo, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "promo/cadastro";
		}

		promoService.salvar(promo);
		attr.addFlashAttribute("success", "Promocao inserida com sucesso");
		return "redirect:/promo/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("promo", promoService.buscarPorId(id));
		return "promo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Promo promo, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "promo/cadastro";
		}

		promoService.salvar(promo);
		attr.addFlashAttribute("success", "Promocao editada com sucesso.");
		return "redirect:/promo/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		promoService.excluir(id);
		attr.addFlashAttribute("success", "Promocao excluída com sucesso.");
		return "redirect:/promo/listar";
	}

	@ModelAttribute("promo")
	public List<Promo> listaPromo() {
		return promoService.buscarTodos();
	}
}
