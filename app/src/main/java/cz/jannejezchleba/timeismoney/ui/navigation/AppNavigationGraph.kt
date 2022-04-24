package cz.jannejezchleba.timeismoney.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.jannejezchleba.timeismoney.ui.screen.GoalScreen
import cz.jannejezchleba.timeismoney.ui.screen.HomeScreen
import cz.jannejezchleba.timeismoney.ui.screen.MoneyScreen
import cz.jannejezchleba.timeismoney.ui.screen.SplashScreen
import cz.jannejezchleba.timeismoney.ui.screen.add.AddGoalScreen
import cz.jannejezchleba.timeismoney.ui.screen.goals.EditGoalScreen
import cz.jannejezchleba.timeismoney.ui.screen.money.InfoCollectScreen

@Composable
fun AppNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name) {
        composable(AppScreens.SplashScreen.name) {
            SplashScreen(navController)
        }
        composable(AppScreens.InfoCollectScreen.name) {
            InfoCollectScreen(navController)
        }
        composable(AppScreens.HomeScreen.name) {
            HomeScreen(navController)
        }
        composable(AppScreens.MoneyScreen.name) {
            MoneyScreen(navController)
        }
        composable(AppScreens.TimeScreen.name) {
            GoalScreen(navController)
        }
        composable(AppScreens.AddGoalScreen.name) {
            AddGoalScreen(navController)
        }
        composable(AppScreens.EditGoalScreen.name + "/{goalId}", arguments = listOf(navArgument("goalId") {
            defaultValue = ""
        })) {
            EditGoalScreen(navController, goalId = it.arguments?.getString("goalId")!!.toInt())
        }
    }
}