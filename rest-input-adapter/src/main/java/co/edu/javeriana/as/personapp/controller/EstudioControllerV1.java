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

import co.edu.javeriana.as.personapp.adapter.EstudioInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/estudio")
public class EstudioControllerV1 {
	
	@Autowired
	private EstudioInputAdapterRest estudioInputAdapterRest;
	
	@ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EstudioResponse> estudios(@PathVariable String database) {
		log.info("Into estudios REST API");
		return estudioInputAdapterRest.historial(database.toUpperCase());
	}
	
	@ResponseBody
	@GetMapping(path = "/{database}/{professionId}/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstudioResponse obtenerEstudio(@PathVariable String database, @PathVariable String professionId, @PathVariable String personId) {
		log.info("Into obtenerEstudio REST API");
		return estudioInputAdapterRest.obtenerEstudio(database.toUpperCase(), professionId, personId);
	}
	
	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public EstudioResponse crearEstudio(@RequestBody EstudioRequest request) {
		log.info("Into crearEstudio REST API");
		return estudioInputAdapterRest.crearEstudio(request);
	}
	
	@ResponseBody
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public EstudioResponse actualizarEstudio(@RequestBody EstudioRequest request) {
		log.info("Into actualizarEstudio REST API");
		return estudioInputAdapterRest.actualizarEstudio(request);
	}
	
	@ResponseBody
	@DeleteMapping(path = "/{database}/{professionId}/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean eliminarEstudio(@PathVariable String database, @PathVariable String professionId, @PathVariable String personId) {
		log.info("Into eliminarEstudio REST API");
		return estudioInputAdapterRest.eliminarEstudio(database.toUpperCase(), professionId, personId);
	}
}
