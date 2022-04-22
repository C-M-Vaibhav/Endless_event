package com.ty.endlessevent.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ty.endlessevent.dao.ResponseStructure;
import com.ty.endlessevent.dto.Event;
import com.ty.endlessevent.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@ApiOperation(value = "Save Event Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Event Saved"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper event data") })
	@PostMapping("user/event")
	public ResponseEntity<ResponseStructure<Event>> saveEvent(@RequestBody @Valid Event event, @RequestParam String email,@RequestParam String password){
		return eventService.saveEvent(email,password,event);
	}
	
	@ApiOperation(value = "Display Event Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Event displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper event data") })
	@GetMapping("event")
	public ResponseEntity<ResponseStructure<Event>> getEventById(@RequestParam int id){
		return eventService.getEventById(id);
	}
	
	@ApiOperation(value = "Display all Event Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "All Event Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper event data") })
	@GetMapping("allevent")
	public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent(){
		return eventService.getAllEvent();
	}
	
	@ApiOperation(value = "Delete Event Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Event deleted"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper event data") })
	@DeleteMapping("event")
	public ResponseEntity<ResponseStructure<Boolean>> deleteEventById(@RequestParam int id){
		return eventService.deleteEventById(id);
	}
	
	@ApiOperation(value = "update Event Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Event updated"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper event data") })
	@PutMapping("event")
	public ResponseEntity<ResponseStructure<Event>> updateEventById(@RequestParam int id, @RequestBody Event event){
		return eventService.updateEventById(event, id);
	}
	
	@GetMapping("hiiii")
	public Event get() {
		return new Event();
	}
}



