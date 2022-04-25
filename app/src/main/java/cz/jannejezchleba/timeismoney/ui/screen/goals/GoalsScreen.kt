package cz.jannejezchleba.timeismoney.ui.screen

import android.widget.Toast
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.ui.component.HeaderCard
import cz.jannejezchleba.timeismoney.ui.component.TimeItem
import cz.jannejezchleba.timeismoney.ui.navigation.AppScreens
import cz.jannejezchleba.timeismoney.ui.screen.goals.GoalsViewModel
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Preview
@Composable
fun GoalScreen(
    navController: NavHostController = NavHostController(LocalContext.current),
    viewModel: GoalsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val allGoals by viewModel.allGoals.observeAsState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(CustomMaterialTheme.paddings.smallPadding),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                HeaderCard("ALL GOALS")
            }
            if (allGoals.isNullOrEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("NO GOALS SET")
                    }
                }
            } else {

                items(allGoals!!) { item ->
                    val onEdit = {
//                        viewModel.getGoal(item.id!!)
                        navController.navigate(AppScreens.EditGoalScreen.name + "/" + item.id)
                    }
                    val onPin = {
                        if (item.isPinned) {
                            viewModel.unpinGoal(item.id!!)
                            Toast.makeText(
                                context,
                                "GOAL UNPINNED FROM HOME SCREEN",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.pinGoal(item.id!!)
                            Toast.makeText(
                                context,
                                "GOAL PINNED TO HOME SCREEN",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    val onDelete = {
                        viewModel.deleteGoal(item.id!!)
                        Toast.makeText(
                            context,
                            "GOAL DELETED",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    TimeItem(item, onEdit, onPin, onDelete)
                }
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}