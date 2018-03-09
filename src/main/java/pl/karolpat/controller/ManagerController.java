package pl.karolpat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.service.DailyIncomeService;

@RestController
public class ManagerController {
	
	private DailyIncomeService dailyIncomeService;
	
	public ManagerController(DailyIncomeService dailyIncomeService) {
		this.dailyIncomeService=dailyIncomeService;
	}

	
	@PostMapping("/dailyIncome")
	ResponseEntity dailyIncome(@RequestBody String date) {
		
		return ResponseEntity.ok(dailyIncomeService.getOneByDate(date));
	}
	
	@GetMapping("/test")
	ResponseEntity test() {
		return ResponseEntity.ok(dailyIncomeService.getAllDailyIncome());
	}
	
}
