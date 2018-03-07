package pl.karolpat.service;

import java.util.List;

import pl.karolpat.entity.ParkingMeter;

public interface ParkingMeterService {

	ParkingMeter save(ParkingMeter PM);
	
	ParkingMeter findUserParkingMeter(long id);
	
	List<ParkingMeter> findAll();
}
