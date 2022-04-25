package cz.jannejezchleba.timeismoney.ui.screen.money

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.jannejezchleba.timeismoney.util.DataStoreHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class InfoCollectViewModel @Inject constructor(private val dataStoreHelper: DataStoreHelper) : ViewModel() {
    companion object {
        private const val averageWorkDaysInMonth = 21.6
    }

    private val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance()
    private var rawDailyWage = 0

    private var _dailyStats = MutableLiveData("0")
    private var _weekStats = MutableLiveData("0")
    private var _monthStats = MutableLiveData("0")
    private var _yearStats = MutableLiveData("0")
    private var _vacationStats = MutableLiveData("0")
    val dailyStats: LiveData<String> = _dailyStats
    val weekStats: LiveData<String> = _weekStats
    val monthStats: LiveData<String> = _monthStats
    val yearStats: LiveData<String> = _yearStats
    val vacationStats: LiveData<String> = _vacationStats


    init {
        currencyFormatter.maximumFractionDigits = 0
        currencyFormatter.currency = Currency.getInstance("CZK")
    }

    suspend fun saveInfo(salary: String,
                         hourRate: String,
                         hours: String,
                         vacation: String,
                         type: Boolean) {
        if (type) {
            dataStoreHelper.saveSalary(salary)
        } else {
            dataStoreHelper.saveHourRate(hourRate)
        }
        dataStoreHelper.saveHours(hours)
        dataStoreHelper.saveVacation(vacation)
        dataStoreHelper.saveSalaryType(type)
        dataStoreHelper.saveDailyWage(rawDailyWage)
        dataStoreHelper.saveUserIsNew(false)
    }

    suspend fun saveDailyWage() {
        dataStoreHelper.saveDailyWage(rawDailyWage)
    }

    fun updateStatistics(
        salary: String,
        hourRate: String,
        hours: String,
        vacation: String,
        type: Boolean
    ) {
        val countingSalary = salary.ifBlank { "0" }
        val countingHourRate = hourRate.ifBlank { "0" }
        if (hours.isNotBlank()) {
            computeDailySalary(countingSalary, countingHourRate, hours, type)
            computeWeekSalary(countingSalary, countingHourRate, hours, type)
            computeMonthSalary(countingSalary, countingHourRate, hours, type)
            computeYearSalary(countingSalary, countingHourRate, hours, type)

            if (vacation.isNotBlank()) {
                computeVacationValue(countingSalary, countingHourRate, hours, vacation, type)
            }
        }
    }

    private fun computeDailySalary(salary: String, hours: String, hourRate: String, type: Boolean) {
        val result = if (type) {
            salary.toInt() / averageWorkDaysInMonth
        } else {
            hours.toInt() * hourRate.toDouble()
        }
        rawDailyWage = result.roundToInt()
        _dailyStats.value = currencyFormatter.format(result.roundToInt())
    }

    private fun computeWeekSalary(salary: String, hours: String, hourRate: String, type: Boolean) {
        val result = if (type) {
            (salary.toInt() / averageWorkDaysInMonth) * 5
        } else {
            hours.toInt() * hourRate.toDouble() * 5
        }
        _weekStats.value = currencyFormatter.format(result.roundToInt())
    }

    private fun computeMonthSalary(salary: String, hours: String, hourRate: String, type: Boolean) {
        val result = if (type) {
            salary.toDouble()
        } else {
            (hours.toInt() * hourRate.toDouble() * averageWorkDaysInMonth)
        }
        _monthStats.value = currencyFormatter.format(result.roundToInt())
    }


    private fun computeYearSalary(salary: String, hours: String, hourRate: String, type: Boolean) {
        val result = if (type) {
            (salary.toDouble() * 12)
        } else {
            (hours.toInt() * hourRate.toDouble() * averageWorkDaysInMonth * 12)
        }
        _yearStats.value = currencyFormatter.format(result.roundToInt())
    }

    private fun computeVacationValue(
        salary: String,
        hours: String,
        hourRate: String,
        vacation: String,
        type: Boolean
    ) {
        val result = if (type) {
            ((salary.toInt() / averageWorkDaysInMonth) * vacation.toInt())
        } else {
            (hours.toInt() * hourRate.toDouble() * vacation.toInt())
        }
        _vacationStats.value = currencyFormatter.format(result.roundToInt())
    }
}