package cz.jannejezchleba.timeismoney.ui.navigation

import cz.jannejezchleba.timeismoney.R

enum class AppScreens {
    SplashScreen,
    InfoCollectScreen,
    AddGoalScreen,
    EditGoalScreen,
    HomeScreen,
    MoneyScreen,
    TimeScreen;
    companion object {
        fun getNavigationItem(route: String?): NavigationItem
                = when (route?.substringBefore("/")) {
            HomeScreen.name -> NavigationItem("Home", R.drawable.ic_home_24)
            MoneyScreen.name -> NavigationItem("Money", R.drawable.ic_money_24)
            TimeScreen.name -> NavigationItem("Goals", R.drawable.ic_goal_24)
            AddGoalScreen.name -> NavigationItem("Add goal", R.drawable.ic_add_circle_24)
            EditGoalScreen.name -> NavigationItem("Edit goal", R.drawable.ic_edit_24)
            else -> NavigationItem("Unknown", R.drawable.ic_help_24)
        }

    }

}