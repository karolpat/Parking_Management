package pl.karolpat.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
	public DailyIncome getOneByDate(String date) {
//		org.joda.time.format.DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return dailyIincomeRepo.findOneByDate(null);
	}

//	@Override
//	public DailyIncome save(DailyIncome dailyInc, LocalDate date) {
//		DailyIncome tmp = dailyIincomeRepo.findOne(date);
//		tmp.setIncome(dailyInc.getIncome());
//		return dailyIincomeRepo.save(tmp);
//	}

	@Override
	public DailyIncome addIncome(Map<String, Double> map) {
		
		double income = map.get("PLN");
		LocalDate today = LocalDate.now();
		System.out.println(income);
		
		DailyIncome dailyInc=dailyIincomeRepo.findOneByDate(today);
		if(dailyInc==null) {
			dailyInc = new DailyIncome();
			dailyInc.setDate(today);
			dailyInc.setIncome(new BigDecimal(income));
		}else {
			dailyInc.setIncome(dailyInc.getIncome().add(new BigDecimal(income)));
		}
		
		
		return dailyIincomeRepo.save(dailyInc);
	}

}