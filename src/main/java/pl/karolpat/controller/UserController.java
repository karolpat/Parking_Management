package pl.karolpat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.entity.User;
import pl.karolpat.service.UserService;

@RestController
public class UserController {

	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("users/startParking/{id}")
	ResponseEntity startParking(@RequestBody String vehicleNumber,@PathVariable("id") long id) {

		User user = userService.getOneById(id);
		if(user.isStarted()==true) {
			return ResponseEntity.ok("Finish your previous parking first");
		}else {
			return ResponseEntity.ok(userService.startParking(vehicleNumber, user));	
		}
		
	}
	
}
