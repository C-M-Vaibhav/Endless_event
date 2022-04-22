package com.ty.endlessevent.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "Event should be filled")
	private String name;
	private String decription;
	@NotNull(message = "cost should not be null")
	@NumberFormat
	private double cost;
	private String img;
	@CreationTimestamp
	private LocalDateTime date;
	@NumberFormat
	private int noOfPerson;
	private boolean decoration;
	private String veg;
	private String nonVeg;
	
	@ManyToMany
	@JoinTable(inverseJoinColumns = @JoinColumn(name = "person_id"),joinColumns = @JoinColumn(name = "event_id"))
	private List<Person> persons;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private User user;
	
	@OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
	private List<Venue> venues;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getNoOfPerson() {
		return noOfPerson;
	}
	public void setNoOfPerson(int noOfPerson) {
		this.noOfPerson = noOfPerson;
	}
	public boolean isDecoration() {
		return decoration;
	}
	public void setDecoration(boolean decoration) {
		this.decoration = decoration;
	}
	public String getVeg() {
		return veg;
	}
	public void setVeg(String veg) {
		this.veg = veg;
	}
	public String getNonVeg() {
		return nonVeg;
	}
	public void setNonVeg(String nonVeg) {
		this.nonVeg = nonVeg;
	}
	public List<Venue> getVenues() {
		return venues;
	}
	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}
	@JsonIgnore
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
	
	
}