package pl.karolpat.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.DailyIncome;

public interface DailyIncomeRepo extends JpaRepository<DailyIncome, Long> {

	/**
	 * Search for the DailyIncome by given date.
	 * 
	 * @param date
	 *            date that constitues the DailyIncome entity.
	 * @return DailyIncome instance from database by given date.
	 */
	DailyIncome findOneByDate(LocalDate date);

}
