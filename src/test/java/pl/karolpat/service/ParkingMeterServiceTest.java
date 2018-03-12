package pl.karolpat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.entity.User;
import pl.karolpat.repository.ParkingMeterRepo;
import pl.karolpat.serviceImpl.ParkingMeterServiceImpl;

@RunWith(SpringRunner.class)
public class ParkingMeterServiceTest {

	private ParkingMeterRepo parkingMeterRepo;
	private ParkingMeterService parkingMeterService;
	ParkingMeter parkingMeter1;
	ParkingMeter parkingMeter2;
	User user1;
	String HOURS;
	String PLN_CURRENCY;
	Timestamp start;

	@Before
	public void setUp() throws Exception {
		parkingMeterRepo = Mockito.mock(ParkingMeterRepo.class);
		parkingMeterService = new ParkingMeterServiceImpl(parkingMeterRepo);
		parkingMeter1 = new ParkingMeter();
		parkingMeter2 = new ParkingMeter();
		user1 = new User();

		Calendar calendar = Calendar.getInstance();    //This block is to create time that will
		calendar.setTime(new Date());					//be constant for tests to eliminate
		calendar.add(Calendar.HOUR, -5);				//failures caused by difference in current time.
		start = new Timestamp(calendar.getTimeInMillis());

	}

	@Test
	public void testSave() {
		// given
		when(parkingMeterRepo.save(parkingMeter1)).thenReturn(parkingMeter1);
		// when
		ParkingMeter result = parkingMeterService.save(parkingMeter1);
		// then
		assertThat(result).isInstanceOf(ParkingMeter.class);
		assertEquals(parkingMeter1, result);
	}

	@Test
	public void testFindUserParkingMeter() {

		user1.setId(1l);
		parkingMeter1.setStart(Timestamp.valueOf("2017-1-1 12:55:00"));
		parkingMeter2.setStart(Timestamp.valueOf("2017-1-1 12:55:30"));
		parkingMeter1.setUser(user1);
		parkingMeter2.setUser(user1);

		when(parkingMeterRepo.getFirstByUserIdOrderByStart(1l)).thenReturn(parkingMeter2);

		ParkingMeter result = parkingMeterService.findUserParkingMeter(1l);

		assertThat(result).isInstanceOf(ParkingMeter.class);
		assertEquals(parkingMeter2, result);
	}

	@Test
	public void testFindAll() {

		List<ParkingMeter> list = Arrays.asList(parkingMeter1, parkingMeter2);
		when(parkingMeterRepo.findAll()).thenReturn(list);

		List<ParkingMeter> result = parkingMeterService.findAll();

		assertThat(result).hasOnlyElementsOfType(ParkingMeter.class);
		assertEquals(list, result);
	}

	@Test
	public void testCheckCost_when_users_is_vip() {

		parkingMeter1.setStart(start);

		user1.setId(1);
		user1.setVip(true); // User's vip status to true

		Set<ParkingMeter> parkings = new HashSet<>();
		parkings.add(parkingMeter1);
		user1.setParking(parkings);

		when(parkingMeterRepo.getFirstByUserIdOrderByStart(1l)).thenReturn(parkingMeter1);

		Map<String, Double> map = parkingMeterService.checkCost(user1);

		assertNotNull(map);
		assertThat(map).containsKey("PLN");
		assertThat(map).containsValue(16.25);
		assertThat(map).containsKey("Hours spent");
		assertThat(map).containsValue(5.0);
	}

	@Test
	public void testCheckCost_when_users_is_NOT_vip() {

		parkingMeter1.setStart(start);

		user1.setId(1);
		user1.setVip(false); // User's vip status to false

		Set<ParkingMeter> parkings = new HashSet<>();
		parkings.add(parkingMeter1);
		user1.setParking(parkings);

		when(parkingMeterRepo.getFirstByUserIdOrderByStart(1l)).thenReturn(parkingMeter1);

		Map<String, Double> map = parkingMeterService.checkCost(user1);

		assertNotNull(map);
		assertThat(map).containsKey("PLN");
		assertThat(map).containsValue(31.0);
		assertThat(map).containsKey("Hours spent");
		assertThat(map).containsValue(5.0);
	}

	@Test
	public void testGetCurrentHours() {

		parkingMeter1.setStart(start);
		when(parkingMeterRepo.getFirstByUserIdOrderByStart(1l)).thenReturn(parkingMeter1);

		int result = parkingMeterService.getCurrentHours(1l);

		assertEquals(5, result);
	}

	@Test
	public void testGetCostIfVip() {

		int hours = 5;

		double result = parkingMeterService.getCostIfVip(hours);
		assertEquals(16.25, result, 0);
	}

	@Test
	public void testGetCostUnlessVip() {

		int hours = 5;

		double result = parkingMeterService.getCostUnlessVip(hours);
		assertEquals(31, result, 0);
	}

	@Test
	public void testSaveSetEnd() {

		user1.setId(1l);
		when(parkingMeterRepo.getFirstByUserIdOrderByStart(1l)).thenReturn(parkingMeter1);

		ParkingMeter result = parkingMeterService.saveSetEnd(user1);

		assertThat(result.getEnd()).isNotNull();
		assertThat(result.getEnd()).isInstanceOf(Timestamp.class);

	}

	@Test
	public void testRound() {

		double value = 12.1234567890;
		int places = 2;

		double result = ParkingMeterServiceImpl.round(value, places);

		assertEquals(12.12, result, 0);
	}

}
