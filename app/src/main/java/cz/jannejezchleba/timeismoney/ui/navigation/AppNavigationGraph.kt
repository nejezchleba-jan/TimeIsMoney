package cz.jannejezchleba.timeismoney.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.jannejezchleba.timeismoney.ui.screen.*

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
            TimeScreen(navController)
        }
        composable(AppScreens.AddGoalScreen.name) {
            AddGoalScreen(navController)
        }
    }
}