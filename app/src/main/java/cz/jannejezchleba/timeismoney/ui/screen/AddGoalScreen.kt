package cz.jannejezchleba.timeismoney.ui.screen

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.component.HeaderCard
import cz.jannejezchleba.timeismoney.ui.component.UserInfoField
import cz.jannejezchleba.timeismoney.ui.styles.customButtonColors
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme

@Preview
@Composable
fun AddGoalScreen(navController: NavHostController = NavHostController(LocalContext.current)) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }


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
            HeaderCard("GOAL INFO")
            UserInfoField(
                title = "Goal name",
                placeholder = "New car",
                value = "",
                iconLeading = R.drawable.ic_goal_24,
                iconDesc = "Item",
                textTrailing = "",
                onChange = {}
            )
            UserInfoField(
                title = "Goal price",
                placeholder = "200",
                value = "",
                iconLeading = R.drawable.ic_price_24,
                iconDesc = "Price",
                textTrailing = "",
                onChange = {}
            )
            HeaderCard("GOAL IMAGE")
            if (imageUri == null) {
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .clickable {
                            launcher.launch("image/*")
                        },
                    elevation = 5.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.default_image),
                        contentDescription = "Default goal",
                        contentScale = ContentScale.Fit
                    )
                }
            } else {
                Card(
                    modifier = Modifier
                        .clickable {
                            launcher.launch("image/*")
                        },
                    elevation = 0.dp
                ) {
                    imageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap.value = MediaStore.Images
                                .Media.getBitmap(context.contentResolver,it)

                        } else {
                            val source = ImageDecoder
                                .createSource(context.contentResolver,it)
                            bitmap.value = ImageDecoder.decodeBitmap(source)
                        }

                        bitmap.value?.let {  btm ->
                            Image(bitmap = btm.asImageBitmap(),
                                contentScale = ContentScale.Inside,
                                contentDescription =null)
                        }
                    }
                }
            }


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(50),

                onClick = {

                },
                colors = customButtonColors(),
            ) {
                Text(text = "CREATE GOAL")
            }
        }
    }
}