package pl.karolpat.serviceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.entity.User;
import pl.karolpat.repository.ParkingMeterRepo;
import pl.karolpat.service.ParkingMeterService;

@Service
public class ParkingMeterServiceImpl implements ParkingMeterService {

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
		return parkingMeterRepo.getFirstByUserIdOrderByStart(id);
	}

	@Override
	public List<ParkingMeter> findAll() {
		return parkingMeterRepo.findAll();
	}

	@Override
	public double checkCost(User user, long id) {

		int hours = getCurrentHours(id);
		if (user.isVip() == true) {
			return getCostIfVip(hours);
		} else {
			return getCostUnlessVip(hours);
		}

	}

	@Override
	public int getCurrentHours(long id) {

		ParkingMeter current = findUserParkingMeter(id);
		
		long start = TimeUnit.MINUTES.convert(current.getStart().getTime(), TimeUnit.MILLISECONDS);
		long now = TimeUnit.MINUTES.convert(new Timestamp(System.currentTimeMillis()).getTime(), TimeUnit.MILLISECONDS);
		
		int hours = (int) (now - start) / 60;
		return hours;
	}

	@Override
	public double getCostIfVip(int hours) {

		double perHour = 2;
		double price = 0;

		if (hours > 1) {
			for (int i = 1; i < hours; i++) {
				price += perHour;
				perHour *= 1.5;
			}
		}

		return price;
	}

	@Override
	public double getCostUnlessVip(int hours) {

		double perHour = 2;
		double price = 1;

		if (hours > 1) {
			for (int i = 1; i < hours; i++) {
				price += perHour;
				perHour *= 2;
			}
		}

		return price;
	}

}
