package co.edu.javeriana.as.personapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.as.personapp.adapter.ProfesionInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.ProfesionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/profesion")
public class ProfesionControllerV1 {
	
	@Autowired
	private ProfesionInputAdapterRest profesionInputAdapterRest;
	
	@ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProfesionResponse> profesiones(@PathVariable String database) {
		log.info("Into profesiones REST API");
		return profesionInputAdapterRest.historial(normalizeDatabase(database));
	}
	
	@ResponseBody
	@GetMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProfesionResponse obtenerProfesion(@PathVariable String database, @PathVariable String id) {
		log.info("Into obtenerProfesion REST API");
		return profesionInputAdapterRest.obtenerProfesion(normalizeDatabase(database), id);
	}
	
	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProfesionResponse crearProfesion(@RequestBody ProfesionRequest request) {
		log.info("Into crearProfesion REST API");
		return profesionInputAdapterRest.crearProfesion(request);
	}
	
	@ResponseBody
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProfesionResponse actualizarProfesion(@RequestBody ProfesionRequest request) {
		log.info("Into actualizarProfesion REST API");
		return profesionInputAdapterRest.actualizarProfesion(request);
	}
	
	@ResponseBody
	@DeleteMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean eliminarProfesion(@PathVariable String database, @PathVariable String id) {
		log.info("Into eliminarProfesion REST API");
		return profesionInputAdapterRest.eliminarProfesion(normalizeDatabase(database), id);
	}
	
	private String normalizeDatabase(String database) {
		if (database.toLowerCase().contains("maria")) {
			return "maria";
		} else if (database.toLowerCase().contains("mongo")) {
			return "mongo";
		}
		return database;
	}
}
