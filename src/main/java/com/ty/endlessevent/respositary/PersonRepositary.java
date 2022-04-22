package com.ty.endlessevent.respositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.endlessevent.dto.Person;
import com.ty.endlessevent.dto.User;

public interface PersonRepositary extends JpaRepository<Person, Integer> {
		
	@Query("select p from Person p where p.email=?1 AND p.password=?2")
	public Person validate(String email,String password);
	
	@Query("select p from Person p where p.id=?1")
	public Person checkId(String id);

}
