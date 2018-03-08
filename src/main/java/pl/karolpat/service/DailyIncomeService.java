package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import pl.karolpat.entity.DailyIncome;

public interface DailyIncomeService {
	
	List<DailyIncome> getAllDailyIncome();
	DailyIncome save(DailyIncome dailyInc);
	DailyIncome getOneByDate(LocalDate date);
	DailyIncome save(DailyIncome dailyInc, LocalDate date);
	DailyIncome addIncome(Map<String, Double> map);
	
}