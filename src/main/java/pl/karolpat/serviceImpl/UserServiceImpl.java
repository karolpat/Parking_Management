package pl.karolpat.serviceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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
	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	public User setUserAsVip(User tmp, long id) {
		User user = userRepo.findOne(id);
		user.setVip(tmp.isVip());
		userRepo.save(user);
		return user;
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

		ParkingMeter parkingMeter = new ParkingMeter(new Timestamp(System.currentTimeMillis()), user, vehicle);
		parkingMeterService.save(parkingMeter);
		return user;
	}

	@Override
	public User checkParking(User user) {

		return null;
	}

	@Override
	public Map<String, Double> finishParking(User user) {

		parkingMeterService.saveSetEnd(user);
		user.setStarted(false);
		userRepo.save(user);

		Map<String, Double> map = parkingMeterService.checkCost(user);
		dailyIncomeService.addIncome(map);

		return map;
	}

}
