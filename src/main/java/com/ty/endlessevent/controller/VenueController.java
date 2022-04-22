package com.ty.endlessevent.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.ty.endlessevent.dto.Venue;
import com.ty.endlessevent.service.VenueService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class VenueController {
	@Autowired
	private VenueService venueService;

	@ApiOperation(value = "Save Venue Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Venue Saved"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper Venue data") })

	@PostMapping("venue")
	public ResponseEntity<ResponseStructure<Venue>> saveVenue(@RequestParam int e_id, @RequestBody Venue venue) {
		return venueService.saveVenue(e_id, venue);
	}

	@ApiOperation(value = "Display Venue Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Venue Data Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper Venue data") })

	@GetMapping("date")
	public ResponseEntity<ResponseStructure<Boolean>> checkDateAndTime(@RequestParam("date") LocalDate date,
			@RequestParam("date") LocalTime time) {
		return venueService.checkDateAndTime1(date, time);
	}

	@ApiOperation(value = "Display Venue Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Venue Data Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper Venue data") })

	@GetMapping("venue")
	public ResponseEntity<ResponseStructure<Venue>> getVenueById(@RequestParam int v_id) {
		return venueService.getVenueById(v_id);
	}

	@ApiOperation(value = "Display Venue Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Venue Data Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper Venue data") })

	@PutMapping("venue")
	public ResponseEntity<ResponseStructure<Venue>> updateVenueById(@RequestParam int v_id, @RequestBody Venue venue) {
		return venueService.updateVenueById1(v_id, venue);
	}

	@ApiOperation(value = "Display Venue Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Venue Data Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper Venue data") })

	@DeleteMapping("venue")
	public ResponseEntity<ResponseStructure<Boolean>> deleteVenue(@RequestParam int id) {
		return venueService.deleteVenueById(id);
	}
}
