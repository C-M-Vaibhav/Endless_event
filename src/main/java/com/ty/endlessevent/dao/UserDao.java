package com.ty.endlessevent.dao;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.endlessevent.EndlessEventApplication;
import com.ty.endlessevent.dto.User;
import com.ty.endlessevent.exception.NoIdFoundException;
import com.ty.endlessevent.respositary.UserRepositary;
import com.ty.endlessevent.service.UserService;

@Repository
public class UserDao {
	
	@Autowired
	UserRepositary repositary;
	
	@Autowired
	EndlessEventApplication application;
	
	
	public User saveUser(User user) {
		User u = repositary.save(user); 
		application.getMail(u.getEmail(), "welcome Admin "+" your Id is : "+u.getId());
		try {
			application.triggerMail();
		} catch (MessagingException e) {
			new NoIdFoundException("No email found to send");
		}
		return u;
	}
	
	public User getUserById(String id) {
		User user = repositary.checkId(id);
		if(user!=null) {
			return user;
		}
		return null;
	}
	public List<User> getAllUser() {

		return repositary.findAll();
	}
	public User updateUser(String id,User user) {
		User e = 
				getUserById(id);
		if(e!=null) {
			return repositary.save(user);
		}
		return null;
	}
	
	public boolean deleteUser(String id) {
		User  user = getUserById(id);
		if(user!=null) {
			repositary.delete(user);
			return true;
		}
		return false;
	}	
	
	public User validate(String email,String password) {
		User user = repositary.validateUser(email, password);
		if(user!=null) {
			return user;
		}
		return null;
	}
}
