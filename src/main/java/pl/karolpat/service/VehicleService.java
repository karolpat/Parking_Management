package pl.karolpat.service;

import java.util.List;

import pl.karolpat.entity.User;
import pl.karolpat.entity.Vehicle;

public interface VehicleService {

	Vehicle save(Vehicle vehicle);

	Vehicle createVehicle(String number, User user);

	List<Vehicle> getVehiclesByNumber(String number);

	Object isStarted(List<Vehicle> list);

}
