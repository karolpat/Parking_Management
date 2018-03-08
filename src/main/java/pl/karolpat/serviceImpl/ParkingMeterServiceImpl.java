package pl.karolpat.serviceImpl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public Map<String, Double> checkCost(User user) {

		int hours = getCurrentHours(user.getId());

		Map<String, Double> map = new HashMap<>();
		map.put("Hours spent", (double) hours);
		
		if (user.isVip() == true) {
			map.put("PLN", round(getCostIfVip(hours),2));
			return map;
		} else {
			map.put("PLN", round(getCostUnlessVip(hours),2));
			return map;
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
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}


	@Override
	public ParkingMeter saveSetEnd(User user) {
		ParkingMeter parkingMeter = parkingMeterRepo.getFirstByUserIdOrderByStart(user.getId());
		parkingMeter.setEnd(new Timestamp(System.currentTimeMillis()));
		return parkingMeter;
	}

}
