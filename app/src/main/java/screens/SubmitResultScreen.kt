package screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.heyjomui.R
import com.example.heyjomui.ui.theme.inter_bold
import com.example.heyjomui.ui.theme.inter_regular
import java.io.File
import java.io.IOException

private const val IMAGE_FILENAME = "selected_image.jpg"

@Composable
fun SubmitResultScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.lavenderGray))
            .verticalScroll(state = rememberScrollState())
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.yellowColor)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            navController.navigate("virtualRunDetailScreen")
                        }
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 24.dp),
                    text = "Submit Result",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = inter_bold,
                    color = Color.Black,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(554.dp)
        ) {
            UploadImageSection(context = LocalContext.current)
            SubmitEventInfoSection()
        }
        SubmitButtonsSection()
    }
}

@Composable
fun UploadImageSection(context: Context) {
    var selectedImageUri by remember { mutableStateOf(loadImageFromStorage(context)) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
            saveImageToStorage(context, uri)
        }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        if (selectedImageUri != null) {

            Image(
                painter = rememberImagePainter(data = selectedImageUri),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(120.dp)
                    .height(240.dp)
                    .padding(bottom = 16.dp)
                    .align(Alignment.Center)
            )

            Button(
                onClick = {
                    selectedImageUri?.let {
                        deleteImageFromStorage(context)
                        selectedImageUri = null
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp, 0.dp, 16.dp, 40.dp)
            ) {
                Text(text = "Delete")
            }
        } else {
            // Display the default image and the upload icon
            Image(
                painter = painterResource(id = R.drawable.upload_image_section),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = painterResource(id = R.drawable.upload_icon),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(96.dp)
                    .height(96.dp)
                    .clickable {
                        launcher.launch("image/*")
                    }
            )
        }
    }
}

/**
 * Save the selected image to internal storage using the specified filename.
 *
 * @param context The application context.
 * @param uri The Uri of the selected image.
 */
private fun saveImageToStorage(context: Context, uri: Uri?) {
    // Check if the URI is not null
    uri?.let {
        try {
            // Open an input stream to read the selected image content
            val inputStream = context.contentResolver.openInputStream(it)

            // Open an output stream to write the image content to a file in internal storage
            val outputStream = context.openFileOutput(IMAGE_FILENAME, Context.MODE_PRIVATE)

            // Use extension functions 'use' to automatically close streams after use
            inputStream?.use { input ->
                outputStream?.use { output ->
                    // Copy the content from the input stream to the output stream
                    input.copyTo(output)
                }
            }
        } catch (e: IOException) {
            // Handle IOException, for example, by printing the stack trace
            e.printStackTrace()
        }
    }
}


private fun deleteImageFromStorage(context: Context) {
    context.deleteFile(IMAGE_FILENAME)
}

/**
 * Load the selected image Uri from internal storage using the specified filename.
 *
 * @param context The application context.
 * @return The Uri of the selected image if it exists, otherwise null.
 */
private fun loadImageFromStorage(context: Context): Uri? {
    try {
        // Create a File object representing the location of the stored image in internal storage
        val file = File(context.filesDir, IMAGE_FILENAME)

        // Check if the file exists
        if (file.exists()) {
            // Return the Uri of the stored image file
            return Uri.fromFile(file)
        }
    } catch (e: Exception) {
        // Handle any exceptions that may occur, for example, by printing the stack trace
        e.printStackTrace()
    }

    // Return null if the image file doesn't exist or an exception occurs
    return null
}


@Composable
fun SubmitEventInfoSection() {

    Row(
        modifier = Modifier
            .fillMaxWidth() // .padding(16.dp, 190.dp, 16.dp, 8.dp).........
            .padding(16.dp, 190.dp, 16.dp, 8.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(
                width = 0.5.dp,
                color = colorResource(id = R.color.mutedGray),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "HeadHunter Virtual Run",
                fontFamily = inter_bold,
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
                modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                // Display the icon inside a circle with the icon background color
                Surface(
                    shape = CircleShape,
                    color = colorResource(id = R.color.yellowColor),
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.safety_glasse),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
                Text(
                    text = "5km Virtual Run",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    fontFamily = inter_regular,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            // horizontal divider
            HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                Column {
                    Text(
                        text = "Category",
                        color = colorResource(id = R.color.mutedGray),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_regular,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "Category 2: 5KM (Men open are 18-45 yo)",
                        color = colorResource(id = R.color.darkPurple),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            // horizontal divider
            HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))

            Column(
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Distance (KM)",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(0.3f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(48.dp)
                            .border(
                                width = 1.dp,
                                color = colorResource(id = R.color.lightGray),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .weight(0.7f),
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedTextField(
                            value = "000.00",
                            onValueChange = { },
                            modifier = Modifier
                                .fillMaxSize(),
                            textStyle = LocalTextStyle.current.copy(
                                color = colorResource(id = R.color.charcoalGray),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = colorResource(id = R.color.charcoalGray),
                                focusedBorderColor = Transparent,
                                unfocusedBorderColor = Transparent,
                            )
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Time",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(0.3f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(48.dp)
                            .border(
                                width = 1.dp,
                                color = colorResource(id = R.color.lightGray),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .weight(0.7f)
                    ) {
                        OutlinedTextField(
                            value = "hh:mm:ss",
                            onValueChange = { },
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                            textStyle = LocalTextStyle.current.copy(
                                color = colorResource(id = R.color.charcoalGray),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = colorResource(id = R.color.charcoalGray),
                                focusedBorderColor = Transparent,
                                unfocusedBorderColor = Transparent,
                            )
                        )

                    }
                }
                Text(
                    text = "e.g: Moving Time / Total Distance Time / Duration",
                    color = colorResource(id = R.color.charcoalGray),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                    fontFamily = inter_regular,
                    modifier = Modifier.padding(32.dp, 8.dp)
                )
            }
        }
    }
}

@Composable
fun SubmitButtonsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.lightGray))
        ) {
            Text(
                text = "Submit",
                color = Color.Black,
                fontFamily = inter_bold,
                fontSize = 14.sp,
            )
        }
        Text(
            text = "By clicking Submit, you declare that your submission\n" +
                    "is truthful and agree to our Terms & Conditions",
            color = colorResource(id = R.color.charcoalGray),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
            fontFamily = inter_regular,
            modifier = Modifier
                .padding(32.dp, 12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}