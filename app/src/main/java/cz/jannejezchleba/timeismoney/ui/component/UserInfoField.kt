package cz.jannejezchleba.timeismoney.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import cz.jannejezchleba.timeismoney.ui.styles.customTextFieldColors
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserInfoField(
    title: String,
    placeholder: String,
    value: String,
    iconLeading: Int,
    iconDesc: String,
    textTrailing: String,
    onChange: (String) -> Unit
) {
    val kc = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onChange(it) },
        label = { Text(text = title, textAlign = TextAlign.End) },
        placeholder = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                kc?.hide()
            }
        ),
        leadingIcon = {
            Icon(
                painterResource(id = iconLeading),
                tint = CustomMaterialTheme.colors.primaryVariant,
                contentDescription = iconDesc
            )
        },
        trailingIcon = {
            Text(textTrailing)
        },
        colors = customTextFieldColors(),
        singleLine = true
    )
}