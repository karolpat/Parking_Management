/**
 * 
 */
package pl.karolpat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import pl.karolpat.entity.DailyIncome;
import pl.karolpat.repository.DailyIncomeRepo;
import pl.karolpat.serviceImpl.DailyIncomeServiceImpl;

@RunWith(SpringRunner.class)
public class DailyIncomeServiceTest {

	private DailyIncomeRepo dailyIncomeRepo;
	private DailyIncomeService dailyIncomeService;
	DailyIncome dailyInc1;
	DailyIncome dailyInc2;
	String dateCorrect;
	String dateIncorrect;

	@Before
	public void setUp() throws Exception {
		dailyIncomeRepo = Mockito.mock(DailyIncomeRepo.class);
		dailyIncomeService = new DailyIncomeServiceImpl(dailyIncomeRepo);
		dailyInc1 = new DailyIncome();
		dailyInc2 = new DailyIncome();
		dateCorrect = "12-03-2018";
		dateIncorrect="23-45-2ss2";
	}

	@Test
	public void testGetAllDailyIncome() {
		// given
		List<DailyIncome> list = Arrays.asList(dailyInc1, dailyInc2);
		when(dailyIncomeRepo.findAll()).thenReturn(list);
		// when
		List<DailyIncome> result = dailyIncomeService.getAllDailyIncome();
		// then
		assertThat(result).hasOnlyElementsOfType(DailyIncome.class);
		assertEquals(list, result);
	}

	@Test
	public void testSave() {
		//given
		dailyInc1.setIncome(new BigDecimal(4));
		when(dailyIncomeRepo.save(dailyInc1)).thenReturn(dailyInc1);
		//when
		DailyIncome result = dailyIncomeService.save(dailyInc1);
		//then
		assertThat(result.getIncome()).isEqualTo(dailyInc1.getIncome());
		assertThat(result).isInstanceOf(DailyIncome.class);
		
	}

	@Test
	public void testGetOneByDate_when_String_with_date_is_correct() {
		
		dailyInc1.setDate(LocalDate.now());
		when(dailyIncomeRepo.findOneByDate(LocalDate.now())).thenReturn(dailyInc1);
		
		Object result = dailyIncomeService.getOneByDate(dateIncorrect);
		
		assertThat(result).isInstanceOf(String.class);
		assertNotEquals(dailyInc1, result);
	}
	
	@Test
	public void testGetOneByDate_when_String_with_date_is_NOT_correct() {
		
		dailyInc1.setDate(LocalDate.now());
		when(dailyIncomeRepo.findOneByDate(LocalDate.now())).thenReturn(dailyInc1);
		
		Object result = dailyIncomeService.getOneByDate(dateCorrect);
		
		assertThat(result).isInstanceOf(DailyIncome.class);
	}

	@Test
	public void testAddIncome() {
		
		Map<String, Double> map = new HashMap<>();
		map.put("PLN", 3.0);
		double income = 3.0;
		LocalDate today = LocalDate.now();
		dailyInc1.setIncome(new BigDecimal(income));
		
		when(dailyIncomeRepo.findOneByDate(today)).thenReturn(dailyInc1);
		when(dailyIncomeRepo.save(dailyInc1)).thenReturn(dailyInc1);
		
		DailyIncome result = dailyIncomeService.addIncome(map);
		
		assertThat(result).isNotNull();
		assertEquals(new BigDecimal(6), result.getIncome() );
		
	}

}
