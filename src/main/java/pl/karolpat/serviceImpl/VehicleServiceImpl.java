package pl.karolpat.serviceImpl;

import org.springframework.stereotype.Service;

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

}
