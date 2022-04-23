package cz.jannejezchleba.timeismoney.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun StatisticsItem(title: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = title, style = CustomMaterialTheme.typography.body1)
        Text(text = value, fontWeight = FontWeight.Bold)
    }
}