package cz.jannejezchleba.timeismoney.ui.component

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.data.domain.Goal
import cz.jannejezchleba.timeismoney.ui.styles.customButtonColors
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Preview
@Composable
fun TimeItem(
    goal: Goal = Goal(),
    onEdit: () -> Unit = {},
    onPin: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    if (goal.imagePath.isBlank()) {
        DefaultTimeItem(goal, onEdit, onPin, onDelete)
    } else {
        TimeItemWithImage(goal, onEdit, onPin, onDelete)
    }
}

@Composable
private fun DefaultTimeItem(
    goal: Goal = Goal(),
    onEdit: () -> Unit,
    onPin: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.background(Color.Transparent),
        elevation = 5.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.background(Color.White)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.default_image),
                    contentDescription = goal.name,
                    contentScale = ContentScale.Inside
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

            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.height(40.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(0.33f),
                    shape = RoundedCornerShape(0),
                    onClick = onEdit,
                    colors = customButtonColors()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit_24),
                        contentDescription = "Edit"
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    shape = RoundedCornerShape(0),
                    onClick = onPin,
                    colors = customButtonColors()
                ) {
                    Icon(
                        painter = if (goal.isPinned) painterResource(id = R.drawable.ic_bookmark_24) else painterResource(
                            id = R.drawable.ic_bookmark_border_24
                        ),
                        contentDescription = "Pin"
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(1f),
                    shape = RoundedCornerShape(0),
                    onClick = onDelete,
                    colors = customButtonColors()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = "Delete"
                    )
                }
            }

        }
    }
}

@Composable
private fun TimeItemWithImage(
    goal: Goal = Goal(),
    onEdit: () -> Unit,
    onPin: () -> Unit,
    onDelete: () -> Unit
) {
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
        modifier = Modifier.background(Color.Transparent),
        elevation = 5.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.background(Color.Transparent)
        ) {
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

            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.height(40.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(0.33f),
                    shape = RoundedCornerShape(0),
                    onClick = onEdit,
                    colors = customButtonColors()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit_24),
                        contentDescription = "Edit"
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    shape = RoundedCornerShape(0),
                    onClick = onPin,
                    colors = customButtonColors()
                ) {
                    Icon(
                        painter = if (goal.isPinned) painterResource(id = R.drawable.ic_bookmark_24) else painterResource(
                            id = R.drawable.ic_bookmark_border_24
                        ),
                        contentDescription = "Pin"
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(1f),
                    shape = RoundedCornerShape(0),
                    onClick = onDelete,
                    colors = customButtonColors()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = "Delete"
                    )
                }
            }

        }
    }
}