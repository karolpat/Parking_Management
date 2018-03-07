package pl.karolpat.serviceImpl;

import java.util.List;

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

	@Override
	public ParkingMeter findUserParkingMeter(long id) {
		return parkingMeterRepo.getFirstByUserIdOrderByStart(id) ;
	}

	@Override
	public List<ParkingMeter> findAll() {
		return parkingMeterRepo.findAll();
	}

	
}
