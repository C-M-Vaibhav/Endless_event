package com.ty.endlessevent.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ty.endlessevent.dao.ResponseStructure;
import com.ty.endlessevent.dto.Event;
import com.ty.endlessevent.dto.Person;
import com.ty.endlessevent.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService service ;
	
	@ApiOperation(value = "Save Person Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Person Saved"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper person data") })
	
	@PostMapping("event/person")
	public List<ResponseEntity<ResponseStructure<Person>>> savePerson(@RequestParam List<Integer> e,@RequestBody @Valid Person person,@RequestParam int noofPersons,@RequestParam boolean decoration,@RequestParam String veg,@RequestParam String non_veg,@RequestParam String area,@RequestParam String city,@RequestParam String country,@RequestParam int pincode,@RequestParam int date,@RequestParam int month,@RequestParam int year,@RequestParam int time,@RequestParam String state){
		List<ResponseEntity<ResponseStructure<Person>>> entities = new ArrayList<ResponseEntity<ResponseStructure<Person>>>();	
		for(Integer ev:e) {
				 entities.add(service.savePerson(ev, person, noofPersons, decoration, veg, non_veg, area, city, country, pincode, date, month, year, time, state));
	 }
			return entities;
	}
	@ApiOperation(value = "Display Person Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Person Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper person data") })
	@GetMapping("person")
	public ResponseEntity<ResponseStructure<Person>> getPersonById(@RequestParam String id){
		return service.getPersonById(id);
	}
	
	@ApiOperation(value = "Delete Person Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Person delete"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper person data") })
	@DeleteMapping("person")
	public ResponseEntity<ResponseStructure<Boolean>> deleteByIdPerson(@RequestParam String id){
		return service.deletePersonById(id);
	}
	@PutMapping("person")
	public ResponseEntity<ResponseStructure<Person>> updatePerson(@RequestBody Person person , @RequestParam String id){ 
		return service.updatePerson(person , id);
	}
	
	@ApiOperation(value = "Get All Person Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "All Person Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper person data") })
	@GetMapping("allperson")
	public ResponseEntity<ResponseStructure<List<Person>>> gatAllPerson(){
		return service.getallPerson() ;
	}
	
	@ApiOperation(value = "Get All Person Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "All Person Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper person data") })
	@GetMapping("allEventsofPerson")
	public ResponseEntity<ResponseStructure<List<Event>>> getEventsall(@RequestParam String id){
		return service.getallEvents(id) ;
	}
	@GetMapping("hii")
	public Person get() {
		return new Person();
	}

}
