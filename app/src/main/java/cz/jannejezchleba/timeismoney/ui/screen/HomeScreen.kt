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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.component.GoalItem
import cz.jannejezchleba.timeismoney.ui.styles.customButtonColors
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, color = CustomMaterialTheme.colors.primary)
            ) {
                Text(
                    "CURRENT GOALS",
                    style = CustomMaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(CustomMaterialTheme.paddings.defaultPadding)
                )
            }
           GoalItem("New shoes", "", 1500, 20)
            GoalItem("Dream car", "", 2000000, 365)
            GoalItem("Trip to Japan", "", 50000, 60)
        }
    }
}

@Preview
@Composable
private fun TimeGoal() {
    Card(
        elevation = 5.dp,
        border = BorderStroke(1.dp, CustomMaterialTheme.colors.primaryVariant)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(0.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(0.5f).padding(0.dp),
                    shape = RoundedCornerShape(topStartPercent = 50),
                    onClick = { },
                    colors = customButtonColors()
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_edit_24,),
                        contentDescription = "Edit")
                }
                Divider(
                    color = CustomMaterialTheme.colors.background,
                    modifier = Modifier
                        .height(5.dp)
                        .width(1.dp)
                )
                Button(
                    modifier = Modifier.fillMaxWidth(1f).padding(0.dp),
                    shape = RoundedCornerShape(topEndPercent = 50),
                    onClick = { },
                    colors = customButtonColors()
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_bookmark_24,),
                        contentDescription = "Edit")
                }
            }
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.example_img),
                    contentDescription = "Blah",
                    contentScale = ContentScale.Fit
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
                            Text(text = "2 000")
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
                            Text(text = "20 DAYS")
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
                                1F to Color.Black
                            )
                        )
                        .padding(CustomMaterialTheme.paddings.smallPadding),
                ) {
                    Text(
                        "Trip to Japan",
                        style = CustomMaterialTheme.typography.h4,
                        color = Color.White
                    )
                }
            }

        }
    }
}