package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Promo;
import br.ufscar.dc.dsw.domain.SitesDeVenda;
import br.ufscar.dc.dsw.service.spec.IPromoService;


@CrossOrigin
@RestController
public class PromoRestController {

	@Autowired
	private IPromoService service;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void parse(SitesDeVenda estado, JSONObject json) {
		Map<String, Object> map = (Map<String, Object>) json.get("estado");
		
		Object id = map.get("id");
		if (id instanceof Integer) {
			estado.setId(((Integer) id).longValue());
		} else {
			estado.setId((Long) id);
		}
		 		
		estado.setEndereco((String) map.get("endereco"));
		estado.setNome((String) map.get("nome"));
	}

	private void parse(Promo promo, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				promo.setId(((Integer) id).longValue());
			} else {
				promo.setId((Long) id);
			}
		}

		promo.setNomepeca((String) json.get("nome da peça"));

		SitesDeVenda estado = new SitesDeVenda();
		parse(estado, json);
		promo.setSitedevenda(estado);
	}

	@GetMapping(path = "/promo")
	public ResponseEntity<List<Promo>> lista() {
		List<Promo> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/promo/{id}")
	public ResponseEntity<Promo> lista(@PathVariable("id") long id) {
		Promo promo = service.buscarPorId(id);
		if (promo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(promo);
	}

	/*
	 * @GetMapping(path = "/promo/sitesdevenda/{id}") public
	 * ResponseEntity<List<Promo>> listaPorSitesDeVenda(@PathVariable("id") long id)
	 * {
	 * 
	 * List<Promo> lista = service.buscarPorId(id);
	 * 
	 * if (lista.isEmpty()) { return ResponseEntity.notFound().build(); } return
	 * ResponseEntity.ok(lista); }
	 */

	/*
	 * @GetMapping(path = "/promo/filtros") public ResponseEntity<List<String>>
	 * listaPorNome(@RequestParam(name = "term") String nome) { List<Promo> promo =
	 * service.buscarPorNome("%" + nome + "%"); List<String> lista = new
	 * ArrayList<>(); for (Promo c : promo) { lista.add(c.getNomePeca() + "/" +
	 * c.getSitedevenda().getEndereco()); } return ResponseEntity.ok(lista); }
	 */

	@PostMapping(path = "/promo")
	@ResponseBody
	public ResponseEntity<Promo> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Promo promo = new Promo();
				parse(promo, json);
				service.salvar(promo);
				return ResponseEntity.ok(promo);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/promo/{id}")
	public ResponseEntity<Promo> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Promo promo = service.buscarPorId(id);
				if (promo == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(promo, json);
					service.salvar(promo);
					return ResponseEntity.ok(promo);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/promo/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Promo promo = service.buscarPorId(id);
		if (promo == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}