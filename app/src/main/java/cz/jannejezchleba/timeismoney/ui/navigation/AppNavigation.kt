package cz.jannejezchleba.timeismoney.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            //choose routes where top nav is hidden
            when (navBackStackEntry?.destination?.route) {
                AppScreens.SplashScreen.name -> {}
                AppScreens.InfoCollectScreen.name -> {}
                else -> TopAppNavigation(
                    navController,
                    AppScreens.getNavigationItem(navBackStackEntry?.destination?.route).title
                )
            }

        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AppNavigationGraph(navController)
            }
        },
        bottomBar = {
            //choose routes where bottom nav is hidden
            when (navBackStackEntry?.destination?.route) {
                AppScreens.SplashScreen.name -> {}
                AppScreens.InfoCollectScreen.name -> {}
                else -> BottomAppNavigation(navController)
            }
        }
    )
}

@Preview
@Composable
private fun BottomAppNavigation(navController: NavController = NavController(LocalContext.current)) {
    val screens = listOf(
        AppScreens.MoneyScreen,
        AppScreens.TimeScreen,
    )
    Box(contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.background(Color.Transparent)) {
        BottomNavigation(
            backgroundColor = CustomMaterialTheme.colors.primaryVariant,
            contentColor = CustomMaterialTheme.colors.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
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
                    alwaysShowLabel = false,
                    selected = currentRoute == screen.name,
                    onClick = {
                        navController.navigate(screen.name) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(AppScreens.HomeScreen.name)
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
        BottomFloatingButton(navController)
    }
}

@Composable
private fun BottomFloatingButton(navController: NavController = NavController(LocalContext.current)) {
    Column(
        Modifier.background(Color.Transparent)
    ) {
        Surface(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(CustomMaterialTheme.colors.background)
                .padding(10.dp),
        ) {
            FloatingActionButton(
                onClick = { navController.navigate(AppScreens.AddItemScreen.name) },
                backgroundColor = CustomMaterialTheme.colors.secondary,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.ic_add_24),
                    contentDescription = "Add item",
                    tint = CustomMaterialTheme.colors.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }


}

@Composable
private fun TopAppNavigation(navController: NavController = NavController(LocalContext.current), currentScreenTitle: String) {
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
                    navController.navigate(AppScreens.HomeScreen.name) }) {
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