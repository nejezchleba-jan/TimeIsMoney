package cz.jannejezchleba.timeismoney.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.component.StatisticsItem
import cz.jannejezchleba.timeismoney.ui.component.SwitchButton
import cz.jannejezchleba.timeismoney.ui.component.UserInfoField
import cz.jannejezchleba.timeismoney.ui.navigation.AppScreens
import cz.jannejezchleba.timeismoney.ui.screen.info.InfoCollectViewModel
import cz.jannejezchleba.timeismoney.ui.styles.customButtonColors
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.timeismoney.util.DataStoreHelper
import kotlinx.coroutines.launch

@Preview
@Composable
fun InfoCollectScreen(
    navController: NavHostController = NavHostController(LocalContext.current),
    viewModel: InfoCollectViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreHelper(context)

    val dailyStats by viewModel.dailyStats.collectAsState()
    val weekStats by viewModel.weekStats.collectAsState()
    val monthStats by viewModel.monthStats.collectAsState()
    val yearStats by viewModel.yearStats.collectAsState()
    val vacationStats by viewModel.vacationStats.collectAsState()

    var salaryType by remember { mutableStateOf(false) }
    var salaryField by remember { mutableStateOf("")}
    var hourlyField by remember { mutableStateOf("") }
    var hoursField by remember { mutableStateOf("") }
    var vacationField by remember { mutableStateOf("") }

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
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.ic_person_pin_24),
                    contentDescription = "User info",
                    tint = CustomMaterialTheme.colors.primaryVariant
                )
                Text(text = "User info", style = CustomMaterialTheme.typography.h6)
            }

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
                }
            }
            if (salaryType) {
                UserInfoField(
                    "Your monthly salary",
                    "20000",
                    salaryField,
                    R.drawable.ic_money_24,
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
                }
            } else {
                UserInfoField(
                    "Your hourly wage",
                    "180",
                    hourlyField,
                    R.drawable.ic_money_24,
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
            }
            Divider()
            Text(text = "Summary", style = CustomMaterialTheme.typography.h6)
            StatisticsItem("Daily rate (avg.)", dailyStats)
            StatisticsItem("Weekly rate (avg.)", weekStats)
            StatisticsItem("Monthly rate (avg.)", monthStats)
            StatisticsItem("Yearly rate (avg.)", yearStats)
            StatisticsItem("Vacation value (avg.)", vacationStats)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(50),

                onClick = {
                    scope.launch {
                        if (salaryType) {
                            dataStore.saveSalary(salaryField)
                        } else {
                            dataStore.saveHourRate(hourlyField)
                        }
                        dataStore.saveHours(hoursField)
                        dataStore.saveVacation(vacationField)
                        dataStore.saveSalaryType(salaryType)
                        dataStore.saveUserIsNew(false)
                        navController.backQueue.clear()
                        navController.navigate(AppScreens.HomeScreen.name)
                    }
                },
                colors = customButtonColors(),
            ) {
                Text(text = "CONFIRM")
            }
        }
    }
}
