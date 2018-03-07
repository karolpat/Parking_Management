package pl.karolpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

}
