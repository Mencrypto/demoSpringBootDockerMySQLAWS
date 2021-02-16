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
import mx.ipn.cic.controlescolar.demo.models.UserModel;
import mx.ipn.cic.controlescolar.demo.services.IUserService;

@RestController
@RequestMapping(path="/rest/user")
public class UserRestController {
	
	private static final Log LOGGER = LogFactory.getLog(UserRestController.class);

	@Autowired
	@Qualifier("REAL")
	private IUserService userService;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<UserModel> getAll(){
		List<UserModel> all = userService.findAll();
		return all;
	}
	
	@GetMapping(path="/{id}")
//	public UserModel getById(@PathVariable("id") int id) {
	public ResponseEntity<UserModel> getById(@PathVariable("id") int id) {
		ResponseEntity<UserModel> response = null;
		try {
			UserModel user = userService.findById(id);
			response = new ResponseEntity<UserModel>(user, HttpStatus.OK);
			return response;
		}catch(CICException e) {
			LOGGER.error(e.getMessage());
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.set("Custom_Response_Value",e.getMessage());
			response = new ResponseEntity<UserModel>(headers,HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return response;
	}
	
	@PostMapping
	public ResponseEntity<UserModel> createNew(@RequestBody UserModel user){
		user = userService.create(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UserModel> update(@RequestBody UserModel user) {
		ResponseEntity<UserModel> response = null;
		try {
			user = userService.update(user);
			response = new ResponseEntity<>(user,HttpStatus.ACCEPTED);
			return response;
		}catch(CICException e) {
			LOGGER.error(e.getMessage());
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.set("ERROR_MESSAGE",e.getMessage());
			response = new ResponseEntity<UserModel>(headers,HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
		ResponseEntity<Boolean> response = null;
		try {
			UserModel user = userService.findById(id);
			userService.delete(user);
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
