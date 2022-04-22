package com.ty.endlessevent.respositary;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.endlessevent.dto.User;

public interface UserRepositary extends JpaRepository<User, Integer> {

	
	@Query("select u from User u where u.email=?1 AND u.password=?2")
	public User validateUser(String email, String password);
	
	@Query("select u from User u where u.id=?1")
	public User checkId(String id);

}
