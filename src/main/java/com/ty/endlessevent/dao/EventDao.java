package com.ty.endlessevent.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.endlessevent.EndlessEventApplication;
import com.ty.endlessevent.dto.Event;
import com.ty.endlessevent.dto.Person;
import com.ty.endlessevent.dto.User;
import com.ty.endlessevent.exception.NoIdFoundException;
import com.ty.endlessevent.respositary.EventRepositary;

@Repository
public class EventDao {
	
	@Autowired
	UserDao dao;
	
	@Autowired
	EventRepositary repositary;
	
	@Autowired
	EndlessEventApplication application;
	
	public Event saveEvent(String email,String password,Event event) {
		User user = dao.validate(email, password);
		if(user!=null) {
			event.setUser(user);
			String msg="Hi admin you added event called "+event.getName()+" the Event Id Is "+event.getId();
			application.getMail(user.getEmail(),msg);
			try {
				application.triggerMail();
			} catch (MessagingException e) {
				new NoIdFoundException("No email found to send");
			}
			return repositary.save(event);
		}
		return null;
	}
	
	public Event getEvent(int id) {
		Optional<Event> optional = repositary.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}
	
	public Event updateEvent(int id,Event event) {
		Event e = getEvent(id);
		if(e!=null) {
			return repositary.save(event);
		}
		return null;
	}
	
	public boolean deleteEvent(int id) {
		Event event = getEvent(id);
		if(event!=null) {
			repositary.delete(event);
			return true;
		}
		return false;
	}
	
	public List<Event> getAllEvents(){
		List<Event> events = repositary.findAll();
		if(events.isEmpty()) {
			return null;
		}
		return events;
	}
	
/*	public List<Event> getAllEventsById(String p_id){
		List<Event> events = repositary.getEventsByPersonId(p_id);
		if(events.isEmpty()) {
			return null;
		}
		return events;
	}*/
	
	public Event bookEvent(int id,Person person) {
		Event event = getEvent(id);
		if(event!=null) {
			List<Person> persons = new ArrayList<Person>();
			persons.add(person);
			event.setPersons(persons);
			return event;
		}
		return null;
	}
	

}
