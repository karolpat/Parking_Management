package pl.karolpat.service;

import java.util.List;

import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.entity.User;

public interface ParkingMeterService {

	ParkingMeter save(ParkingMeter PM);
	
	ParkingMeter findUserParkingMeter(long id);
	
	List<ParkingMeter> findAll();
	
	double checkCost(User user, long id);
	
	int getCurrentHours(long id);
	
	double getCostIfVip(int hours);
	
	double getCostUnlessVip(int hours);
}