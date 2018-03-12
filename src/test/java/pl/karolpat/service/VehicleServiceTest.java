package pl.karolpat.service;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;
import pl.karolpat.entity.User;
import pl.karolpat.entity.Vehicle;
import pl.karolpat.repository.VehicleRepo;
import pl.karolpat.serviceImpl.VehicleServiceImpl;

@RunWith(SpringRunner.class)
public class VehicleServiceTest {

	private VehicleRepo vehicleRepo;
	private VehicleService vehicleService;
	Vehicle vehicle;
	Vehicle vehicle1;
	Vehicle vehicle2;
	Vehicle vehicle3;
	User user1;
	User user2;
	String number;

	@Before
	public void setUp() throws Exception {
		vehicleRepo = Mockito.mock(VehicleRepo.class);
		vehicleService = new VehicleServiceImpl(vehicleRepo);
		vehicle1 = new Vehicle();
		vehicle2 = new Vehicle();
		vehicle3 = new Vehicle();
		user1 = new User();
		user2 = new User();
		number = "ELW 21KR";

	}

	@Test
	public void testSave() {
		when(vehicleRepo.save(vehicle1)).thenReturn(vehicle1);

		Vehicle result = vehicleService.save(vehicle1);

		assertThat(result).isInstanceOf(Vehicle.class);
		assertEquals(vehicle1, result);

	}

	@Test
	public void testCreateVehicle() {

		User user = new User();
		vehicle = new Vehicle(number, user);

		when(vehicleRepo.save(vehicle)).thenReturn(vehicle);

		Vehicle result = vehicleService.save(vehicle);

		assertThat(result).isInstanceOf(Vehicle.class);
		assertEquals(vehicle, result);
		assertEquals(vehicle.getNumber(), result.getNumber());

	}

	@Test
	public void testGetVehiclesByNumber() {

		vehicle1.setNumber(number);
		vehicle2.setNumber(number);
		vehicle3.setNumber("ELW 4S01");
		List<Vehicle> list = Arrays.asList(vehicle1, vehicle2);

		when(vehicleRepo.findAllByNumber(number)).thenReturn(list);

		List<Vehicle> result = vehicleService.getVehiclesByNumber(number);

		assertThat(result).hasOnlyElementsOfType(Vehicle.class);
		assertThat(result.size()).isEqualTo(2);
		assertEquals(list, result);
	}

	@Test
	public void testIsStarted_should_return_true() {
		
		user1.setStarted(true);
		vehicle = new Vehicle(number,user1);
		List<Vehicle> list = Arrays.asList(vehicle);
		
		Optional<Vehicle> result = vehicleService.isStarted(list);
		
		assertThat(result.isPresent()).isEqualTo(true);
	}

	@Test
	public void testIsStarted_should_return_false() {

		user1.setStarted(false);
		vehicle = new Vehicle(number,user1);
		List<Vehicle> list = Arrays.asList(vehicle);
		
		Optional<Vehicle> result = vehicleService.isStarted(list);
		
		assertThat(result.isPresent()).isEqualTo(false);
	}

}
