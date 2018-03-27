package pl.karolpat.serviceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import pl.karolpat.entity.DailyIncome;
import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.entity.User;
import pl.karolpat.entity.Vehicle;
import pl.karolpat.repository.UserRepo;
import pl.karolpat.service.DailyIncomeService;
import pl.karolpat.service.ParkingMeterService;
import pl.karolpat.service.UserService;
import pl.karolpat.service.VehicleService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;
	private VehicleService vehicleService;
	private ParkingMeterService parkingMeterService;
	private DailyIncomeService dailyIncomeService;

	static private String NOT_VALID_USERNAME = "Username not available. Please select another one.";
	static private String VIP_STATUS_CHANGE = "Cannot change vip status on user who uses praking.";

	public UserServiceImpl(UserRepo userRepo, VehicleService vehicleService, ParkingMeterService parkingMeterService,
			DailyIncomeService dailyIncomeService) {
		this.userRepo = userRepo;
		this.vehicleService = vehicleService;
		this.parkingMeterService = parkingMeterService;
		this.dailyIncomeService = dailyIncomeService;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getOneById(long id) {
		return userRepo.findOne(id);
	}

	@Override
	public Object save(String username) {

		User user = userRepo.findOneByUsername(username);
		if (user != null) {

			return NOT_VALID_USERNAME;
		} else {

			user = new User();
			user.setUsername(username);
			return userRepo.save(user);
		}

	}

	@Override
	public Object setUserAsVip(long id) {

		User user = userRepo.findOne(id);

		if (user.isStarted()) {
			return VIP_STATUS_CHANGE;

		} else {

			user.setVip(true);
			userRepo.save(user);
			return user;
		}

	}

	@Override
	public List<User> findAllWhereVip(boolean vip) {
		return userRepo.findAllByVip(true);
	}

	@Override
	public User startParking(String number, User user) {

		user.setStarted(true);
		Vehicle vehicle = vehicleService.createVehicle(number, user);
		userRepo.save(user);

		// When User starts parking meter, creates instance of ParkingMeter with current
		// time as start.
		ParkingMeter parkingMeter = new ParkingMeter(new Timestamp(System.currentTimeMillis()), user, vehicle);
		parkingMeterService.save(parkingMeter);
		return user;
	}

	@Override
	public Map<String, Double> finishParking(User user) {

		parkingMeterService.saveSetEnd(user);
		
		// User's started status is false again, so that User can start new parking meter.
		user.setStarted(false); 
		userRepo.save(user);

		Map<String, Double> map = parkingMeterService.checkCost(user);
		DailyIncome dailyIncome = dailyIncomeService.addIncome(map);

		return map;
	}

	@Override
	public User getOneByUsername(String username) {
		return userRepo.findOneByUsername(username);
	}

}
