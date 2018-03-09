package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import pl.karolpat.entity.DailyIncome;

public interface DailyIncomeService {

	List<DailyIncome> getAllDailyIncome();

	DailyIncome save(DailyIncome dailyInc);

	Object getOneByDate(String date);

	DailyIncome addIncome(Map<String, Double> map);

}