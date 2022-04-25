package cz.jannejezchleba.timeismoney.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.component.HeaderCard
import cz.jannejezchleba.timeismoney.ui.component.StatisticsItem
import cz.jannejezchleba.timeismoney.ui.component.SwitchButton
import cz.jannejezchleba.timeismoney.ui.component.UserInfoField
import cz.jannejezchleba.timeismoney.ui.screen.money.InfoCollectViewModel
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.timeismoney.util.DataStoreHelper
import kotlinx.coroutines.launch

@Preview
@Composable
fun MoneyScreen(
    navController: NavHostController = NavHostController(LocalContext.current),
    viewModel: InfoCollectViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreHelper(context)


    val savedHours = dataStore.getHours.collectAsState(initial = "")
    val savedHoursRate = dataStore.getHourRate.collectAsState(initial = "")
    val savedSalary = dataStore.getSalary.collectAsState(initial = "")
    val savedVacation = dataStore.getVacation.collectAsState(initial = "")
    val savedSalaryType = dataStore.getSalaryType.collectAsState(initial = true)

    var salaryField by remember { mutableStateOf("") }
    var hourlyField by remember { mutableStateOf("") }
    var hoursField by remember { mutableStateOf("") }
    var vacationField by remember { mutableStateOf("") }

    val dailyStats by viewModel.dailyStats.observeAsState()
    val weekStats by viewModel.weekStats.observeAsState()
    val monthStats by viewModel.monthStats.observeAsState()
    val yearStats by viewModel.yearStats.observeAsState()
    val vacationStats by viewModel.vacationStats.observeAsState()

    var salaryType by remember { mutableStateOf(true) }

    viewModel.updateStatistics(
        savedSalary.value ?: "",
        savedHoursRate.value ?: "",
        savedHours.value ?: "",
        savedVacation.value ?: "",
        savedSalaryType.value ?: true
    )

    salaryField = savedSalary.value ?: ""
    hourlyField = savedHoursRate.value ?: ""
    hoursField = savedHours.value ?: ""
    vacationField = savedVacation.value ?: ""
    salaryType = savedSalaryType.value ?: true

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(CustomMaterialTheme.paddings.defaultPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderCard("USER INFO")
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                SwitchButton("HOURLY", false, !salaryType) {
                    salaryType = false
                    viewModel.updateStatistics(
                        salaryField,
                        hourlyField,
                        hoursField,
                        vacationField,
                        salaryType
                    )
                    scope.launch {
                        dataStore.saveSalaryType(salaryType)
                        viewModel.saveDailyWage()
                    }
                }
                SwitchButton("MONTHLY", true, salaryType) {
                    salaryType = true
                    viewModel.updateStatistics(
                        salaryField,
                        hourlyField,
                        hoursField,
                        vacationField,
                        salaryType
                    )
                    scope.launch {
                        dataStore.saveSalaryType(salaryType)
                        viewModel.saveDailyWage()
                    }
                }
            }
            if (salaryType) {
                UserInfoField(
                    "Your monthly salary",
                    "20000",
                    salaryField,
                    R.drawable.ic_price_24,
                    "Salary",
                    "Kč"
                ) {
                    salaryField = it
                    viewModel.updateStatistics(
                        salaryField,
                        hourlyField,
                        hoursField,
                        vacationField,
                        salaryType
                    )
                    scope.launch {
                        dataStore.saveSalary(it)
                        viewModel.saveDailyWage()
                    }
                }
            } else {
                UserInfoField(
                    "Your hourly wage",
                    "180",
                    hourlyField,
                    R.drawable.ic_price_24,
                    "Salary",
                    "Kč/h"
                ) {
                    hourlyField = it
                    viewModel.updateStatistics(
                        salaryField,
                        hourlyField,
                        hoursField,
                        vacationField,
                        salaryType
                    )
                    scope.launch {
                        dataStore.saveHourRate(it)
                        viewModel.saveDailyWage()
                    }
                }
            }
            UserInfoField(
                "Avg. work time per day",
                "8",
                hoursField,
                R.drawable.ic_time_24,
                "Hours",
                "h"
            ) {
                hoursField = it
                viewModel.updateStatistics(
                    salaryField,
                    hourlyField,
                    hoursField,
                    vacationField,
                    salaryType
                )
                scope.launch {
                    dataStore.saveHours(it)
                    viewModel.saveDailyWage()
                }
            }
            UserInfoField(
                "Vacation days per year",
                "20",
                vacationField,
                R.drawable.ic_vacation_24,
                "Vacation",
                "days"
            ) {
                vacationField = it
                viewModel.updateStatistics(
                    salaryField,
                    hourlyField,
                    hoursField,
                    vacationField,
                    salaryType
                )
                scope.launch {
                    dataStore.saveVacation(it)
                }
            }
            Divider()
            HeaderCard("SUMMARY")
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                border = BorderStroke(1.dp, color = CustomMaterialTheme.colors.primary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CustomMaterialTheme.paddings.defaultPadding),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StatisticsItem("Daily rate (avg.)", dailyStats!!)
                    StatisticsItem("Weekly rate (avg.)", weekStats!!)
                    StatisticsItem("Monthly rate (avg.)", monthStats!!)
                    StatisticsItem("Yearly rate (avg.)", yearStats!!)
                    StatisticsItem("Vacation value (avg.)", vacationStats!!)
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
