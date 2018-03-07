package pl.karolpat.serviceImpl;

import org.springframework.stereotype.Service;

import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.repository.ParkingMeterRepo;
import pl.karolpat.service.ParkingMeterService;
@Service
public class ParkingMeterServiceImpl implements ParkingMeterService{

	
	private ParkingMeterRepo parkingMeterRepo;

	public ParkingMeterServiceImpl(ParkingMeterRepo parkingMeterRepo) {
		this.parkingMeterRepo = parkingMeterRepo;
	}

	@Override
	public ParkingMeter save(ParkingMeter PM) {
		parkingMeterRepo.save(PM);
		return PM;
	}
	
	
}
