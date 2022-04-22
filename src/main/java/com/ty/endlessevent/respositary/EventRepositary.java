package com.ty.endlessevent.respositary;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.endlessevent.dto.Event;
import com.ty.endlessevent.dto.Person;
import com.ty.endlessevent.dto.User;

public interface EventRepositary extends JpaRepository<Event, Integer> {
	
	/*
	 * @Query("select e from Event e where e.persons.id=?1") public List<Event>
	 * getEventsByPersonId(String id);
	 */
	
}
