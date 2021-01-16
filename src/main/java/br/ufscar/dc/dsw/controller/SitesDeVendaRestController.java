package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.SitesDeVenda;
import br.ufscar.dc.dsw.service.spec.ISitesDeVendaService;

@CrossOrigin
@RestController
public class SitesDeVendaRestController {

	@Autowired
 private ISitesDeVendaService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
 }

	private void parse(SitesDeVenda sitesdevenda, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				sitesdevenda.setId(((Integer) id).longValue());
			} else {
				sitesdevenda.setId((Long) id);
			}
 	}

		sitesdevenda.setNome((String) json.get("nome"));
		sitesdevenda.setEndereco((String) json.get("endereço"));
 }

	@GetMapping(path = "/sitesdevenda")
	public ResponseEntity<List<SitesDeVenda>> lista() {
		List<SitesDeVenda> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 }

	@GetMapping(path = "/sitesdevenda/{id}")
	public ResponseEntity<SitesDeVenda> lista(@PathVariable("id") long id) {
		SitesDeVenda sitesdevenda = service.buscarPorId(id);
		if (sitesdevenda == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(sitesdevenda);
 }

	@PostMapping(path = "/sitesdevenda")
	@ResponseBody
	public ResponseEntity<SitesDeVenda> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				SitesDeVenda sitesdevenda = new SitesDeVenda();
				parse(sitesdevenda, json);
				service.salvar(sitesdevenda);
				return ResponseEntity.ok(sitesdevenda);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@PutMapping(path = "/sitesdevenda/{id}")
	public ResponseEntity<SitesDeVenda> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				SitesDeVenda sitesdevenda = service.buscarPorId(id);
				if (sitesdevenda == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(sitesdevenda, json);
					service.salvar(sitesdevenda);
					return ResponseEntity.ok(sitesdevenda);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@DeleteMapping(path = "/sitesdevenda/{id}")
 public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		SitesDeVenda sitesdevenda = service.buscarPorId(id);
		if (sitesdevenda == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}