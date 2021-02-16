package mx.ipn.cic.controlescolar.demo.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mx.ipn.cic.controlescolar.demo.models.RolModel;
import mx.ipn.cic.controlescolar.demo.services.IRolService;


@Controller
@RequestMapping(path ="/rol", method = RequestMethod.GET)
public class RolCrontroller {
	
	private static final Log LOGGER = LogFactory.getLog(UserController.class);
	
	@Autowired
	@Qualifier("Rol")
	private IRolService rolService;
	

	@GetMapping(path ="/all")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("rol/allRoles");
		
		List<RolModel> roles = rolService.findAll();
		
		LOGGER.info(String.format("Se encontraron %d resultados",roles.size()));
		mav.addObject("rolList", roles);

		return mav;	
	}
	
	
	//Forma de insertar un formulario con thymeleaf
	@GetMapping(path = "/newRolFormThymeleaf")
	public ModelAndView getNewRolFormThymeleaf() {
		ModelAndView mav = new ModelAndView("rol/new_rol_form_thymelaf");
		//Agrega al contexto para que lo pueda recibir el siguiente metodo (createNewUser5)
		mav.addObject("rol", new RolModel());
		
		return mav;
	}
	
	//los parametros name de ModelAttribute debe ser tambien el nombre de la variable que se agrega
	//en el metodo addObject
	@PostMapping(path="/newRol")
	public String createNewRol(@ModelAttribute(name = "rol") RolModel rol) {
		rol = rolService.create(rol);
		LOGGER.info("Se guardo exitosamente el usuario: "+ rol.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/rol/all";
	}
	
	@GetMapping(path = "/editForm/{id}")
	//se mapea lo que este entre llaves lo que recibe el metodo, y con PathVariable se amarra nuevamente
	public ModelAndView updateRol(@PathVariable("id") int id) {
		RolModel rol = this.rolService.findById(id);
		
		ModelAndView mav = new ModelAndView("rol/edit_rol_form_thymelaf");
		mav.addObject("rol", rol);
		return mav;
	}
	
	@PostMapping(path="/updateRol")
	public String updateUser(@ModelAttribute(name = "rol") RolModel rol) {
		rolService.update(rol);
		LOGGER.info("Se actualizo exitosamente el rol: "+ rol.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/rol/all";
	}
	
	@GetMapping(path = "/delete/{id}")
	//se mapea lo que este entre llaves lo que recibe el metodo, y con PathVariable se amarra nuevamente
	public String deleteRol(@PathVariable("id") int id) {
		RolModel rol = this.rolService.findById(id);
		this.rolService.delete(rol);
		//ModelAndView mav = new ModelAndView("user/delete_user_form_thymelaf");
		//mav.addObject("user", user);
//		return mav;
		return "redirect:/rol/all";
	}
	
	

}
