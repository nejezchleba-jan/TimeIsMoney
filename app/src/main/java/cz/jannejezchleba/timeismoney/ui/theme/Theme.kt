package cz.jannejezchleba.timeismoney.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White


private val DarkColorPalette = darkColors(
    primary = PurpleMain,
    primaryVariant = PurpleDark,
    secondary = PurpleAccent,
    error = Red,
    secondaryVariant = PurpleLight,
    surface = Black,
    background = Black,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
    onError = White

)

private val LightColorPalette = lightColors(
    primary = PurpleMain,
    primaryVariant = PurpleDark,
    secondary = PurpleAccent,
    error = Red,
    secondaryVariant = PurpleLight,
    surface = White,
    onPrimary = White,
    onSecondary = White,
    onBackground = Black,
    onSurface = Black,
    onError = White

)

@Composable
fun TimeIsMoneyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalPaddings provides Paddings()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object CustomMaterialTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val paddings: Paddings
        @Composable
        get() = LocalPaddings.current
}