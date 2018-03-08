package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.entity.User;

public interface ParkingMeterService {

	ParkingMeter save(ParkingMeter PM);
	
	ParkingMeter saveSetEnd(User user);
	
	ParkingMeter findUserParkingMeter(long id);
	
	List<ParkingMeter> findAll();
	
	Map<String, Double> checkCost(User user);
	
	int getCurrentHours(long id);
	
	double getCostIfVip(int hours);
	
	double getCostUnlessVip(int hours);
	
	
}