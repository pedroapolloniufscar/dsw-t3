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

import br.ufscar.dc.dsw.domain.SalasDeTeatro;
import br.ufscar.dc.dsw.service.spec.ISalasDeTeatroService;


@CrossOrigin
@RestController
public class SalasDeTeatroRestController {

	@Autowired
 private ISalasDeTeatroService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
 }

	private void parse(SalasDeTeatro salasdeteatro, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				salasdeteatro.setId(((Integer) id).longValue());
			} else {
				salasdeteatro.setId((Long) id);
			}
 	}

		salasdeteatro.setNome((String) json.get("nome"));
		salasdeteatro.setCidade((String) json.get("cidade"));
 }

	@GetMapping(path = "/salasdeteatro")
	public ResponseEntity<List<SalasDeTeatro>> lista() {
		List<SalasDeTeatro> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 }

	@GetMapping(path = "/salasdeteatro/{id}")
	public ResponseEntity<SalasDeTeatro> lista(@PathVariable("id") long id) {
		SalasDeTeatro salasdeteatro = service.buscarPorId(id);
		if (salasdeteatro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(salasdeteatro);
 }

	@PostMapping(path = "/salasdeteatro")
	@ResponseBody
	public ResponseEntity<SalasDeTeatro> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				SalasDeTeatro salasdeteatro = new SalasDeTeatro();
				parse(salasdeteatro, json);
				service.salvar(salasdeteatro);
				return ResponseEntity.ok(salasdeteatro);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@PutMapping(path = "/salasdeteatro/{id}")
	public ResponseEntity<SalasDeTeatro> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				SalasDeTeatro salasdeteatro = service.buscarPorId(id);
				if (salasdeteatro == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(salasdeteatro, json);
					service.salvar(salasdeteatro);
					return ResponseEntity.ok(salasdeteatro);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@DeleteMapping(path = "/salasdeteatro/{id}")
 public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		SalasDeTeatro salasdeteatro = service.buscarPorId(id);
		if (salasdeteatro == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}