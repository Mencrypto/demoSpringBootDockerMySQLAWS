package mx.ipn.cic.controlescolar.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ControlescolarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ControlescolarApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ControlescolarApplication.class);
	}

}
