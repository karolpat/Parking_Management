package pl.karolpat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.service.DailyIncomeService;

@RestController
@RequestMapping("manager")
public class ManagerController {

	private DailyIncomeService dailyIncomeService;

	public ManagerController(DailyIncomeService dailyIncomeService) {
		this.dailyIncomeService = dailyIncomeService;
	}

	@PostMapping("/dailyIncome")
	ResponseEntity<Object> dailyIncome(@RequestBody String date) {

		return ResponseEntity.ok(dailyIncomeService.getOneByDate(date));
	}

	@GetMapping("/all/dailyIncome")
	ResponseEntity<Object> test() {
		return ResponseEntity.ok(dailyIncomeService.getAllDailyIncome());
	}

}
