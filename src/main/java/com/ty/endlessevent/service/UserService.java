package com.ty.endlessevent.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.endlessevent.dao.ResponseStructure;
import com.ty.endlessevent.dao.UserDao;
import com.ty.endlessevent.dto.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		User user2 = userDao.saveUser(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (user2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(user2);
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("not found");
		responseStructure.setData(user2);
		ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		return entity;
	}

	public ResponseEntity<ResponseStructure<User>> getUserById(String id) {
		User user2 = userDao.getUserById(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (user2 == null) {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("not find");
			responseStructure.setData(user2);
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
			return entity;
		} else {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(user2);
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
			return entity;
		}
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteUserById(String id) {
		ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
		boolean status = userDao.deleteUser(id);
		if (status) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(status);
			ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(responseStructure,HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("not found");
		responseStructure.setData(status);
		ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(responseStructure,HttpStatus.NOT_FOUND);
		return entity;
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user, String id) {
		User user2 = userDao.updateUser(id, user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (user2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(user2);
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("not found");
		responseStructure.setData(user2);
		ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		return entity;
	}

	public ResponseEntity<ResponseStructure<User>> getAllUser() {
		List<User> users = userDao.getAllUser();
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
		if (users != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(users);
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("not found");
		responseStructure.setData(users);
		ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(HttpStatus.NOT_FOUND);
		return entity;
	}
}
