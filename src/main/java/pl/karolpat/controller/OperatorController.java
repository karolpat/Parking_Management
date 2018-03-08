package pl.karolpat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.entity.User;
import pl.karolpat.service.UserService;

@RestController
@RequestMapping("users")
public class OperatorController {

	private UserService userService;

	public OperatorController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("")
	ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@RequestMapping("/{id}")
	ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		return ResponseEntity.ok(userService.getOneById(id));
	}

	@PutMapping("/{id}/vip")
	ResponseEntity<?> setUserAsVip(@RequestBody User tmp, @PathVariable("id") long id) {
		if(tmp.isStarted()) {
			return ResponseEntity.ok("Cannot change vip status on user who uses praking.");
		}else {
			return ResponseEntity.ok(userService.setUserAsVip(tmp, id));
		}
		
	}

	@GetMapping("/vip")
	ResponseEntity<List<User>> showAllVips() {
		return ResponseEntity.ok(userService.findAllWhereVip(true));
	}

//	@GetMapping("")
}
