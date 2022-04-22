package com.ty.endlessevent.dao;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.endlessevent.EndlessEventApplication;
import com.ty.endlessevent.dto.Event;
import com.ty.endlessevent.dto.Person;
import com.ty.endlessevent.exception.NoIdFoundException;
import com.ty.endlessevent.respositary.PersonRepositary;

@Repository
public class PersonDao {
	
	@Autowired
	PersonRepositary repositary;
	
	@Autowired
	EventDao dao;
	
	@Autowired
	EndlessEventApplication application;
	
	public Person savePerson(Event event,Person person) {
		Person p = validate(person.getEmail(), person.getPassword());
		if(event!=null) {
			if(p!=null) {
				Person per = repositary.save(p);
				String msg="welcome new customer";//+" "+p.getName()+" Your ID is "+p.getId()+" "+"Your number"+" "+p.getNo()+" Your booked the event like "+p.getEvents().getName()+" Your TotalCost you have to pay is "+p.getEvents().getCost()+" Your place is "+p.getVenue().getArea()+" "+p.getVenue().getCity()+" "+p.getVenue().getCountry()+" "+p.getVenue().getPincode()+" Your thank for using our app ";
				EndlessEventApplication.getMail(p.getEmail(),msg);
				try {
					application.triggerMail();
				} catch (MessagingException e) {
					new NoIdFoundException("No email found to send");
				}
				return per;
			}
			Person per = repositary.save(person);
			String msg="welcome new customer";//+" "+per.getName()+" Your ID is "+per.getId()+" "+"Your number"+" "+per.getNo()+" Your booked the event like "+per.getEvents()+" Your TotalCost you have to pay is "+per.getEvents().getCost()+" Your place is "+per.getEvents()+" "+per.getVenue().getCity()+" "+per.getVenue().getCountry()//+" "+per.getVenue().getPincode()+" Your thank for using our app ";
			EndlessEventApplication.getMail(person.getEmail(),msg);
			try {
				application.triggerMail();
			} catch (MessagingException e) {
				new NoIdFoundException("No email found to send");
			}
			return per;
		}
		return null;
	}
	
	public Person getPersonById(String id) {
		Person person = repositary.checkId(id);
		if(person!=null) {
			EndlessEventApplication.getMail(person.getEmail(),"going to search you by Id customer");
			try {
				application.triggerMail();
			} catch (MessagingException e) {
				new NoIdFoundException("No email found to send");
			}
			return person;
		}
		return null;
		
	}
	
	public Person updatePerson(Person person , String id) {
		Person e = getPersonById(id);
		if(e!=null) {
			EndlessEventApplication.getMail(person.getEmail(),"your profile is updated costumer thank your for your time ");
			try {
				application.triggerMail();
			} catch (MessagingException r) {
				new NoIdFoundException("No email found to send");
			}
			return repositary.save(person);
		}
		return null;
	}
	public List<Person> getAllPerson(){
		
		List<Person> list = repositary.findAll();
		for(Person p:list) {
			EndlessEventApplication.getMail(p.getEmail(),"Admin is seeing your deatils your getting new offers dont miss it");
			try {
				application.triggerMail();
			} catch (MessagingException e) {
				new NoIdFoundException("No email found to send");
			}
		}
		return list;
		
	}
	
	public boolean deletePerson(String id) {
		Person  person = getPersonById(id);
		if(person!=null) {
			EndlessEventApplication.getMail(person.getEmail(),"your are deleted by our app");
			try {
				application.triggerMail();
			} catch (MessagingException e) {
				new NoIdFoundException("No email found to send");
			}
			repositary.delete(person);
			return true;
		}
		return false;
	}
	
	public Person validate(String email,String password) {
		Person person = repositary.validate(email, password);
		if(person!=null) {
			EndlessEventApplication.getMail(person.getEmail(),"your email is logined in our app your email is validated");
			try {
				application.triggerMail();
			} catch (MessagingException e) {
				new NoIdFoundException("No email found to send");
			}
			return person;
		}
		return null;
	
	}
	public List<Event> findEventsByPersonId(String id){
		Person  person = getPersonById(id);
		List<Event> events = person.getEvents();
		return events;
		
		
	}
}
