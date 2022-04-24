package cz.jannejezchleba.timeismoney.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.ui.component.HeaderCard
import cz.jannejezchleba.timeismoney.ui.component.TimeItem
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Preview
@Composable
fun TimeScreen(navController: NavHostController = NavHostController(LocalContext.current)) {
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderCard("ALL TIME GOALS")
            TimeItem("New shoes", "", 1500, 20)
            TimeItem("Dream car", "", 2000000, 365)
            TimeItem("Trip to Japan", "", 50000, 60,)
        }
    }
}