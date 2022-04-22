package com.ty.endlessevent.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.endlessevent.dao.EventDao;
import com.ty.endlessevent.dao.PersonDao;
import com.ty.endlessevent.dao.ResponseStructure;
import com.ty.endlessevent.dao.VenueDao;
import com.ty.endlessevent.dto.Event;
import com.ty.endlessevent.dto.Person;
import com.ty.endlessevent.dto.Venue;

@Service
public class PersonService {
	@Autowired
	private PersonDao dao;

	@Autowired
	EventDao dao2;

	@Autowired
	private VenueDao venueDao;

	public ResponseEntity<ResponseStructure<Person>> savePerson(int e_id, Person person, int noofPersons,
			boolean decoration, String veg, String non_veg, String area, String city, String country, int pincode,
			int date, int month, int year, int time, String state) {
		Event event = dao2.getEvent(e_id);
		double cost = event.getCost();
		if (decoration == true) {
			cost += 500;
		}
		if (noofPersons != 0) {
			if (veg.equalsIgnoreCase("yes") && non_veg.equalsIgnoreCase("yes")) {
				cost += noofPersons * 125;
			} else if (veg.equalsIgnoreCase("yes")) {
				cost += noofPersons * 50;
			} else if (non_veg.equalsIgnoreCase("yes")) {
				cost += noofPersons * 100;
			}
		}
		event.setCost(cost);
		Venue venue = new Venue();
		venue.setArea(area);
		venue.setCity(city);
		venue.setDate(LocalDate.of(year, month, date));
		venue.setEvent(event);
		venue.setTime(LocalTime.of(time, 00));
		venue.setPincode(pincode);
		venue.setState(state);
		venue.setCountry(country);
		Venue v = venueDao.saveVenue(e_id, venue);
		List<Venue> venues = new ArrayList<Venue>();
		venues.add(v);
		event.setVenues(venues);
		dao2.bookEvent(e_id, person);
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		person.setEvents(events);
		Person person2 = dao.savePerson(event, person);
		if (person2 != null) {
			ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(person2);
			ResponseEntity<ResponseStructure<Person>> entity = new ResponseEntity<ResponseStructure<Person>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Find Somthing is wrong");
			responseStructure.setData(person2);
			ResponseEntity<ResponseStructure<Person>> entity = new ResponseEntity<ResponseStructure<Person>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;

		}
	}

	public ResponseEntity<ResponseStructure<Person>> getPersonById(String id) {

		Person person = dao.getPersonById(id);
		ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
		if (person == null) {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("success");
			responseStructure.setData(person);
			ResponseEntity<ResponseStructure<Person>> entity = new ResponseEntity<ResponseStructure<Person>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;

		} else {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(person);
			ResponseEntity<ResponseStructure<Person>> entity = new ResponseEntity<ResponseStructure<Person>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
	}

	public ResponseEntity<ResponseStructure<Boolean>> deletePersonById(String id) {
		ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
		boolean status = dao.deletePerson(id);
		if (status) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(status);
			ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not deleted");
		responseStructure.setData(status);
		ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(
				responseStructure, HttpStatus.NOT_FOUND);
		return entity;
	}

	public ResponseEntity<ResponseStructure<Person>> updatePerson(Person person, String id) {
		Person person2 = dao.updatePerson(person, id);
		ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
		if (person2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(person2);
			ResponseEntity<ResponseStructure<Person>> entity = new ResponseEntity<ResponseStructure<Person>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("success");
		responseStructure.setData(person2);
		ResponseEntity<ResponseStructure<Person>> entity = new ResponseEntity<ResponseStructure<Person>>(
				responseStructure, HttpStatus.OK);
		return entity;
	}

	public ResponseEntity<ResponseStructure<List<Person>>> getallPerson() {
		List<Person> list = dao.getAllPerson();
		ResponseStructure<List<Person>> responseStructure = new ResponseStructure<List<Person>>();
		if (list != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(list);
			ResponseEntity<ResponseStructure<List<Person>>> entity = new ResponseEntity<ResponseStructure<List<Person>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(null);
		ResponseEntity<ResponseStructure<List<Person>>> entity = new ResponseEntity<ResponseStructure<List<Person>>>(
				responseStructure, HttpStatus.NOT_FOUND);
		return entity;
	}
	
	public ResponseEntity<ResponseStructure<List<Event>>> getallEvents(String id) {
		List<Event> list = dao.findEventsByPersonId(id);
		ResponseStructure<List<Event>> responseStructure = new ResponseStructure<List<Event>>();
		if (list != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(list);
			ResponseEntity<ResponseStructure<List<Event>>> entity = new ResponseEntity<ResponseStructure<List<Event>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(null);
		ResponseEntity<ResponseStructure<List<Event>>> entity = new ResponseEntity<ResponseStructure<List<Event>>>(
				responseStructure, HttpStatus.NOT_FOUND);
		return entity;
	}

}
