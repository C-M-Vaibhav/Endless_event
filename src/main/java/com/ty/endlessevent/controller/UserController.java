package com.ty.endlessevent.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ty.endlessevent.dao.ResponseStructure;
import com.ty.endlessevent.dto.User;
import com.ty.endlessevent.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@ApiOperation(value = "Save User Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "User Saved"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper User data") })

	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody @Valid User user) {
		return service.saveUser(user);
	}

	@ApiOperation(value = "Display User Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "User Data Displayed"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper User data") })

	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<User>> UsergetByID(@RequestParam String id) {
		return service.getUserById(id);
	}

	@ApiOperation(value = "Delete User Details", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "User Delete"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper User data") })

	@DeleteMapping("/user")
	public ResponseEntity<ResponseStructure<Boolean>> deleteUserById(@RequestParam String id) {
		return service.deleteUserById(id);
	}

	

	@ApiOperation(value = " User Details Updated", produces = "application/json", consumes = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "User Updated"),
			@ApiResponse(code = 405, message = "Bad Request,Not propper User data") })

	@PutMapping("/user")
	public ResponseEntity<ResponseStructure<User>> updateUserById(@RequestBody User user, @RequestParam String id) {
		return service.updateUser(user, id);

	}

	@GetMapping("hiii")
	public User get() {
		return new User();
	}
}
