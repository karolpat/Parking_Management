package pl.karolpat.repository;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.DailyIncome;

public interface DailyIncomeRepo extends JpaRepository<DailyIncome, LocalDate> {

}
