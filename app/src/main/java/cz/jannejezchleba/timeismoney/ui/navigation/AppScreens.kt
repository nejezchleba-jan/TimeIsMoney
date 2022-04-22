package cz.jannejezchleba.timeismoney.ui.navigation

import cz.jannejezchleba.timeismoney.R

enum class AppScreens {
    SplashScreen,
    InfoCollectScreen,
    AddItemScreen,
    HomeScreen,
    MoneyScreen,
    TimeScreen;
    companion object {
        fun getNavigationItem(route: String?): NavigationItem
                = when (route?.substringBefore("/")) {
            InfoCollectScreen.name -> NavigationItem("Your info", R.drawable.ic_person_pin_24)
            HomeScreen.name -> NavigationItem("Home", R.drawable.ic_home_24)
            MoneyScreen.name -> NavigationItem("Money", R.drawable.ic_money_24)
            TimeScreen.name -> NavigationItem("Time", R.drawable.ic_time_24)
            AddItemScreen.name -> NavigationItem("Add item", R.drawable.ic_add_circle_24)
            else -> NavigationItem("Unknown", R.drawable.ic_help_24)
        }

    }

}