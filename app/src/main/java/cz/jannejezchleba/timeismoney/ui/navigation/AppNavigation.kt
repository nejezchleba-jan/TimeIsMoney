package cz.jannejezchleba.timeismoney.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Preview
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val shouldShowBottomNavigation = when (navBackStackEntry?.destination?.route) {
        AppScreens.SplashScreen.name -> false
        AppScreens.InfoCollectScreen.name -> false
        AppScreens.AddGoalScreen.name -> false
        AppScreens.EditGoalScreen.name -> false
        else -> true
    }

    val shouldShowTopNavigation = when (navBackStackEntry?.destination?.route) {
        AppScreens.SplashScreen.name -> false
        AppScreens.InfoCollectScreen.name -> false
        AppScreens.AddGoalScreen.name -> false
        AppScreens.EditGoalScreen.name -> false
        else -> true
    }

    val shouldShowTopNavigationWithClose = when (navBackStackEntry?.destination?.route) {
        AppScreens.AddGoalScreen.name -> true
        AppScreens.EditGoalScreen.name -> true
        else -> false
    }

    Scaffold(
        topBar = {
            if (shouldShowTopNavigation) {
                TopAppNavigation(
                    navController,
                    AppScreens.getNavigationItem(navBackStackEntry?.destination?.route).title
                )
            } else if (shouldShowTopNavigationWithClose) {
                TopAppNavigationWithClose(
                    navController,
                    AppScreens.getNavigationItem(navBackStackEntry?.destination?.route).title
                )
            }
        },
        content = { padding ->
//            Box(modifier = Modifier.padding(padding)) {
               AppNavigationGraph(navController)

//            }
        },
        floatingActionButton = {
            if (shouldShowBottomNavigation) {
                BottomFloatingButton(navController)
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            if (shouldShowBottomNavigation) {
                BottomAppNavigation(navController)
            }
        }
    )
}


@Composable
private fun BottomAppNavigation(navController: NavController = NavController(LocalContext.current)) {
    val screens = listOf(
        AppScreens.MoneyScreen,
        AppScreens.TimeScreen,
    )
    BottomAppBar(
        cutoutShape = RoundedCornerShape(50)
    ) {
        BottomNavigation(
            backgroundColor = CustomMaterialTheme.colors.primaryVariant,
            contentColor = CustomMaterialTheme.colors.onPrimary,
            modifier = Modifier.fillMaxWidth()
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            screens.forEach { screen ->
                val navItem = AppScreens.getNavigationItem(screen.name)
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = navItem.iconResource),
                            contentDescription = navItem.title
                        )
                    },
                    label = {
                        Text(text = navItem.title)
                    },
                    selectedContentColor = CustomMaterialTheme.colors.onPrimary,
                    unselectedContentColor = CustomMaterialTheme.colors.onPrimary.copy(0.6f),
                    alwaysShowLabel = true,
                    selected = currentRoute == screen.name,
                    onClick = {
                        navController.navigate(screen.name) {
                            popUpTo(AppScreens.HomeScreen.name)
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomFloatingButton(navController: NavController = NavController(LocalContext.current)) {
    FloatingActionButton(
        onClick = { navController.navigate(AppScreens.AddGoalScreen.name) },
        backgroundColor = CustomMaterialTheme.colors.secondary,
        shape = RoundedCornerShape(50)
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.ic_add_24),
            contentDescription = "Add item",
            tint = CustomMaterialTheme.colors.onPrimary
        )
    }
}

@Composable
private fun TopAppNavigation(
    navController: NavController = NavController(LocalContext.current),
    currentScreenTitle: String
) {
    TopAppBar(
        backgroundColor = CustomMaterialTheme.colors.primaryVariant,
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(currentScreenTitle)
                IconButton(onClick = {
                    navController.navigate(AppScreens.HomeScreen.name)
                }) {
                    val navItem = AppScreens.getNavigationItem(AppScreens.HomeScreen.name)
                    Icon(
                        painterResource(id = navItem.iconResource),
                        contentDescription = navItem.title,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        },
    )
}

@Composable
private fun TopAppNavigationWithClose(
    navController: NavController = NavController(LocalContext.current),
    currentScreenTitle: String
) {
    TopAppBar(
        backgroundColor = CustomMaterialTheme.colors.primaryVariant,
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(currentScreenTitle)
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painterResource(id = R.drawable.ic_close_24),
                        contentDescription = "Close",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        },
    )
}