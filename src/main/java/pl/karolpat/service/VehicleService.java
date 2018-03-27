package pl.karolpat.service;

import java.util.List;
import java.util.Optional;

import pl.karolpat.entity.User;
import pl.karolpat.entity.Vehicle;

public interface VehicleService {

	Vehicle save(Vehicle vehicle);

	Vehicle createVehicle(String number, User user);

	List<Vehicle> getVehiclesByNumber(String number);

	Optional<Vehicle> isStarted(List<Vehicle> list);

}
