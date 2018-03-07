package pl.karolpat.service;

import java.util.List;

import org.joda.time.LocalDate;

import pl.karolpat.entity.DailyIncome;

public interface DailyIncomeService {
	
	List<DailyIncome> getAllDailyIncome();
	DailyIncome save(DailyIncome dailyInc);
	DailyIncome getOneById(long id);
	DailyIncome getOneByDate(LocalDate localDate);
	DailyIncome save(DailyIncome dailyInc, long id);
	
}