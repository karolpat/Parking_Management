package pl.karolpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.ParkingMeter;

public interface ParkingMeterRepo extends JpaRepository<ParkingMeter, Long> {

	ParkingMeter getFirstByUserIdOrderByStart(long id);

}
