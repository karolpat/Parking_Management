package pl.karolpat.serviceImpl;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import pl.karolpat.entity.User;
import pl.karolpat.entity.Vehicle;
import pl.karolpat.repository.VehicleRepo;
import pl.karolpat.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	private VehicleRepo vehicleRepo;

	public VehicleServiceImpl(VehicleRepo vehicleRepo) {
		this.vehicleRepo = vehicleRepo;
	}

	@Override
	public Vehicle save(Vehicle vehicle) {
		vehicleRepo.save(vehicle);
		return vehicle;
	}

	@Override
	public Vehicle createVehicle(String number, User user) {

		Vehicle vehicle = new Vehicle(number, user);
		return vehicleRepo.save(vehicle);

	}

	@Override
	public List<Vehicle> getVehiclesByNumber(String number) {
		return vehicleRepo.findAllByNumber(number);
	}

	@Override
	public Object isStarted(List<Vehicle> list) {
		return list.stream().filter(v -> v.getOwner().isStarted()).findFirst();

	}

}
