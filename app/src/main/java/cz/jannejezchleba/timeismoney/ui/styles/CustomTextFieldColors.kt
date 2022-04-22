package cz.jannejezchleba.timeismoney.ui.styles

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun customTextFieldColors(
    textColor: Color = CustomMaterialTheme.colors.onBackground,
    disabledTextColor: Color = Color.Gray,
    backgroundColor: Color = CustomMaterialTheme.colors.background,
    cursorColor: Color = CustomMaterialTheme.colors.primary,
    errorCursorColor: Color = CustomMaterialTheme.colors.error,
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,
)