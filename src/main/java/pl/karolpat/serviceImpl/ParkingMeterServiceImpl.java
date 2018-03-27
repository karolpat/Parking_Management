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

/**
 * @author karolpat
 *
 */
@Service
public class ParkingMeterServiceImpl implements ParkingMeterService {

	private ParkingMeterRepo parkingMeterRepo;

	static private String HOURS = "Hours spent";
	static private String PLN_CURRENCY = "PLN";

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

		int hours = getCurrentHours(user.getId()); // Number of hours that user spent at the parking.

		Map<String, Double> map = new HashMap<>();
		map.put(HOURS, (double) hours); // hours as a value to HOURS String key.

		if (user.isVip() == true) { // Depending on User's vip status total cost will be different
			map.put(PLN_CURRENCY, round(getCostIfVip(hours), 2));
			return map;
		} else {
			map.put(PLN_CURRENCY, round(getCostUnlessVip(hours), 2));
			return map;
		}

	}

	@Override
	public int getCurrentHours(long id) {

		ParkingMeter current = parkingMeterRepo.getFirstByUserIdOrderByStart(id);// First ParkingMeter found by user id ordered by start date.

		// Converts start date to minutes.
		long start = TimeUnit.MINUTES.convert(current.getStart().getTime(), TimeUnit.MILLISECONDS);

		// Converts end date (that is actually current time) to minutes.
		long now = TimeUnit.MINUTES.convert(new Timestamp(System.currentTimeMillis()).getTime(), TimeUnit.MILLISECONDS);

		int hours = (int) (now - start) / 60; // Converts difference of start and end time (minutes) to hours.
		return hours;
	}

	@Override
	public double getCostIfVip(int hours) {

		double perHour = 2;
		double price = 0;

		if (hours > 0) {
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

		if (hours > 0) {
			for (int i = 1; i < hours; i++) {

				price += perHour;
				perHour *= 2;
			}
		}

		return price;
	}

	@Override
	public ParkingMeter saveSetEnd(User user) {
		ParkingMeter parkingMeter = parkingMeterRepo.getFirstByUserIdOrderByStart(user.getId());
		parkingMeter.setEnd(new Timestamp(System.currentTimeMillis()));
		return parkingMeter;
	}

	/**
	 * Simple method to round given double value so that returned value has exactly
	 * two decimal places.
	 * 
	 * @param value
	 *            double value to be rounded.
	 * @param places
	 *            number of decimal places in returned double value.
	 * @return Rounded double value with given decimal places.
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

}
