package pl.karolpat.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import pl.karolpat.entity.DailyIncome;
import pl.karolpat.repository.DailyIncomeRepo;
import pl.karolpat.service.DailyIncomeService;

@Service
public class DailyIncomeServiceImpl implements DailyIncomeService {

	private DailyIncomeRepo dailyIincomeRepo;

	public DailyIncomeServiceImpl(DailyIncomeRepo dailyIincomeRepo) {
		this.dailyIincomeRepo = dailyIincomeRepo;
	}

	@Override
	public List<DailyIncome> getAllDailyIncome() {
		return dailyIincomeRepo.findAll();
	}

	@Override
	public DailyIncome save(DailyIncome dailyInc) {
		return dailyIincomeRepo.save(dailyInc);
	}

	@Override
	public Object getOneByDate(String date) {

		boolean pattern = Pattern.matches("^([0-3][0-9]-[0-1][1-9]-[0-9]{4})$", date); // Regex pattern to check the
																						// format of input String
		if (pattern) {
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			return dailyIincomeRepo.findOneByDate(LocalDate.parse(date, df));
		} else {
			return "Wrong date format. Must be: \"dd-MM-yyyy\""; // Returned information for user about fail.
		}

	}

	@Override
	public DailyIncome addIncome(Map<String, Double> map) {

		double income = map.get("PLN"); // Takes form given map value of key "PLN" that is the cost of stay in the
										// parking.
		LocalDate today = LocalDate.now(); // Creates actual Date.

		DailyIncome dailyInc = dailyIincomeRepo.findOneByDate(today); // Searches for the DailyIncome by given date

		if (dailyInc == null) { // if DailyIncome instance is null that means in given dates there are no
								// records.

			dailyInc = new DailyIncome();
			dailyInc.setDate(today);
			dailyInc.setIncome(new BigDecimal(income));
		} else {

			dailyInc.setIncome(dailyInc.getIncome().add(new BigDecimal(income)));
		}

		return dailyIincomeRepo.save(dailyInc);
	}

}
