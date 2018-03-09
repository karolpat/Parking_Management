package pl.karolpat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.entity.User;
import pl.karolpat.service.ParkingMeterService;
import pl.karolpat.service.UserService;

@RestController
@RequestMapping("users/")
public class UserController {

	private UserService userService;
	private ParkingMeterService parkingMeterService;

	public UserController(UserService userService, ParkingMeterService parkingMeterService) {
		this.userService = userService;
		this.parkingMeterService = parkingMeterService;
	}

	public User getUserById(long id) {
		return userService.getOneById(id);
	}

	@PostMapping("startParking/{id}")
	ResponseEntity<?> startParking(@RequestBody String vehicleNumber, @PathVariable("id") long id) {

		User user = getUserById(id);

		if (user.isStarted() == true) {
			return ResponseEntity.ok("Finish your previous parking first");
		} else {
			return ResponseEntity.ok(userService.startParking(vehicleNumber, user));
		}

	}

	@GetMapping("checkParking/{id}")
	ResponseEntity<?> checkParking(@PathVariable("id") long id) {

		User user = getUserById(id);

		if (user.isStarted() == false) {
			return ResponseEntity.ok("Nothing to show. Start parking first.");
		} else {
			return ResponseEntity.ok(parkingMeterService.findUserParkingMeter(id));
		}

	}

	@GetMapping("checkCost/{id}")
	ResponseEntity<?> checkCost(@PathVariable("id") long id) {

		User user = getUserById(id);

		if (user.isStarted() == false) {
			return ResponseEntity.ok("Nothing to show. There is no current parking");
		} else {

			return ResponseEntity.ok(parkingMeterService.checkCost(user));
		}
	}

	@GetMapping("endParking/{id}")
	ResponseEntity<?> endUserParking(@PathVariable("id") long id) {

		User user = getUserById(id);
		if (user.isStarted() == false) {
			return ResponseEntity.ok("Nothing to be finished.");
		} else {
			return ResponseEntity.ok(userService.finishParking(user));
		}

	}

}
