package cz.jannejezchleba.timeismoney.ui.styles

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun customButtonColors(
    backgroundColor: Color = CustomMaterialTheme.colors.primary,
    contentColor: Color = Color.White,
) = ButtonDefaults.buttonColors(
    backgroundColor = backgroundColor,
    contentColor =  contentColor
)