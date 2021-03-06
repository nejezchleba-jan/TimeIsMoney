package cz.jannejezchleba.timeismoney.ui.screen.add

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cz.jannejezchleba.timeismoney.R
import cz.jannejezchleba.timeismoney.ui.component.HeaderCard
import cz.jannejezchleba.timeismoney.ui.component.InfoBubble
import cz.jannejezchleba.timeismoney.ui.component.UserInfoField
import cz.jannejezchleba.timeismoney.ui.navigation.AppScreens
import cz.jannejezchleba.timeismoney.ui.screen.goals.GoalsViewModel
import cz.jannejezchleba.timeismoney.ui.styles.customButtonColors
import cz.jannejezchleba.timeismoney.ui.styles.customTextFieldColors
import cz.jannejezchleba.timeismoney.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.timeismoney.util.DataStoreHelper
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun AddGoalScreen(
    navController: NavHostController = NavHostController(LocalContext.current),
    viewModel: GoalsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    var nameField by rememberSaveable { mutableStateOf("") }
    var priceField by rememberSaveable { mutableStateOf("") }

    //Loading data
    val dataStore = DataStoreHelper(LocalContext.current)
    val savedDailyWage = dataStore.getDailyWage.collectAsState(initial = 0)

    val computedTime by viewModel.computedTime.observeAsState()

    val kc = LocalSoftwareKeyboardController.current

    //Image opening
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val imageGetLauncher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            imageUri = uri
        }
    }

    LaunchedEffect(imageUri) {
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
        }
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
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nameField,
                onValueChange = {
                    nameField = it
                },
                label = { Text(text = "Goal name", textAlign = TextAlign.End) },
                placeholder = { Text("New car") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        kc?.hide()
                    }
                ),
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.ic_goal_24),
                        tint = CustomMaterialTheme.colors.primaryVariant,
                        contentDescription = "Goal name"
                    )
                },
                colors = customTextFieldColors(),
                singleLine = true
            )
            UserInfoField(
                title = "Goal price",
                placeholder = "200",
                value = priceField,
                iconLeading = R.drawable.ic_price_24,
                iconDesc = "Price",
                onChange = {
                    priceField = it
                    if (it.isNotBlank()) {
                        viewModel.computeTimeForGoal(
                            savedDailyWage.value!!,
                            priceField.toDouble()
                        )
                    } else {
                        viewModel.computeTimeForGoal(
                            savedDailyWage.value!!,
                            0.0
                        )
                    }
                }
            )
            InfoBubble(computedTime!!, R.drawable.ic_time_24)
            HeaderCard("GOAL IMAGE")
            if (imageUri == null) {
                Card(
                    backgroundColor = Color.White,
                    contentColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            imageGetLauncher.launch(arrayOf("image/*"))
                        },
                    elevation = 5.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.default_image),
                        contentDescription = "Default goal",
                        contentScale = ContentScale.Inside
                    )
                }
            } else {
                Card(
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                imageGetLauncher.launch(arrayOf("image/*"))
                            }
                        },
                    elevation = 0.dp
                ) {
                    if (bitmap.value != null) {
                        Image(
                            bitmap = bitmap.value!!.asImageBitmap(),
                            contentScale = ContentScale.Inside,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(50),

                onClick = {
                    val imagePath = if (imageUri == null) {
                        ""
                    } else {
                        imageUri.toString()
                    }
                    viewModel.saveGoal(
                        nameField,
                        priceField.toInt(),
                        computedTime!!,
                        imagePath
                    )
                    Toast.makeText(
                        context,
                        "GOAL CREATED",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(AppScreens.HomeScreen.name)
                },
                colors = customButtonColors(),
            ) {
                Text(text = "CREATE GOAL")
            }
        }
    }
}