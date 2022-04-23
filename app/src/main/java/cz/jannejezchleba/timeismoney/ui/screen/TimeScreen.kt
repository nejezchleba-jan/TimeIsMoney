package cz.jannejezchleba.timeismoney.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.ui.component.TimeItem
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun TimeScreen(navController: NavHostController) {
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, color = CustomMaterialTheme.colors.primary)
            ) {
                Text(
                    "ALL GOALS",
                    style = CustomMaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(CustomMaterialTheme.paddings.defaultPadding)
                )
            }
            TimeItem("New shoes", "", 1500, 20)
            TimeItem("Dream car", "", 2000000, 365)
            TimeItem("Trip to Japan", "", 50000, 60,)
        }
    }
}