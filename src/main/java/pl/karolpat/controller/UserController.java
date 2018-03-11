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

	static private String FINISH_PREVIOUS_PARKING = "Finish your previous parking first.";
	static private String START_PARKING_FIRST = "Nothing to show. Start parking first.";
	static private String NO_CURRENT_PARKING = "Nothing to show. There is no current parking.";
	static private String NOTHING_TO_FINISH = "Nothing to be finished.";

	public UserController(UserService userService, ParkingMeterService parkingMeterService) {
		this.userService = userService;
		this.parkingMeterService = parkingMeterService;
	}

	/**
	 * Method to find User by given id. Used in action in this controller multiple
	 * times.
	 * 
	 * @param id
	 *            id of user to be found.
	 * @return User instance of given id.
	 */
	public User getUserById(long id) {
		return userService.getOneById(id);
	}

	/**
	 * Creates new user with given username. Username as a String is required to be
	 * key in.
	 * 
	 * @param username
	 *            given String that is user's username.
	 * @return String in case input String is already present in the database or
	 *         User instance when creating new user is successful.
	 */
	@PostMapping("/create")
	ResponseEntity<Object> createNewUser(@RequestBody String username) {
		return ResponseEntity.ok(userService.save(username));
	}

	/**
	 * User of given id starts the parking meter. Current time is saved in
	 * parkingMeter instance as a start and end is null until users ends the parking
	 * meter. PostMapping is used as well as RequestedBody. User has to key in the
	 * registration number as a String preferably. I used Postman to do that.
	 * 
	 * @param vehicleNumber
	 *            registration number of the vehicle that user has to key in.
	 * @param id
	 *            id of user who is starting the parking meter.
	 * @return User instance who started the parking meter.
	 */
	@PostMapping("startParking/{id}")
	ResponseEntity<Object> startParking(@RequestBody String vehicleNumber, @PathVariable("id") long id) {

		User user = getUserById(id);

		if (user.isStarted() == true) {
			return ResponseEntity.ok(FINISH_PREVIOUS_PARKING);
		} else {
			return ResponseEntity.ok(userService.startParking(vehicleNumber, user));
		}

	}

	/**
	 * User checks if parking meter has started and if yes, at what time.
	 * 
	 * @param id
	 *            id of user who checks whether the parking meter has stared.
	 * @return String in case user has not started the parking meter, parkingMeter
	 *         instance in case user has started the parking meter.
	 */
	@GetMapping("checkParking/{id}")
	ResponseEntity<Object> checkParking(@PathVariable("id") long id) {

		User user = getUserById(id);

		if (user.isStarted() == false) {
			return ResponseEntity.ok(START_PARKING_FIRST);
		} else {
			return ResponseEntity.ok(parkingMeterService.findUserParkingMeter(id));
		}

	}

	/**
	 * User checks how much would pay if ending parking meter is at the time of
	 * checking.
	 * 
	 * @param id
	 *            id of user who checks the cost.
	 * @return String in case user has not started the parking meter, cost and time
	 *         of stay in case user has started the parking.
	 */
	@GetMapping("checkCost/{id}")
	ResponseEntity<?> checkCost(@PathVariable("id") long id) {

		User user = getUserById(id);

		if (user.isStarted() == false) {
			return ResponseEntity.ok(NO_CURRENT_PARKING);
		} else {

			return ResponseEntity.ok(parkingMeterService.checkCost(user));
		}
	}

	/**
	 * User of given id stops the parking meter. The time of the finish is save to
	 * parkingMeter instance. The cost of stay is counted and presented to user as
	 * well as the time of the stay.
	 * 
	 * @param id
	 *            id of user who stops the parking meter.
	 * @return String in case user has not started the parking so that can not
	 *         finish any. Total cost and time of stay is presented in case user
	 *         stops the parking meter successfully.
	 */
	@PostMapping("endParking/{id}")
	ResponseEntity<?> endUserParking(@PathVariable("id") long id) {

		User user = getUserById(id);
		if (user.isStarted() == false) {
			return ResponseEntity.ok(NOTHING_TO_FINISH);
		} else {
			return ResponseEntity.ok(userService.finishParking(user));
		}

	}

}
