package cz.jannejezchleba.timeismoney.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.navigation.AppScreens
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.timeismoney.util.DataStoreHelper
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val dataStore = DataStoreHelper(context)
    val firstTimeAppStart = dataStore.getUserIsNew.collectAsState(initial = true)

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1000)
        if (firstTimeAppStart.value!!) {
            navController.navigate(AppScreens.InfoCollectScreen.name)
        } else {
            navController.navigate(AppScreens.HomeScreen.name)
        }
    }

    Splash(alpha = alphaAnim.value)
}

@Preview
@Composable
private fun Splash(alpha: Float = 1f) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(CustomMaterialTheme.colors.primary)
            .fillMaxSize()
    ) {
        Icon(
            modifier = Modifier
                .size(150.dp)
                .alpha(alpha),
            painter = painterResource(id = R.drawable.ic_money_24),
            contentDescription = "Logo icon",
            tint = Color.White
        )
    }
}