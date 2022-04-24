package cz.jannejezchleba.timeismoney.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.ui.component.GoalItem
import cz.jannejezchleba.timeismoney.ui.component.HeaderCard
import cz.jannejezchleba.timeismoney.ui.screen.goals.GoalsViewModel
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme


@Composable
fun HomeScreen(
    navController: NavHostController = NavHostController(LocalContext.current),
    viewModel: GoalsViewModel = hiltViewModel()
) {
    val pinnedGoals by viewModel.pinnedGoals.observeAsState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(CustomMaterialTheme.paddings.smallPadding)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                HeaderCard("PINNED GOALS")
            }
            if (pinnedGoals.isNullOrEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("NO GOALS PINNED")
                    }
                }
            } else {
                items(pinnedGoals!!) { item ->
                    GoalItem(item)
                }
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}