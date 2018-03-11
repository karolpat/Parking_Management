package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import pl.karolpat.entity.DailyIncome;

/**
 * @author karolpat
 *
 */
public interface DailyIncomeService {

	/**
	 * Gives list of all DailyIncomes that are present in the database.
	 * 
	 * @return List of DailyIncome
	 */
	List<DailyIncome> getAllDailyIncome();

	/**
	 * Saves to database new or edited DailyIncome
	 * 
	 * @param dailyInc
	 *            DailyIncome that is being saved into the database.
	 * @return DailyIncome that is being saved.
	 */
	DailyIncome save(DailyIncome dailyInc);

	/**
	 * Search for the DailyIncome in the database by given date. Date is given as a
	 * String and later it is parsed to LocaLDate format.
	 * 
	 * @param date
	 *            date that DailyIncome is being search of.
	 * @return Spring if the format of input String is incorrect or DailyIncome
	 *         instance.
	 */
	Object getOneByDate(String date);

	/**
	 * Adds income to the DailyIncome that is cost of stay that should be paid by
	 * User who uses the parking.
	 * 
	 * @param map
	 *            map that contains String that is currency of income (only PLN so
	 *            far) and Double as a value that is cost of stay. Also contains the
	 *            time of the stay.
	 * @return DailyIncome of which the income is increased.
	 */
	DailyIncome addIncome(Map<String, Double> map);

}