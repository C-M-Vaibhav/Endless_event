package com.ty.endlessevent;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import com.ty.endlessevent.service.EmailService;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EndlessEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndlessEventApplication.class, args);
	}
	@Bean
	public Docket getDocket() { 
		Contact contact = new Contact("EndlessEvent", "www.endlessevent.com", "endlessevent@gmail.com");

		List<VendorExtension> extensions = new ArrayList<VendorExtension>();

		ApiInfo apiInfo = new ApiInfo("Endless Event Api Service", "Endless Event Management Center.", "SNAPSHOT 1.0",
				"www.endlessevent.com", contact, "9175485485", "Licence123", extensions);

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.ty"))
				.build().apiInfo(apiInfo).useDefaultResponseMessages(false);
	}
	
	@Autowired
	EmailService service;
	
	static String email="cmvaibhav8904@gmail.com";
	static String msg="hi admin";
	
	public static void getMail(String vemail,String message) {
		 email=vemail;
		 msg=message;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		
			
		service.sendSimpleEmail("cmvaibhav4@gmail.com",email, msg, "welcome to endless event !!!!!!!");
	}

}
