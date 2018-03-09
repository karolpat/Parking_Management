package pl.karolpat.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import pl.karolpat.entity.DailyIncome;

public interface DailyIncomeService {
	
	List<DailyIncome> getAllDailyIncome();
	DailyIncome save(DailyIncome dailyInc);
	DailyIncome getOneByDate(String date);
//	DailyIncome save(DailyIncome dailyInc, LocalDate date);
	DailyIncome addIncome(Map<String, Double> map);
	
}