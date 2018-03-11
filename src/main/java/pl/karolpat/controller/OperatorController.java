package pl.karolpat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.entity.User;
import pl.karolpat.entity.Vehicle;
import pl.karolpat.service.UserService;
import pl.karolpat.service.VehicleService;

@RestController
@RequestMapping("users")
public class OperatorController {

	private UserService userService;
	private VehicleService vehicleService;
	
	static private String VIP_STATUS_CHANGE = "Cannot change vip status on user who uses praking.";

	public OperatorController(UserService userService, VehicleService vehicleService) {
		this.userService = userService;
		this.vehicleService = vehicleService;
	}

	/**
	 * At address localhost:5555/users list of all users is shown.
	 * 
	 * @return List of all User instances.
	 */
	@RequestMapping("")
	ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	/**
	 * Shows details of User that is searched by given in URL id.
	 * 
	 * @param id
	 *            given in URL id of User to be shown.
	 * @return User instance of given id or nothing if there is no user of such id.
	 */
	@RequestMapping("/{id}")
	ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		return ResponseEntity.ok(userService.getOneById(id));
	}

	/**
	 * Sets User status as VIP so that this User will pay less for the parking.
	 * 
	 * @param tmp
	 *            User instance that is the input.
	 * @param id
	 *            id of User whose vip status is going to change
	 * @return User whose vip status has been changed or a String indicating that
	 *         operation failed.
	 */
	@PutMapping("/{id}/vip")
	ResponseEntity<?> setUserAsVip(@RequestBody User tmp, @PathVariable("id") long id) {
		if (tmp.isStarted()) {
			return ResponseEntity.ok(VIP_STATUS_CHANGE);
		} else {
			return ResponseEntity.ok(userService.setUserAsVip(tmp, id));
		}

	}

	/**
	 * Shows list of all User having vip status.
	 * 
	 * @return List of User instances that are vips.
	 */
	@GetMapping("/vip")
	ResponseEntity<List<User>> showAllVips() {
		return ResponseEntity.ok(userService.findAllWhereVip(true));
	}

	/**
	 * Shows whether a vehicle of given number has started ParkingMeter.
	 * 
	 * @param number
	 *            number of vehicle to be checked.
	 * @return True or false whether or not the vehicle has started the
	 *         ParkingMeter.
	 */
	@PostMapping("/vehicle")
	ResponseEntity<?> test(@RequestBody String number) {
		List<Vehicle> vehicles = vehicleService.getVehiclesByNumber(number);
		return ResponseEntity.ok(vehicleService.isStarted(vehicles));
	}
}
