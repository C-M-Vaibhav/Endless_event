package com.ty.endlessevent.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.endlessevent.dao.EventDao;
import com.ty.endlessevent.dao.ResponseStructure;
import com.ty.endlessevent.dto.Event;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;

	public ResponseEntity<ResponseStructure<Event>> saveEvent(String email, String password, Event event) {
		Event event2 = eventDao.saveEvent(email, password, event);
		ResponseStructure<Event> responseStructure = new ResponseStructure<Event>();
		if (event2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(event2);
			ResponseEntity<ResponseStructure<Event>> entity = new ResponseEntity<ResponseStructure<Event>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(event2);
			ResponseEntity<ResponseStructure<Event>> entity = new ResponseEntity<ResponseStructure<Event>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}

	}

	public ResponseEntity<ResponseStructure<Event>> getEventById(int id) {
		Event event = eventDao.getEvent(id);
		ResponseStructure<Event> responseStructure = new ResponseStructure<Event>();
		if (event != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(event);
			ResponseEntity<ResponseStructure<Event>> entity = new ResponseEntity<ResponseStructure<Event>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(event);
			ResponseEntity<ResponseStructure<Event>> entity = new ResponseEntity<ResponseStructure<Event>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
	}

	public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent() {
		List<Event> events = eventDao.getAllEvents();
		ResponseStructure<List<Event>> responseStructure = new ResponseStructure<List<Event>>();
		if (events != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(events);
			ResponseEntity<ResponseStructure<List<Event>>> entity = new ResponseEntity<ResponseStructure<List<Event>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(events);
			ResponseEntity<ResponseStructure<List<Event>>> entity = new ResponseEntity<ResponseStructure<List<Event>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteEventById(int id) {
		boolean status = eventDao.deleteEvent(id);
		ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
		if (status) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(status);
			ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(status);
			ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}

	}

	public ResponseEntity<ResponseStructure<Event>> updateEventById(Event event, int id) {
		Event event2 = eventDao.updateEvent(id, event);
		ResponseStructure<Event> responseStructure = new ResponseStructure<Event>();
		if (event2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(event2);
			ResponseEntity<ResponseStructure<Event>> entity = new ResponseEntity<ResponseStructure<Event>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(event2);
			ResponseEntity<ResponseStructure<Event>> entity = new ResponseEntity<ResponseStructure<Event>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
	}
	
/*	public ResponseEntity<ResponseStructure<List<Event>>> getAllEventsBYId(String p_id) {
		List<Event> events = eventDao.getAllEventsById(p_id);
		ResponseStructure<List<Event>> responseStructure = new ResponseStructure<List<Event>>();
		if (events != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(events);
			ResponseEntity<ResponseStructure<List<Event>>> entity = new ResponseEntity<ResponseStructure<List<Event>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(events);
			ResponseEntity<ResponseStructure<List<Event>>> entity = new ResponseEntity<ResponseStructure<List<Event>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
	}*/


}
