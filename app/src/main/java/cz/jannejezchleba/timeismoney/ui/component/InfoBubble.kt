package cz.jannejezchleba.timeismoney.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun InfoBubble(info: String, iconResource: Int) {
    Card(
        contentColor = CustomMaterialTheme.colors.onBackground,
        backgroundColor = CustomMaterialTheme.colors.background,
        shape = RoundedCornerShape(50),
        border = BorderStroke(2.dp, CustomMaterialTheme.colors.primaryVariant),
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = CustomMaterialTheme.paddings.smallPadding,
                horizontal = CustomMaterialTheme.paddings.defaultPadding
            ),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(iconResource),
                contentDescription = "Currently left",
                tint = CustomMaterialTheme.colors.primary
            )
            Text(text = info, style = CustomMaterialTheme.typography.button)
        }
    }
}