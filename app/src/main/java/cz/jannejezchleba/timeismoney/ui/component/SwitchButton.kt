package cz.jannejezchleba.timeismoney.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.timeismoney.ui.styles.customButtonColors
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun SwitchButton(title: String, isLeftSide: Boolean, isSelected: Boolean, onClick: () -> Unit) {
    if (isSelected) {
        Button(
            modifier = Modifier.width(150.dp),
            shape = if (isLeftSide) RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50) else RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50),
            onClick = onClick,
            colors = customButtonColors()
        ) {
            Text(text = title)
        }
    } else {
        OutlinedButton(
            modifier = Modifier.width(150.dp),
            shape = if (isLeftSide) RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50) else RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = CustomMaterialTheme.colors.onBackground
            ),
            border = BorderStroke(1.dp, CustomMaterialTheme.colors.primaryVariant)
        ) {
            Text(text = title)
        }
    }
}