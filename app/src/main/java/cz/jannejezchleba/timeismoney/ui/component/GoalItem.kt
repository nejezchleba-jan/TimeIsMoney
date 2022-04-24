package cz.jannejezchleba.timeismoney.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme
import java.io.File

@Composable
fun GoalItem(title: String, imagePath: String = "", price: Int, timeLeft: Int) {
    if (imagePath.isBlank() || !File(imagePath).exists()) {
        DefaultGoal(title = title, price = price, timeLeft = timeLeft)
    } else {
        GoalWithImage(title = title, imagePath = imagePath, price = price, timeLeft = timeLeft)
    }
}

@Composable
private fun DefaultGoal(title: String, price: Int, timeLeft: Int) {
    Card(
        elevation = 5.dp,
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, CustomMaterialTheme.colors.primaryVariant)
    ) {
        Column {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.default_image),
                    contentDescription = title
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(CustomMaterialTheme.paddings.smallPadding),
                ) {
                    OutlinedButton(
                        onClick = {},
                        enabled = false,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, CustomMaterialTheme.colors.primaryVariant),
                        colors = ButtonDefaults.buttonColors(disabledContentColor = CustomMaterialTheme.colors.primaryVariant)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_money_24),
                                contentDescription = "Value",
                            )
                            Text(text = price.toString())
                        }
                    }
                    OutlinedButton(
                        onClick = {},
                        enabled = false,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, CustomMaterialTheme.colors.primaryVariant),
                        colors = ButtonDefaults.buttonColors(disabledContentColor = CustomMaterialTheme.colors.primaryVariant)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_time_24),
                                contentDescription = "Currently left",
                            )
                            Text(text = "$timeLeft DAYS")
                        }
                    }
                }
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                1F to Color.Gray
                            )
                        )
                        .padding(CustomMaterialTheme.paddings.smallPadding),
                ) {
                    Text(
                        title,
                        style = CustomMaterialTheme.typography.h4,
                        color = CustomMaterialTheme.colors.primaryVariant
                    )
                }
            }

        }
    }
}

@Composable
private fun GoalWithImage(title: String, imagePath: String, price: Int, timeLeft: Int) {
    Card(
        elevation = 5.dp,
    ) {
        Column {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = rememberImagePainter(imagePath),
                    contentDescription = title
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(CustomMaterialTheme.paddings.smallPadding),
                ) {
                    OutlinedButton(
                        onClick = {},
                        enabled = false,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, CustomMaterialTheme.colors.primaryVariant),
                        colors = ButtonDefaults.buttonColors(disabledContentColor = CustomMaterialTheme.colors.primaryVariant)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_money_24),
                                contentDescription = "Value",
                            )
                            Text(text = price.toString())
                        }
                    }
                    OutlinedButton(
                        onClick = {},
                        enabled = false,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, CustomMaterialTheme.colors.primaryVariant),
                        colors = ButtonDefaults.buttonColors(disabledContentColor = CustomMaterialTheme.colors.primaryVariant)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_time_24),
                                contentDescription = "Currently left",
                            )
                            Text(text = "$timeLeft DAYS")
                        }
                    }
                }
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                1F to Color.Gray
                            )
                        )
                        .padding(CustomMaterialTheme.paddings.smallPadding),
                ) {
                    Text(
                        title,
                        style = CustomMaterialTheme.typography.h4,
                        color = Color.White
                    )
                }
            }

        }
    }
}