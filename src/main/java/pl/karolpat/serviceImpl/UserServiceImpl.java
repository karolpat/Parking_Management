package pl.karolpat.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.entity.User;
import pl.karolpat.entity.Vehicle;
import pl.karolpat.repository.UserRepo;
import pl.karolpat.service.ParkingMeterService;
import pl.karolpat.service.UserService;
import pl.karolpat.service.VehicleService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;
	private VehicleService vehicleService;
	private ParkingMeterService parkingMeterService;

	public UserServiceImpl(UserRepo userRepo, VehicleService vehicleService, ParkingMeterService parkingMeterService) {
		this.userRepo = userRepo;
		this.vehicleService = vehicleService;
		this.parkingMeterService=parkingMeterService;
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
	public User startParking(String vehicleNumber, User user) {
		
		user.setStarted(true);
		Vehicle vehicle = new Vehicle(vehicleNumber, user);
		vehicleService.save(vehicle);
		userRepo.save(user);
		
		ParkingMeter parkingMeter = new ParkingMeter(new Timestamp(System.currentTimeMillis()),user, vehicle);
		parkingMeterService.save(parkingMeter);
		return user;
	}

}
