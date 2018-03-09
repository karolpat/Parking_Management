package pl.karolpat.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.DailyIncome;

public interface DailyIncomeRepo extends JpaRepository<DailyIncome, Long> {

	DailyIncome findOneByDate(LocalDate date);

}
