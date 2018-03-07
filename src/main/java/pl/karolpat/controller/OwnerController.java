package pl.karolpat.controller;

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
public class OwnerController {

	
	private UserService userService;
	
	public OwnerController(UserService userService) {
		this.userService=userService;
	}
	
	@RequestMapping("/")
	ResponseEntity getUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@RequestMapping("users/{id}")
	ResponseEntity getUserById(@PathVariable("id") long id) {
		return ResponseEntity.ok(userService.getOneById(id));
	}
	
	@PutMapping("users/{id}/vip")
	ResponseEntity setUserAsVip(@RequestBody User tmp, @PathVariable("id") long id) {
		return ResponseEntity.ok(userService.setUserAsVip(tmp, id));
	}
	
	@GetMapping("users/vip")
	ResponseEntity showAllVips() {
		return ResponseEntity.ok(userService.findAllWhereVip(true));
	}
}
