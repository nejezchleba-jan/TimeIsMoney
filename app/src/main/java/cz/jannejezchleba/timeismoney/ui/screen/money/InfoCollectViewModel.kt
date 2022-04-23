package cz.jannejezchleba.timeismoney.ui.screen.money

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class InfoCollectViewModel @Inject constructor() : ViewModel() {
    companion object {
        private const val averageWorkDaysInMonth = 21.6
    }

    private val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance()

    private var _dailyStats = MutableStateFlow("0")
    private var _weekStats = MutableStateFlow("0")
    private var _monthStats = MutableStateFlow("0")
    private var _yearStats = MutableStateFlow("0")
    private var _vacationStats = MutableStateFlow("0")
    val dailyStats: StateFlow<String> = _dailyStats
    val weekStats: StateFlow<String> = _weekStats
    val monthStats: StateFlow<String> = _monthStats
    val yearStats: StateFlow<String> = _yearStats
    val vacationStats: StateFlow<String> = _vacationStats

    init {
        currencyFormatter.maximumFractionDigits = 0
        currencyFormatter.currency = Currency.getInstance("CZK")
    }

    fun updateStatistics(
        salary: String,
        hourRate: String,
        hours: String,
        vacation: String,
        type: Boolean
    ) {
        val countingSalary = if (salary.isBlank()) "0" else salary
        val countingHourRate = if (hourRate.isBlank()) "0" else hourRate
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