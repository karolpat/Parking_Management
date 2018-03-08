package pl.karolpat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
	
	Vehicle findOneByNumber(String number);
	
	List<Vehicle> findAllByNumber(String number);

}
