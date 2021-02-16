package mx.ipn.cic.controlescolar.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mx.ipn.cic.controlescolar.demo.models.RolModel;
import mx.ipn.cic.controlescolar.demo.models.UserModel;
import mx.ipn.cic.controlescolar.demo.services.IRolService;
import mx.ipn.cic.controlescolar.demo.services.IUserService;


@Controller
@RequestMapping(path ="/user", method = RequestMethod.GET)
public class UserController {
	
	private static final Log LOGGER = LogFactory.getLog(UserController.class);
	
	@Autowired
	@Qualifier("REAL")
	private IUserService userService;
	
	@Autowired
	@Qualifier("Rol")
	private IRolService rolService;
	
	//@PostMapping(path ="/all")
	//@DeleteMapping(path ="/all")
	//@RequestMapping(path="/user/all")
	@GetMapping(path ="/all")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("user/allUsers");
//	public String findAll() {
		
		List<UserModel> users = userService.findAll();
		
		LOGGER.info(String.format("Se encontraron %d resultados",users.size()));
		mav.addObject("userList", users);
		// Se regresa el html ((.../templates) a mostrar al usuario esto es porque es un controlador 
		// y el return debe ser el nombre del recurso sin el .html
//		return "user/allUsers";
		return mav;	
	}
	
	@GetMapping(path = "/newUserForm")
	public ModelAndView getNewUserForm() {
		ModelAndView mav = new ModelAndView("user/new_user_form");
		
		return mav;
	}
	
	@PostMapping(path="/newUser")
	public String createNewUser(HttpServletRequest request) {
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String surname = request.getParameter("surname");
		String strAge = request.getParameter("age");
		int age = Integer.parseInt(strAge);
		UserModel user = new UserModel(name, lastname, surname, age);
		user = userService.create(user);
		LOGGER.info("Se guardó exitosamente el usuario: "+ user.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/user/all";
	}
	
	@PostMapping(path="/newUser2")
	public String createNewUser2(@RequestParam(name ="name") String name, 
			@RequestParam(name ="lastname", required = true) String lastname,
			@RequestParam(name ="surname") String surname,
			@RequestParam(name ="age", required =false, defaultValue = "18") int age) {

		UserModel user = new UserModel(name, lastname, surname, age);
		user = userService.create(user);
		LOGGER.info("Se guardó exitosamente el usuario: "+ user.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/user/all";
	}

	//No realiza validaciones
	@PostMapping(path="/newUser3")
	public String createNewUser3(UserModel user) {
		user = userService.create(user);
		LOGGER.info("Se guardó exitosamente el usuario: "+ user.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/user/all";
	}
	
	//aqui valida la información con las anotaciones del UserModel
	@PostMapping(path="/newUser4")
	public String createNewUser4(@Validated UserModel user) {
		user = userService.create(user);
		LOGGER.info("Se guardó exitosamente el usuario: "+ user.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/user/all";
	}
	
	//Esta sirve como api rest y puedes usar postman enviando un json con los parametros
	@PostMapping(path="/newUserx")
	public String createNewUserx(@Validated @RequestBody UserModel user) {
		user = userService.create(user);
		LOGGER.info("Se guardó exitosamente el usuario: "+ user.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/user/all";
	}
	
	//Forma de insertar un formulario con thymeleaf
	@GetMapping(path = "/newUserFormThymeleaf")
	public ModelAndView getNewUserFormThymeleaf() {
		ModelAndView mav = new ModelAndView("user/new_user_form_thymelaf");
		//Agrega al contexto para que lo pueda recibir el siguiente metodo (createNewUser5)
		mav.addObject("user", new UserModel());
		List<RolModel> roles = rolService.findAll();
		mav.addObject("rolList", roles);
		
		return mav;
	}
	
	//los parametros name de ModelAttribute debe ser tambien el nombre de la variable que se agrega
	//en el metodo addObject
	@PostMapping(path="/newUser5")
	public String createNewUser5(@ModelAttribute(name = "user") UserModel user) {
		user = userService.create(user);
		LOGGER.info("Se guardó exitosamente el usuario: "+ user.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/user/all";
	}
	
	@GetMapping(path = "/editForm/{id}")
	//se mapea lo que este entre llaves lo que recibe el metodo, y con PathVariable se amarra nuevamente
	public ModelAndView updateUSer(@PathVariable("id") int id) {
		UserModel user = this.userService.findById(id);
		
		ModelAndView mav = new ModelAndView("user/edit_user_form_thymelaf");
		mav.addObject("user", user);
		List<RolModel> roles = rolService.findAll();
		mav.addObject("rolList", roles);
		
		return mav;
	}
	
	@PostMapping(path="/updateUser")
	public String updateUser(@ModelAttribute(name = "user") UserModel user) {
		
//		RolModel rolnuevo = rolService.findById(rol.getId());
//		user.setRol(rol);
		userService.update(user);
		LOGGER.info("Se actualizó exitosamente el usuario: "+ user.getId());
		//redirect nos hace un reedireccionamiento por medio de html y se tiene que 
		// poner el path completo
		return "redirect:/user/all";
	}
	
	@GetMapping(path = "/delete/{id}")
	//se mapea lo que este entre llaves lo que recibe el metodo, y con PathVariable se amarra nuevamente
	public String deleteUSer(@PathVariable("id") int id) {
		UserModel user = this.userService.findById(id);
		this.userService.delete(user);
		//ModelAndView mav = new ModelAndView("user/delete_user_form_thymelaf");
		//mav.addObject("user", user);
//		return mav;
		return "redirect:/user/all";
	}
	
	@GetMapping(path = "/find/{name}")
	//se mapea lo que este entre llaves lo que recibe el metodo, y con PathVariable se amarra nuevamente
	public String findByName(@PathVariable("name") String name) {
		UserModel user = this.userService.findByName(name);
		LOGGER.info(user);
//		UserModel user2 = this.userService.findByNameContains(name);
		return "redirect:/user/all";
	}
	
	@GetMapping(path="/getCombo")
	public ModelAndView getCombo() {
		ModelAndView mav = new ModelAndView("user/comboUsers");
		List<UserModel> users = userService.findAll();
		mav.addObject("userList", users);
		return mav;
	}

}
