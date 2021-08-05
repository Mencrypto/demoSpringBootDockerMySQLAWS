package mx.ipn.cic.controlescolar.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(path ="/", method = RequestMethod.GET)
public class IndexController {
		
		private static final Log LOGGER = LogFactory.getLog(IndexController.class);
		
		@GetMapping(path ="/")
		public ModelAndView index() {
			ModelAndView mav = new ModelAndView("index/index");
			LOGGER.info("Desplegando index");
			return mav;	
		}

}
