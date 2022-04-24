package cz.jannejezchleba.timeismoney.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun HeaderCard(title: String = "") {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(50),
        border = BorderStroke(2.dp, color = CustomMaterialTheme.colors.primary)
    ) {
        Text(
            title,
            style = CustomMaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(CustomMaterialTheme.paddings.defaultPadding)
        )
    }
}