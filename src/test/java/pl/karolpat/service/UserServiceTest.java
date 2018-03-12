package pl.karolpat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import pl.karolpat.entity.DailyIncome;
import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.entity.User;
import pl.karolpat.entity.Vehicle;
import pl.karolpat.repository.UserRepo;
import pl.karolpat.serviceImpl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	private UserService userService;
	private UserRepo userRepo;
	private VehicleService vehicleService;
	private ParkingMeterService parkingMeterService;
	private DailyIncomeService dailyIncomeService;
	User user1;
	User user2;
	User user3;
	Vehicle vehicle;
	ParkingMeter parkingMeter;

	@Before
	public void setup() {
		userRepo = Mockito.mock(UserRepo.class);
		vehicleService = Mockito.mock(VehicleService.class);
		parkingMeterService = Mockito.mock(ParkingMeterService.class);
		dailyIncomeService = Mockito.mock(DailyIncomeService.class);
		userService = new UserServiceImpl(userRepo, vehicleService, parkingMeterService, dailyIncomeService);
		user1 = new User("Karol");
		user2 = new User("Paweł");
		user3 = new User("Gaweł");
		vehicle = new Vehicle();
		parkingMeter = new ParkingMeter();

	}

	@Test
	public void testGetAllUsers() {
		// given
		List<User> users = Arrays.asList(user1, user2, user3);
		when(userRepo.findAll()).thenReturn(users);
		// when
		List<User> result = userService.getAllUsers();
		// then
		assertEquals(users.size(), result.size());
		assertThat(result).hasOnlyElementsOfType(User.class);
	}

	@Test
	public void testGetOneById() {
		user1.setId(1);
		when(userRepo.findOne(1l)).thenReturn(user1);

		User result = userService.getOneById(1l);

		assertEquals(user1, result);
	}

	@Test
	public void testSave_User_when_username_is_NOT_valid() {
		User user = new User();

		String username = "Mati";
		user.setUsername(username);

		when(userRepo.findOneByUsername(username)).thenReturn(user);
		when(userRepo.save(user)).thenReturn(user);

		Object result = userService.save(username);

		assertThat(result).isNotNull();
		assertThat(result).isInstanceOf(String.class);
	}

	@Test
	public void testSetUserAsVip_when_started_is_false() {

		user1.setId(1l);
		user1.setStarted(false);

		when(userRepo.findOne(1l)).thenReturn(user1);
		when(userRepo.save(user1)).thenReturn(user1);

		Object result = userService.setUserAsVip(1l);

		assertEquals(user1, result);
		assertThat(result).isInstanceOf(User.class);
		assertThat(((User) result).isVip()).isEqualTo(true);

	}

	@Test
	public void testSetUserAsVip_when_started_is_true() {

		user1.setId(1l);
		user1.setStarted(true);

		when(userRepo.findOne(1l)).thenReturn(user1);
		when(userRepo.save(user1)).thenReturn(user1);

		Object result = userService.setUserAsVip(1l);

		assertThat(result).isInstanceOf(String.class);

	}

	@Test
	public void testFindAllWhereVip() {

		user1.setVip(true);
		user2.setVip(true);
		boolean vip = true;

		List<User> list = Arrays.asList(user1, user2);

		when(userRepo.findAllByVip(vip)).thenReturn(list);

		List<User> result = userService.findAllWhereVip(vip);

		assertEquals(list, result);
		assertThat(result).hasOnlyElementsOfType(User.class);

	}

	@Test
	public void testStartParking() {

		String number = "ELW 5432S";
		Timestamp start = new Timestamp(System.currentTimeMillis());
		Set<Vehicle> vehicles = new HashSet<>();
		Set<ParkingMeter> parkingMeters = new HashSet<>();

		user1.setStarted(true);

		vehicle.setNumber(number);
		vehicle.setOwner(user1);

		parkingMeter.setStart(start);
		parkingMeter.setUser(user1);

		vehicles.add(vehicle);
		parkingMeters.add(parkingMeter);

		user1.setVehicle(vehicles);
		user1.setParking(parkingMeters);

		when(vehicleService.createVehicle(number, user1)).thenReturn(vehicle);
		when(userRepo.save(user1)).thenReturn(user1);
		when(parkingMeterService.save(parkingMeter)).thenReturn(parkingMeter);

		User result = userService.startParking(number, user1);

		assertNotNull(result);
		assertThat(result.getVehicle()).isNotEmpty();
		assertThat(result.getParking()).isNotEmpty();
		assertThat(result.getVehicle()).containsExactly(vehicle);
		assertThat(result.getParking()).containsExactly(parkingMeter);
	}

	@Test
	public void testFinishParking() {

		Map<String, Double> map = new HashMap<>();
		map.put("PLN", 3.0);
		map.put("Hours spent", 1.0);

		user1.setStarted(false);
		user1.setVip(false);

		DailyIncome dailyInc = new DailyIncome();
		dailyInc.setIncome(new BigDecimal(3.0));

		when(userRepo.save(user1)).thenReturn(user1);
		when(parkingMeterService.checkCost(user1)).thenReturn(map);
		when(dailyIncomeService.addIncome(map)).thenReturn(dailyInc);

		Map<String, Double> result = userService.finishParking(user1);

		assertEquals(map, result);
	}

	@Test
	public void testGetOneByUsername() {

		when(userRepo.findOneByUsername("Karol")).thenReturn(user1);

		User result = userService.getOneByUsername("Karol");

		assertThat(result.getUsername()).isEqualTo("Karol");
		assertEquals(user1, result);
	}

}
