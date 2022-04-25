package cz.jannejezchleba.timeismoney.ui.component

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.data.domain.Goal
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Composable
fun GoalItem(goal: Goal = Goal()) {
    if (goal.imagePath.isBlank()) {
        DefaultGoal(goal)
    } else {
        GoalWithImage(goal)
    }
}

@Composable
private fun DefaultGoal(goal: Goal = Goal()) {
    Card(
        elevation = 5.dp,
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, CustomMaterialTheme.colors.primaryVariant)
    ) {
        Column {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.default_image),
                    contentDescription = goal.name
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(CustomMaterialTheme.paddings.smallPadding),
                ) {
                    InfoBubble(goal.price.toString(), R.drawable.ic_money_24)
                    InfoBubble(goal.time, R.drawable.ic_time_24)
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
                        goal.name,
                        style = CustomMaterialTheme.typography.h4,
                        color = CustomMaterialTheme.colors.primaryVariant
                    )
                }
            }

        }
    }
}

@Composable
private fun GoalWithImage(goal: Goal = Goal()) {
    val bitmap: Bitmap?
    val currentContentResolver = LocalContext.current.contentResolver
    goal.imagePath.let {
        val uri = Uri.parse(it)
        bitmap = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images
                .Media.getBitmap(currentContentResolver, uri)
        } else {
            val source = ImageDecoder
                .createSource(currentContentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
    }
    Card(
        elevation = 5.dp,
    ) {
        Column {
            Box(contentAlignment = Alignment.Center) {
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentScale = ContentScale.Inside,
                        contentDescription = goal.name
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.default_image),
                        contentDescription = goal.name,
                        contentScale = ContentScale.Inside
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(CustomMaterialTheme.paddings.smallPadding),
                ) {
                    InfoBubble(goal.price.toString(), R.drawable.ic_money_24)
                    InfoBubble(goal.time, R.drawable.ic_time_24)
                }
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                1F to Color.Black
                            )
                        )
                        .padding(CustomMaterialTheme.paddings.smallPadding),
                ) {
                    Text(
                        goal.name,
                        style = CustomMaterialTheme.typography.h4,
                        color = Color.White
                    )
                }
            }

        }
    }
}