package pl.karolpat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.entity.DailyIncome;
import pl.karolpat.service.DailyIncomeService;

@RestController
@RequestMapping("manager")
public class ManagerController {

	private DailyIncomeService dailyIncomeService;

	public ManagerController(DailyIncomeService dailyIncomeService) {
		this.dailyIncomeService = dailyIncomeService;
	}

	/**
	 * Shows a value of the daily income by given date in specific format. The
	 * format of input String is checked with regex in a proper Service.
	 * 
	 * @param date
	 *            input String of date that manager wants to check income of.
	 * @return DailyIncome entity of given date and the sum of income or String if
	 *         the input String does not pass the regex.
	 */
	@PostMapping("/dailyIncome")
	ResponseEntity<Object> dailyIncome(@RequestBody String date) {
		return ResponseEntity.ok(dailyIncomeService.getOneByDate(date));
	}

	/**
	 * Shows list of incomes in all days.
	 * 
	 * @return List of DailyIncome entities.
	 */
	@GetMapping("/all/dailyIncome")
	ResponseEntity<List<DailyIncome>> incomeListOfAllDays() {
		return ResponseEntity.ok(dailyIncomeService.getAllDailyIncome());
	}

}
