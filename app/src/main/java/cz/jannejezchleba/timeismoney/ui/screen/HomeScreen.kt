package cz.jannejezchleba.timeismoney.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme


@Composable
fun HomeScreen(navController: NavHostController = NavHostController(LocalContext.current)) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(CustomMaterialTheme.paddings.defaultPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                border = BorderStroke(1.dp, color = CustomMaterialTheme.colors.primary)
            ) {
                Text("Current goals", style = CustomMaterialTheme.typography.h5, textAlign= TextAlign.Center, modifier = Modifier.padding(CustomMaterialTheme.paddings.defaultPadding))
            }
            GoalItem()
            GoalItem()
            GoalItem()
        }
    }
}

@Preview
@Composable
private fun GoalItem() {
    Card(
        modifier = Modifier.padding(CustomMaterialTheme.paddings.defaultPadding),
        elevation = 5.dp,
        border = BorderStroke(1.dp, color = CustomMaterialTheme.colors.primary)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(CustomMaterialTheme.paddings.smallPadding)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_bookmark_24),
                    contentDescription = "Currently pinned",
                    tint = CustomMaterialTheme.colors.secondary
                )
                Text(text = "Pinned on 22.04.2022")
            }
            Divider()
            Box {
                Image(
                    painter = painterResource(id = R.drawable.example_img),
                    contentDescription = "Image"
                )
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                1F to Color.Black
                            )
                        )
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 16.dp),
                ) {
                    OutlinedButton(
                        onClick = {},
                        enabled = false,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(disabledContentColor = CustomMaterialTheme.colors.primaryVariant)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_time_24),
                                contentDescription = "Currently left",
                            )
                            Text(text = "20 DAYS")
                        }
                    }
                }
            }

        }
    }
}