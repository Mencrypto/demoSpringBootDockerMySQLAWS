package mx.ipn.cic.controlescolar.demo.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.CICException;
import mx.ipn.cic.controlescolar.demo.models.RolModel;
import mx.ipn.cic.controlescolar.demo.services.IRolService;

@RestController
@RequestMapping(path="/rest/rol")
public class RolRestController {
	
	private static final Log LOGGER = LogFactory.getLog(RolRestController.class);

	@Autowired
	@Qualifier("Rol")
	private IRolService rolService;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<RolModel>> getAll(){
		List<RolModel> all = rolService.findAll();
		return new ResponseEntity<>(all,HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
//	public RolModel getById(@PathVariable("id") int id) {
	public ResponseEntity<RolModel> getById(@PathVariable("id") int id) {
		ResponseEntity<RolModel> response = null;
		try {
			RolModel rol = rolService.findById(id);
			response = new ResponseEntity<RolModel>(rol, HttpStatus.OK);
			return response;
		}catch(CICException e) {
			LOGGER.error(e.getMessage());
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.set("Custom_Response_Value",e.getMessage());
			response = new ResponseEntity<RolModel>(headers,HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return response;
	}
	
	@PostMapping
	public ResponseEntity<RolModel> createNew(@RequestBody RolModel rol){
		rol = rolService.create(rol);
		return new ResponseEntity<>(rol,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<RolModel> update(@RequestBody RolModel rol) {
		ResponseEntity<RolModel> response = null;
		try {
			rol = rolService.update(rol);
			response = new ResponseEntity<>(rol,HttpStatus.ACCEPTED);
			return response;
		}catch(CICException e) {
			LOGGER.error(e.getMessage());
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.set("ERROR_MESSAGE",e.getMessage());
			response = new ResponseEntity<RolModel>(headers,HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
		ResponseEntity<Boolean> response = null;
		try {
			RolModel rol = rolService.findById(id);
			rolService.delete(rol);
			response = new ResponseEntity<>(true, HttpStatus.ACCEPTED);
			return response;
		}catch(CICException e) {
			LOGGER.error(e.getMessage());
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.set("ERROR_MESSAGE",e.getMessage());
			response = new ResponseEntity<Boolean>(false, headers,HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
}
